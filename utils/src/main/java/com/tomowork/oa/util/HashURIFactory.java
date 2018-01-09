package com.tomowork.oa.util;

import java.net.URI;
import java.util.ArrayList;

import com.tomowork.oa.velocity.tools.view.URIFactory;


/**
 * Choose base URI based on path?query#segment hash value.
 *
 * @author zlei
 */
public class HashURIFactory extends AbstractURIFactory implements URIFactory {
	private final URI[] uris;

	private final boolean hashQuery;

	public HashURIFactory(String... uris) {
		this(true, uris);
	}

	public HashURIFactory(boolean hashQuery, String... uris) {
		if (uris.length == 0)
			throw new IllegalArgumentException();

		ArrayList<URI> list = new ArrayList<>(uris.length);
		for (String uri : uris) {
			uri = uri.trim();
			if (uri.isEmpty())
				throw new IllegalArgumentException();

			list.add(URI.create(uri));
		}

		this.uris = list.toArray(new URI[list.size()]);
		this.hashQuery = hashQuery;
	}

	@Override
	public URI createURI(String path, String query, String fragment) {
		return super.createURI(path, query, fragment);
	}

	protected URI getURI(String path, String query) {
		if (uris.length > 1) {
			int hash = path != null ? path.hashCode() : 0;
			if (hashQuery && query != null) {
				hash ^= query.hashCode();
			}
			hash = hash >>> 16 ^ hash & 0xFFFF;

			return uris[hash % uris.length];
		} else {
			return uris[0];
		}
	}
}
