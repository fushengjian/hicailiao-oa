package com.tomowork.oa.assets;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.rmi.Remote;

/**
 * 远程文件管理器
 *
 * @author zlei
 *
 */
public interface RemoteFSManager extends Remote {

	RMIOutputStream newRMIOutputStream(String remotePath, OpenOption... options) throws IOException;

	RMIInputStream newRMIInputStream(String remotePath, OpenOption... options) throws IOException;

	void copy(String srcPath, String dstPath, CopyOption... options) throws IOException;

	void move(String srcPath, String dstPath, CopyOption... options) throws IOException;

	void delete(String remotePath) throws IOException;

	boolean deleteIfExists(String remotePath) throws IOException;

	boolean exist(String remotePath, LinkOption... options) throws IOException;

	boolean isRegularFile(String remotePath, LinkOption... options) throws IOException;

	void createDirectory(String remotePath) throws IOException;

	void createDirectories(String remotePath) throws IOException;

	long size(String remotePath) throws IOException;

	long folderSize(String remoteFolder) throws IOException;
}
