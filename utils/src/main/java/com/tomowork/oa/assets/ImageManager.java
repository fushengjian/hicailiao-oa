package com.tomowork.oa.assets;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.rmi.Remote;

/**
 * 图片处理
 *
 * @author zlei
 *
 */
public interface ImageManager extends Remote {

	void waterMarkWithImage(String pressImg, String targetImg, int pos, float alpha) throws IOException;

	void waterMarkWithText(String filePath, String outPath, String text,
			Color color, Font font, int pos, float qualNum) throws IOException;

	void waterMarkWithText(String filePath, String outPath, String text,
			Color color, Font font, int left, int top, float qualNum) throws IOException;

	void imageScale(String sourceImg, String targetImg, int width, int height) throws IOException;
}
