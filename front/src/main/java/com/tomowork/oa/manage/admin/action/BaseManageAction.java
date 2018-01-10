package com.tomowork.oa.manage.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.tomowork.oa.annotation.Log;
import com.tomowork.oa.annotation.LogType;
import com.tomowork.oa.annotation.SecurityMapping;
import com.tomowork.oa.core.mv.JModelAndView;
import com.tomowork.oa.core.mv.ModelAndViewFactory;
import com.tomowork.oa.core.security.support.SecurityUserHolder;
import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.core.tools.database.DatabaseTools;
import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.foundation.service.SysConfigService;
import com.tomowork.oa.foundation.service.UserService;
import com.tomowork.oa.velocity.tools.view.URIFactory;

@Controller
public class BaseManageAction {

	@Autowired
	private SysConfigService configService;

	@Autowired
	private UserService userService;

	@Autowired
	private DatabaseTools databaseTools;

	@Autowired
	@Named("imageUriFactory")
	URIFactory uriFactory;

	@Log (title = "用户登陆", type = LogType.LOGIN)
	@RequestMapping ({ "/login_success.htm" })
	public void login_success(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = this.userService.getObjById(SecurityUserHolder
				.getCurrentUser().getId());

		user.setLoginDate(new Date());
		user.setLoginIp(CommUtil.getIpAddr(request));
		user.setLoginCount(user.getLoginCount() + 1);
		this.userService.update(user);
		HttpSession session = request.getSession(false);
		session.setAttribute("user", user);
		session.setAttribute("lastLoginDate", new Date());
		session.setAttribute("loginIp", CommUtil.getIpAddr(request));
		session.setAttribute("login", Boolean.valueOf(true));
		String role = user.getUserRole();
		String url = CommUtil.getURL(request) + "/user_login_success.htm";
		String login_role = (String) session.getAttribute("login_role");

		boolean ajax_login = CommUtil.null2Boolean(session
				.getAttribute("ajax_login"));
		if (ajax_login) {
			response.setContentType("text/plain");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			try {
				PrintWriter writer = response.getWriter();
				writer.print("success");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if ((login_role.equals("admin")) && (role.indexOf("ADMIN") >= 0)) {
				url = CommUtil.getURL(request) + "/admin/index.htm";
				request.getSession(false).setAttribute("admin_login",
						Boolean.valueOf(true));
			}

			response.sendRedirect(url);
		}
	}

	@RequestMapping ({ "/logout_success.htm" })
	public void logout_success(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);
		String targetUrl = CommUtil.getURL(request) + "/login.htm";

		// xluo 当是后台管理用户退出时 跳转到后台管理用户登录页面
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUserRole().equalsIgnoreCase("ADMIN")) {
			targetUrl = CommUtil.getURL(request) + "/admin/login.htm";
		}

		session.removeAttribute("user");
		session.removeAttribute("login");
		session.removeAttribute("role");
		session.removeAttribute("cart");
		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
		.getRequest().getSession(false).removeAttribute("user");

		if (this.configService.getSysConfig().isSecond_domain_open()) {
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("tomowork_user_session")) {
					cookie.setMaxAge(0);
					cookie.setValue("");
					cookie.setDomain(CommUtil.generic_domain(request));
					response.addCookie(cookie);
				}
			}
		}
		response.sendRedirect(targetUrl);
	}

	@RequestMapping ({ "/login_error.htm" })
	public ModelAndView login_error(HttpServletRequest request,
									HttpServletResponse response) {
		String login_role = (String) request.getSession(false).getAttribute(
				"login_role");
		JModelAndView mv = null;
		String tomowork_view_type = CommUtil.null2String(request.getSession(
				false).getAttribute("tomowork_view_type"));
		if ((tomowork_view_type != null) && (!(tomowork_view_type.equals("")))) {
			if (tomowork_view_type.equals("weixin")) {
				String store_id = CommUtil.null2String(request
						.getSession(false).getAttribute("store_id"));
				mv = ModelAndViewFactory.createModelAndView("weixin/error.html", 1, request, response);
				mv.addObject("url", CommUtil.getURL(request)
						+ "/weixin/index.htm?store_id=" + store_id);
			}
		} else {
			if (login_role == null)
				login_role = "user";
			if (login_role.equals("admin")) {
				mv = ModelAndViewFactory.createModelAndView("admin/blue/login_error.html", 2, request, response);
			} else {
				mv = ModelAndViewFactory.createModelAndView("error.html", 1, request, response);
				mv.addObject("url", CommUtil.getURL(request)
						+ "/login.htm");
			}
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
	public ModelAndView error500(HttpServletRequest request,
			HttpServletResponse response) {
		JModelAndView mv = ModelAndViewFactory.createModelAndView("admin/blue/500.html", 2, request, response);
		HttpSession session = request.getSession(false);
		String tomowork_view_type = session != null ? CommUtil
				.null2String(session.getAttribute("tomowork_view_type")) : null;
		if ((tomowork_view_type != null) && (!(tomowork_view_type.equals("")))
				&& (tomowork_view_type.equals("weixin"))) {
			String store_id = CommUtil.null2String(request.getSession(false)
					.getAttribute("store_id"));
			mv = ModelAndViewFactory.createModelAndView("weixin/500.html", 1, request, response);
			mv.addObject("url", CommUtil.getURL(request)
					+ "/weixin/index.htm?store_id=" + store_id);
		}
		return mv;
	}
}
