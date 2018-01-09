package com.tomowork.oa.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession(true);
		if (log.isTraceEnabled()) {
			log.trace("sessionId: {}", session.getId());
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}
