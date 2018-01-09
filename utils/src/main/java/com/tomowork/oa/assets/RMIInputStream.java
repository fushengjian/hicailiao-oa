package com.tomowork.oa.assets;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * RMIInputStream
 *
 * @author zlei
 *
 */
public class RMIInputStream extends InputStream implements Serializable {
	private final RMIInputStreamInf in;

	public RMIInputStream(RMIInputStreamInf in) {
		this.in = in;
	}

	public int read() throws IOException {
		return in.read();
	}

	public int read(byte[] b, int off, int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		} else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		} else if (len == 0) {
			return 0;
		}

		byte[] c = in.read(len);
		int l = c.length < len ? c.length : len;
		System.arraycopy(c, 0, b, off, l);

		return l;
	}

	public long skip(long n) throws IOException {
		return in.skip(n);
	}

	public int available() throws IOException {
		return in.available();
	}

	public void close() throws IOException {
		in.close();
	}

	public synchronized void mark(int readlimit) {
		try {
			in.mark(readlimit);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void reset() throws IOException {
		in.reset();
	}

	public boolean markSupported() {
		try {
			return in.markSupported();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
}
