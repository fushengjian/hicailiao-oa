package com.tomowork.oa.util;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * FactoryBean for BaseURIFactory
 *
 * @author zlei
 */
public class BaseURIFactoryBean implements FactoryBean<BaseURIFactory>, InitializingBean {
	private String uri;

	private BaseURIFactory uriFactory;

	@Override
	public BaseURIFactory getObject() throws Exception {
		return uriFactory;
	}

	@Override
	public Class<?> getObjectType() {
		return BaseURIFactory.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (uri == null)
			throw new IllegalStateException("uri not set");

		uriFactory = new BaseURIFactory(uri);
	}

	public String getUri() {
		return uri == null ? "" : uri;
	}

	public void setUri(String uri) {
		if (uri.isEmpty())
			throw new IllegalArgumentException("uri should not be empty");

		this.uri = uri;
	}
}
