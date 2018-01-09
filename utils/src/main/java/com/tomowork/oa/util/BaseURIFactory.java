package com.tomowork.oa.util;

import java.net.URI;

import com.tomowork.oa.velocity.tools.view.URIFactory;


/**
 * baseUrl + path
 *
 * @author zlei
 */
public class BaseURIFactory extends AbstractURIFactory implements URIFactory {
	private final URI uri;

	public BaseURIFactory(String uri) {
		uri = uri.trim();
		if (uri.isEmpty())
			throw new IllegalArgumentException();

		this.uri = URI.create(uri);
	}

	@Override
	public URI createURI(String path, String query, String fragment) {
		return super.createURI(path, query, fragment);
	}

	protected URI getURI(String path, String query) {
		return uri;
	}
}
