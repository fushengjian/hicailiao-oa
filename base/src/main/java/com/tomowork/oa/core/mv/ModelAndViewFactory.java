package com.tomowork.oa.core.mv;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.foundation.service.SysConfigService;

@Named
public class ModelAndViewFactory {
	private static ModelAndViewFactory instance;

	@Inject
	private SysConfigService configService;


	public static JModelAndView createModelAndView(String viewName) {
		return new JModelAndView(viewName);
	}

	public static JModelAndView createModelAndView(String viewName, HttpServletRequest request, HttpServletResponse response) {
		return instance._getModelAndView(viewName, request, response);
	}

	public static JModelAndView createModelAndView(String viewName, int type, HttpServletRequest request, HttpServletResponse response) {
		return instance._getModelAndView(viewName, type, request, response);
	}

	/**
	 * 返回异常访问页面
	 *
	 * @param message
	 * @param request
	 * @param response
	 * @return
	 */
	public static JModelAndView createErrorView(String message, HttpServletRequest request, HttpServletResponse response) {
		JModelAndView mv = instance._getModelAndView("error.html", 1, request, response);
		mv.addObject("op_title", message);
		mv.addObject("url", CommUtil.getURL(request) + "/index.htm"); //默认返回首页
		return mv;
	}

	private JModelAndView _getModelAndView(String viewName, HttpServletRequest request, HttpServletResponse response) {
		return new JModelAndView(viewName, configService.getSysConfig(), request, response);
	}

	private JModelAndView _getModelAndView(String viewName, int type, HttpServletRequest request, HttpServletResponse response) {
		return new JModelAndView(viewName, configService.getSysConfig(), type, request, response);
	}

	@PostConstruct
	public void postConstruct() {
		instance = this;
	}
}
