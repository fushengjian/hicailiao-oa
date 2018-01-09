package com.tomowork.oa.util;

import java.net.URI;
import java.net.URISyntaxException;

import com.tomowork.oa.velocity.tools.view.URIFactory;


/**
 * General URIFactory implementation.
 *
 * @author zlei
 */
public abstract class AbstractURIFactory implements URIFactory {

	public URI createURI(String path, String query, String fragment) {
		URI u = getURI(path, query);
		try {
			return new URI(u.getScheme(), null, u.getHost(), u.getPort(), combinePath(u.getPath(), path), query, fragment);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected abstract URI getURI(String path, String query);

	protected String combinePath(String start, String end) {
		if (end == null) {
			return start;
		} else if (start == null) {
			return end;
		} else {
			boolean startEnds = start.endsWith("/");
			boolean endStarts = end.startsWith("/");
			return startEnds ^ endStarts ? start + end : (startEnds & endStarts ? start + end.substring(1, end.length()) : start + '/' + end);
		}
	}
}
