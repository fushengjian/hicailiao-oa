package com.tomowork.oa.velocity.tools.view;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.velocity.tools.view.WebappResourceLoader;

/**
 * This is a structured whitespace globbing implementation that uses an early whitespace filtering while the resource is loaded.
 * <p/>
 * This version inherits from org.apache.velocity.tools.view.WebappResourceLoader, but one could easily craft a version inheriting from org.apache.velocity.runtime.resource.loader.FileResourceLoader.
 *
 * @author  zlei
 * @see <a href="http://wiki.apache.org/velocity/StructuredGlobbingResourceLoader">velocity wiki</a>
 */
public class StructuredGlobbingResourceLoader extends WebappResourceLoader {

	// FIXME: need synchronized ??? by hzl 2016/2/2
	public synchronized InputStream getResourceStream(String name) {
		return new VTLIndentationGlobber(super.getResourceStream(name));
	}

	public static class VTLIndentationGlobber extends FilterInputStream {
		protected String buffer = "";

		protected int bufpos = 0;

		protected enum State {
			defstate, hash, comment, directive, schmoo, eol, eof
		}

		protected State state = State.defstate;

		public VTLIndentationGlobber(InputStream is) {
			super(is);
		}

		// TODO - multiline comments #* ... *# not taken into account for now in all cases
		public int read() throws IOException {
			while (true) {
				switch (state) {
					case defstate: {
						int ch = in.read();
						switch (ch) {
							case (int) '#':
								state = State.hash;
								buffer = "";
								bufpos = 0;
								return ch;
							case (int) ' ':
							case (int) '\t':
								buffer += (char) ch;
								break;
							case -1:
								state = State.eof;
								break;
							default:
								buffer += (char) ch;
								state = State.schmoo;
								break;
						}
						break;
					}
					case eol:
						if (bufpos < buffer.length()) return (int) buffer.charAt(bufpos++);
						else {
							state = State.defstate;
							buffer = "";
							bufpos = 0;
							return '\n';
						}
					case eof:
						if (bufpos < buffer.length()) return (int) buffer.charAt(bufpos++);
						else return -1;
					case hash: {
						int ch = in.read();
						switch (ch) {
							case (int) '#':
								state = State.directive;
								return ch;
							case -1:
								state = State.eof;
								return -1;
							default:
								state = State.directive;
								buffer = "##";
								return ch;
						}
					}
					case directive: {
						int ch = in.read();
						if (ch == (int) '\n') {
							state = State.eol;
							break;
						} else if (ch == -1) {
							state = State.eof;
							break;
						} else return ch;
					}
					case schmoo: {
						int ch = in.read();
						if (ch == (int) '\n') {
							state = State.eol;
							break;
						} else if (ch == -1) {
							state = State.eof;
							break;
						} else {
							buffer += (char) ch;
							return (int) buffer.charAt(bufpos++);
						}
					}
				}
			}
		}

		public int read(byte[] b, int off, int len) throws IOException {
			int i;
			int ok = 0;
			while (len-- > 0) {
				i = read();
				if (i == -1) return (ok == 0) ? -1 : ok;
				b[off++] = (byte) i;
				ok++;
			}
			return ok;
		}

		public int read(byte[] b) throws IOException {
			return read(b, 0, b.length);
		}

		public boolean markSupported() {
			return false;
		}
	}
}