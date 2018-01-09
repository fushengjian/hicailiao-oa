package com.tomowork.oa.assets;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMIInputStream stub
 *
 * @author zlei
 *
 */
public interface RMIInputStreamInf extends Remote {

	int read() throws IOException;

	byte[] read(int len) throws IOException;

	long skip(long n) throws IOException;

	int available() throws IOException;

	void close() throws IOException;

	void mark(int readlimit) throws RemoteException;

	void reset() throws IOException;

	boolean markSupported() throws RemoteException;
}
