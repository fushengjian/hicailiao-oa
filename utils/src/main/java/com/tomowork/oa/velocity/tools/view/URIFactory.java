package com.tomowork.oa.velocity.tools.view;

import java.net.URI;

/**
 * URIFactory
 *
 * @author zlei
 */
public interface URIFactory {
	URI createURI(String path, String query, String fragment);
}
