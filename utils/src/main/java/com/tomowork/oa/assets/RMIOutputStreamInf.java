package com.tomowork.oa.assets;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.rmi.Remote;

/**
 * RMIOutputStream stub
 *
 * @author zlei
 *
 */
public interface RMIOutputStreamInf extends Remote, Closeable, Flushable {

	void write(int b) throws IOException;

	void write(byte[] b, int off, int len) throws IOException;

	void flush() throws IOException;

	void close() throws IOException;
}
