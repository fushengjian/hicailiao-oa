package com.tomowork.oa.manage.admin.action;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tomowork.oa.annotation.SecurityMapping;
import com.tomowork.oa.core.mv.JModelAndView;
import com.tomowork.oa.core.mv.ModelAndViewFactory;
import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.core.tools.database.DatabaseTools;
import com.tomowork.oa.velocity.tools.view.URIFactory;

@Controller
public class BaseManageAction {

	@Autowired
	private DatabaseTools databaseTools;

	@Autowired
	@Named("imageUriFactory")
	URIFactory uriFactory;

	@RequestMapping ({ "/login_error.htm" })
	public ModelAndView login_error(HttpServletRequest request, HttpServletResponse response) {
		String login_role = (String) request.getSession(false).getAttribute("login_role");
		JModelAndView mv;
		if (login_role == null)
			login_role = "user";
		if (login_role.equals("admin")) {
			mv = ModelAndViewFactory.createModelAndView("admin/blue/login_error.html", 2, request, response);
		} else {
			mv = ModelAndViewFactory.createModelAndView("error.html", 1, request, response);
			mv.addObject("url", CommUtil.getURL(request)
					+ "/login.htm");
		}
		mv.addObject("op_title", "登录失败");
		return mv;
	}

	/**
	 * 重定向到管理主页
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping ({ "/index.htm" })
	public void manage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(CommUtil.getURL(request) + "/manage.do");
	}

	@SecurityMapping (title = "欢迎页面", value = "/admin/welcome.htm*", rtype = "admin", rname = "欢迎页面", rcode = "admin_index", display = false, rgroup = "设置")
	@RequestMapping ({ "/admin/welcome.htm" })
	public ModelAndView welcome(HttpServletRequest request,
								HttpServletResponse response) {
		JModelAndView mv = ModelAndViewFactory.createModelAndView("admin/blue/welcome.html", 2, request, response);
		Properties props = System.getProperties();
		mv.addObject("os", props.getProperty("os.name"));
		mv.addObject("java_version", props.getProperty("java.version"));
		mv.addObject("shop_version", Integer.valueOf(20140301));
		mv.addObject("database_version",
				this.databaseTools.queryDatabaseVersion());
		mv.addObject("web_server_version", request.getSession(false)
				.getServletContext().getServerInfo());
		return mv;
	}

	@RequestMapping({ "/500.htm" })
	public ModelAndView error500(HttpServletRequest request, HttpServletResponse response) {
		return ModelAndViewFactory.createModelAndView("admin/blue/500.html", 2, request, response);
	}

	@RequestMapping({ "/404.htm" })
	public ModelAndView error404(HttpServletRequest request, HttpServletResponse response) {
		return ModelAndViewFactory.createModelAndView("admin/blue/404.html", 2, request, response);
	}
}
