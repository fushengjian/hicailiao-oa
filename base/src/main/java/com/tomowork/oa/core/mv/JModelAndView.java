package com.tomowork.oa.core.mv;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.core.tools.HttpInclude;
import com.tomowork.oa.foundation.domain.SysConfig;
import com.tomowork.oa.mv.Model;
import com.tomowork.oa.view.web.tools.ImageViewTools;

public class JModelAndView extends ModelAndView implements Model {

	public JModelAndView(String viewName) {
		super.setViewName(viewName);
	}

	public JModelAndView(String viewName, SysConfig config,
			HttpServletRequest request, HttpServletResponse response) {

		String webPath = CommUtil.getURL(request);

		super.setViewName(viewName);
		//super.addObject("domainPath", CommUtil.generic_domain(request));

		super.addObject("webPath", webPath);
		super.addObject("config", config);
		//super.addObject("uconfig", uconfig);
		super.addObject("httpInclude", new HttpInclude(request, response));
		String query_url = "";
		if ((request.getQueryString() != null)
				&& (!(request.getQueryString().isEmpty()))) {
			query_url = "?" + request.getQueryString();
		}
		super.addObject("current_url", request.getRequestURI() + query_url);

		super.addObject("imageWebServer", webPath);

		super.addObject("imageViewTools", ImageViewTools.getInstance());

		String miscWebServer = config.getMiscWebServer();
		if (miscWebServer == null || miscWebServer.trim().isEmpty()) {
			miscWebServer = webPath;
		}
		String chaturl = config.getChaturl();
		super.addObject("chaturl", chaturl);
		//传给SiteMesh用 by gaogh 2016/01/12
		request.setAttribute("miscWebServer", miscWebServer);
		request.setAttribute("gobalConfig", config);
		//end by gaogh
		super.addObject("miscWebServer", miscWebServer);
	}

	public JModelAndView(String viewName, SysConfig config,
			int type, HttpServletRequest request, HttpServletResponse response) {
		if (config.getSysLanguage() != null) {
			if (config.getSysLanguage().equals("zh_cn")) {
				if (type == 0) { // 前端用户中心
					super.setViewName("WEB-INF/templates/zh_cn/system_v2/"
							+ viewName);
				} else if (type == 1) { // 前端
					super.setViewName("WEB-INF/templates/zh_cn/shop_v2/"
							+ viewName);
				} else if (type == 2) { // 管理台
					super.setViewName("WEB-INF/templates/zh_cn/system/"
							+ viewName);
				} else
					super.setViewName(viewName);
			} else {
				if (type == 0) {
					super.setViewName("WEB-INF/templates/"
							+ config.getSysLanguage() + "/system_v2/" + viewName);
				} else if (type == 1) {
					super.setViewName("WEB-INF/templates/"
							+ config.getSysLanguage() + "/shop_v2/" + viewName);
				} else if (type == 2) {
					super.setViewName("WEB-INF/templates/"
							+ config.getSysLanguage() + "/system/" + viewName);
				} else
					super.setViewName(viewName);
			}
		} else {
			super.setViewName(viewName);
		}
		super.addObject("CommUtil", CommUtil.getInstance());

		String webPath = CommUtil.getURL(request);

		//super.addObject("domainPath", CommUtil.generic_domain(request));
		super.addObject("webPath", webPath);

		super.addObject("config", config);
		//super.addObject("uconfig", uconfig);
		super.addObject("httpInclude", new HttpInclude(request, response));
		String query_url = "";
		if ((request.getQueryString() != null)
				&& (!(request.getQueryString().isEmpty()))) {
			query_url = "?" + request.getQueryString();
		}
		super.addObject("current_url", request.getRequestURI() + query_url);

		super.addObject("imageWebServer", webPath);

		super.addObject("imageViewTools", ImageViewTools.getInstance());

		// 静态资源 by hzl 2015/8/15
		String miscWebServer = config.getMiscWebServer();
		if (miscWebServer == null || miscWebServer.trim().isEmpty()) {
			miscWebServer = webPath;
		}
		String chaturl = config.getChaturl();
		super.addObject("chaturl", chaturl);
		//传给SiteMesh用 by gaogh 2016/01/12
		request.setAttribute("miscWebServer", miscWebServer);
		request.setAttribute("gobalConfig", config);
		//end by gaogh

		super.addObject("miscWebServer", miscWebServer);
	}

	@Override
	public JModelAndView addObject(String attributeName, Object attributeValue) {
		super.addObject(attributeName, attributeValue);
		return this;
	}

	@Override
	public JModelAndView addObject(Object attributeValue) {
		addObject(attributeValue);
		return this;
	}

	@Override
	public JModelAndView addAllObjects(Map<String, ?> modelMap) {
		super.addAllObjects(modelMap);
		return this;
	}
}
