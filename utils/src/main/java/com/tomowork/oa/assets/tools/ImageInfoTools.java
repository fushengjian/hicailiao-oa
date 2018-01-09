package com.tomowork.oa.assets.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import com.drew.imaging.FileType;
import com.drew.imaging.FileTypeDetector;
import com.drew.imaging.ImageProcessingException;
import com.drew.imaging.bmp.BmpMetadataReader;
import com.drew.imaging.gif.GifMetadataReader;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.imaging.png.PngMetadataReader;
import com.drew.imaging.png.PngProcessingException;
import com.drew.imaging.tiff.TiffMetadataReader;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.bmp.BmpHeaderDirectory;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.gif.GifHeaderDirectory;
import com.drew.metadata.jpeg.JpegDirectory;
import com.drew.metadata.png.PngDirectory;

public class ImageInfoTools {

	public static ImageInfo getImageInfo(Path imgFile) {
		return getImageInfo(imgFile.toFile());
	}

	public static ImageInfo getImageInfo(File imgFile) {

		try (InputStream is = Files.newInputStream(imgFile.toPath(), StandardOpenOption.READ)) {
			ImageInfo info = getImageInfo(is);
			info.setName(imgFile.getName());
			info.setSize(imgFile.length());
			return info;
		} catch (IOException | JpegProcessingException | PngProcessingException | TiffProcessingException | MetadataException e) {
		} catch (ImageProcessingException e) {
		}

		return null;
	}

	private static ImageInfo getImageInfo(InputStream is) throws IOException, MetadataException, ImageProcessingException {

		ImageInfo info = new ImageInfo();

		BufferedInputStream bis = is instanceof BufferedInputStream ? (BufferedInputStream)is : new BufferedInputStream(is);
		FileType type = FileTypeDetector.detectFileType(bis);

		Metadata metadata;
		switch (type) {
		case Jpeg:
			metadata = JpegMetadataReader.readMetadata(bis);
			JpegDirectory jdi = metadata.getFirstDirectoryOfType(JpegDirectory.class);
			info.setType(ImageType.JPG);
			info.setHeight(jdi.getLong(JpegDirectory.TAG_IMAGE_HEIGHT));
			info.setWidth(jdi.getLong(JpegDirectory.TAG_IMAGE_WIDTH));
			break;
		case Png:
			metadata = PngMetadataReader.readMetadata(bis);
			PngDirectory pdi = metadata.getFirstDirectoryOfType(PngDirectory.class);
			info.setType(ImageType.PNG);
			info.setHeight(pdi.getLong(PngDirectory.TAG_IMAGE_HEIGHT));
			info.setWidth(pdi.getLong(PngDirectory.TAG_IMAGE_WIDTH));
			break;
		case Gif:
			metadata = GifMetadataReader.readMetadata(bis);
			GifHeaderDirectory gdi = metadata.getFirstDirectoryOfType(GifHeaderDirectory.class);
			info.setType(ImageType.GIF);
			info.setHeight(gdi.getLong(GifHeaderDirectory.TAG_IMAGE_HEIGHT));
			info.setWidth(gdi.getLong(GifHeaderDirectory.TAG_IMAGE_WIDTH));
			break;
		case Tiff:
			metadata = TiffMetadataReader.readMetadata(bis);
			ExifDirectoryBase tdi = metadata.getFirstDirectoryOfType(ExifDirectoryBase.class);
			info.setType(ImageType.TIFF);
			info.setHeight(tdi.getLong(ExifDirectoryBase.TAG_IMAGE_HEIGHT));
			info.setWidth(tdi.getLong(ExifDirectoryBase.TAG_IMAGE_WIDTH));
			break;
		case Bmp:
			metadata = BmpMetadataReader.readMetadata(bis);
			BmpHeaderDirectory bdi = metadata.getFirstDirectoryOfType(BmpHeaderDirectory.class);
			info.setType(ImageType.BMP);
			info.setHeight(bdi.getLong(BmpHeaderDirectory.TAG_IMAGE_HEIGHT));
			info.setWidth(bdi.getLong(BmpHeaderDirectory.TAG_IMAGE_WIDTH));
			break;
		default:
			throw new ImageProcessingException("unsupported file type");
		}

		return info;
	}
}
