package com.tomowork.oa.assets.tools;

import java.util.HashMap;
import java.util.Map;

public enum ImageType {
	/**
	 * JPEG
	 */
	JPG,

	/**
	 * PNG
	 */
	PNG,

	/**
	 * GIF
	 */
	GIF,

	/**
	 * TIFF
	 */
	TIFF,

	/**
	 * BMP
	 */
	BMP;

	private static final Map<String, ImageType> typeMap = new HashMap<>();
	static {
		typeMap.put("jpeg", JPG);
		typeMap.put("jpg", JPG);

		typeMap.put("png", PNG);
		typeMap.put("gif", GIF);
		typeMap.put("tiff", TIFF);
		typeMap.put("bmp", BMP);
	}

	public static ImageType typeOf(String type) {
		return type == null ? null : typeMap.get(type.toLowerCase());
	}

}
