package com.tomowork.oa.core.tools;


import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.tomowork.oa.assets.RemoteFSManager;
import com.tomowork.oa.foundation.domain.Accessory;
import com.tomowork.oa.foundation.domain.SysConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccessoryUtil {
	private static volatile AccessoryUtil instance;

	@Autowired
	private RemoteFSManager fsManager;

	@PostConstruct
	public void postConstruct() {
		instance = this;
	}


	public static boolean deleteFile(String path) {
		boolean flag = false;

		RemoteFSManager fs = instance.fsManager;

		try {
			if (fs.isRegularFile(path)) {
				fs.delete(path);
				flag = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public static boolean del_acc(SysConfig config, HttpServletRequest request, Accessory acc) {
		boolean ret1 = true;
		boolean ret2 = true;

		if (acc != null) {
			ret1 = deleteFile(acc.getPath() + File.separator + acc.getName());
			ret2 = deleteFile(acc.getPath() + File.separator + acc.getName() + "_small." + acc.getExt());
		}

		return ret1 && ret2;
	}

	public static boolean fileExist(String path) {
		File file = new File(path);
		return file.exists();
	}

	public static String getUploadPath(String uploadFilePath, ServletRequest request, String subPath) {
		if (subPath == null || subPath.trim().isEmpty())
			throw new IllegalArgumentException("invalid subPath: " + subPath);

		return subPath.trim();
	}
}
