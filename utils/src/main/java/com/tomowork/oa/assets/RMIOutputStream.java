package com.tomowork.oa.assets;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * RMIOutputStream
 *
 * @author zlei
 *
 */
public class RMIOutputStream extends OutputStream implements Serializable {

	private final RMIOutputStreamInf out;

	public RMIOutputStream(RMIOutputStreamInf out) {
		this.out = out;
	}

	public void write(int b) throws IOException {
		out.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		out.write(b, off, len);
	}

	public void flush() throws IOException {
		out.flush();
	}

	public void close() throws IOException {
		out.close();
	}

}
