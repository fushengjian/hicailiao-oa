package com.tomowork.oa.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tomowork.oa.core.security.support.SecurityUserHolder;
import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.foundation.service.SysConfigService;

@Component
public class ShopFilter implements Filter {

	@Autowired
	private SysConfigService configService;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		boolean redirect = false;
		String redirect_url = "";

		User user = SecurityUserHolder.getCurrentUser();
		if (user != null) {
			if (url.indexOf("/login.htm") >= 0) {
				redirect = true;
				redirect_url = CommUtil.getURL(request) + "/index.htm";
			}
			if (url.indexOf("/register.htm") >= 0) {
				redirect = true;
				redirect_url = CommUtil.getURL(request) + "/index.htm";
			}
		} else if (url.indexOf("/install") < 0) {
			redirect = false;
		} else {
			redirect_url = CommUtil.getURL(request) + "/index.htm";
			redirect = true;
		}
		if (redirect)
			response.sendRedirect(redirect_url);
		else
			chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {
	}
}
