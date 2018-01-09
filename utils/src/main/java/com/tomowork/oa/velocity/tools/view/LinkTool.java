package com.tomowork.oa.velocity.tools.view;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.tools.generic.ValueParser;


/**
 * LinkTool
 *
 * @author zlei
 */
public class LinkTool extends org.apache.velocity.tools.view.LinkTool {
	protected URIFactory uriFactory;

	@Override
	protected void configure(ValueParser props) {
		String uriFactoryKey = props.getString("uriFactoryKey");
		if (uriFactoryKey != null) {
			HttpServletRequest request = (HttpServletRequest)props.getValue("request");
			uriFactory = getURIFactory(request, uriFactoryKey);

			if (uriFactory == null) {
				debug("Could not find uriFactory with uriFactoryKey %s", uriFactoryKey);
			}
		}

		super.configure(props);

		if (uriFactory != null) {
			// fix super.configure setPath from requestPath
			setPath(null);
		}
	}

	@Override
	protected void setFromRequest(HttpServletRequest request) {
		if (uriFactory == null) {
			super.setFromRequest(request);
		}
	}

	@Override
	public org.apache.velocity.tools.view.LinkTool addRequestParams(String... butOnlyThese) {
		if (uriFactory != null) {
			return this;
		} else {
			return super.addRequestParams(butOnlyThese);
		}
	}

	@Override
	public org.apache.velocity.tools.view.LinkTool addRequestParamsExcept(String... ignoreThese) {
		if (uriFactory != null) {
			return this;
		} else {
			return super.addRequestParamsExcept(ignoreThese);
		}
	}

	@Override
	public org.apache.velocity.tools.view.LinkTool addMissingRequestParams(String... ignoreThese) {
		if (uriFactory != null) {
			return this;
		} else {
			return super.addMissingRequestParams(ignoreThese);
		}
	}

	@Override
	protected boolean isPathChanged() {
		return uriFactory != null || super.isPathChanged();
	}

	@Override
	public String getContextPath() {
		return uriFactory != null ? null : super.getContextPath();
	}

	@Override
	protected URI createURI() {
		if (uriFactory != null) {
			try {
				return uriFactory.createURI(getPath(), getQuery(), getAnchor());
			} catch (Exception e) {
				debug("Could not create URI", e);
			}
			return null;
		} else {
			return super.createURI();
		}
	}

	@Override
	public String toString() {
		if (uriFactory != null) {
			URI uri = createURI();
			return uri != null ? (this.query != null ? decodeQueryPercents(uri.toString()) : uri.toString()) : null;
		} else {
			return super.toString();
		}
	}

	private URIFactory getURIFactory(HttpServletRequest request, String uriFactoryKey) {
		URIFactory factory = null;
		try {
			factory = (URIFactory) request.getAttribute(uriFactoryKey);
		} catch (ClassCastException e) {
			LOG.warn("LinkTool: error get uriFactory", e);
		}

		if (factory == null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				try {
					factory = (URIFactory) session.getAttribute(uriFactoryKey);
				} catch (ClassCastException e) {
					LOG.warn("LinkTool: error get uriFactory", e);
				}
			}
		}

		if (factory == null) {
			try {
				factory = (URIFactory) request.getServletContext().getAttribute(uriFactoryKey);
			} catch (ClassCastException e) {
				LOG.warn("LinkTool: error get uriFactory", e);
			}
		}

		return factory;
	}
}
