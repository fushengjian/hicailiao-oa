package com.tomowork.oa.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * FactoryBean for HashURIFactory.
 *
 * @author zlei
 */
public class HashURIFactoryBean implements FactoryBean<HashURIFactory>, InitializingBean {
	private String[] uris;

	private boolean hashQuery = true;

	private HashURIFactory uriFactory;

	@Override
	public HashURIFactory getObject() throws Exception {
		return uriFactory;
	}

	@Override
	public Class<?> getObjectType() {
		return HashURIFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (uris == null)
			throw new IllegalStateException("uris not set");

		uriFactory = new HashURIFactory(hashQuery, uris);
	}

	public String getUris() {
		if (uris == null)
			return "";
		else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < uris.length - 1; i++) {
				sb.append(uris[i]);
				sb.append(',');
				sb.append(' ');
			}
			sb.append(uris[uris.length - 1]);

			return sb.toString();
		}
	}

	public void setUris(String uris) {
		if (uris.isEmpty())
			throw new IllegalArgumentException("uris should not be empty");

		String[] s = parse(uris);
		if (s.length == 0)
			throw new IllegalArgumentException("invalid uris: " + uris);

		this.uris = s;
	}

	public boolean isHashQuery() {
		return hashQuery;
	}

	public void setHashQuery(boolean hashQuery) {
		this.hashQuery = hashQuery;
	}

	private String[] parse(String uris) {
		String[] sa = uris.split(",");
		List<String> list = new ArrayList<>(sa.length);
		for (String s : sa) {
			if (s != null) {
				s = s.trim();
				if (!s.isEmpty())
					list.add(s);
			}
		}
		return list.toArray(new String[list.size()]);
	}
}
