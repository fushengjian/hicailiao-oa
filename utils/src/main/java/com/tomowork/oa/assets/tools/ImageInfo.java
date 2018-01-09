package com.tomowork.oa.assets.tools;

import java.io.Serializable;

public class ImageInfo implements Serializable {
	private static final long serialVersionUID = -1048343644759366420L;

	private String name;

	private ImageType type;

	private long width;

	private long height;

	private long size;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageType getType() {
		return type;
	}

	public void setType(ImageType type) {
		this.type = type;
	}

	public long getWidth() {
		return width;
	}

	public void setWidth(long width) {
		this.width = width;
	}

	public long getHeight() {
		return height;
	}

	public void setHeight(long height) {
		this.height = height;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String toString() {
		return String.format("type: [%s], size: [%d], height: [%d], width: [%d]", type, size, height, width);
	}
}
