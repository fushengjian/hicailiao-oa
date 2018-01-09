package com.tomowork.oa.manage.sys.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Maps;

import com.tomowork.oa.manage.common.config.Global;
import com.tomowork.oa.manage.common.utils.CacheUtils;
import com.tomowork.oa.manage.common.utils.CookieUtils;
import com.tomowork.oa.manage.common.utils.StringUtils;
import com.tomowork.oa.manage.common.web.BaseController;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 登录Controller
 *
 * @version 2013-5-31
 */
@Controller
public class LoginController extends BaseController {

	/**
	 * 管理登录
	 */
	@RequestMapping (value = "${adminPath}/login.do", method = RequestMethod.GET)
	public String login(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ManageUser manageUser = UserUtils.getUser();
		// 如果已经登录，则跳转到管理首页
		if (manageUser.getId() != null) {
			return "redirect:" + Global.getAdminPath();
		}
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录失败，真正登录的POST请求由Filter完成
	 */
	@RequestMapping (value = "${adminPath}/login.do", method = RequestMethod.POST)
	public String login(
			@RequestParam (FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		ManageUser manageUser = UserUtils.getUser();
		// 如果已经登录，则跳转到管理首页
		if (manageUser.getId() != null) {
			return "redirect:" + Global.getAdminPath();
		}
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				username);
		model.addAttribute("isValidateCodeLogin",
				isValidateCodeLogin(username, true, false));
		return "modules/sys/sysLogin";
	}

	/**
	 * 登录成功，进入管理首页
	 */
	@RequiresUser
	@RequestMapping (value = {"${adminPath}/manage.do", ""})
	public String index(HttpServletRequest request, HttpServletResponse response) {
		ManageUser manageUser = UserUtils.getUser();
		// 未登录，则跳转到登录页
		if (manageUser.getId() == null) {
			return "redirect:" + Global.getAdminPath() + "/login";
		}
		// 登录成功后，验证码计算器清零
		isValidateCodeLogin(manageUser.getLoginName(), false, true);
		// 登录成功后，获取上次登录的当前站点ID
		UserUtils.putCache("siteId", CookieUtils.getCookie(request, "siteId"));
		return "modules/sys/sysIndex";
	}

	/**
	 * 权限不足时跳转页面
	 */
	@RequiresUser
	@RequestMapping (value = {"${adminPath}/403.do", ""})
	public String unauthorized(HttpServletRequest request, HttpServletResponse response) {
		if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			return "redirect:" + Global.getAdminPath() + "/ajax_403.do";
		}
		return "error/403";
	}

	/**
	 * ajax权限不足时跳转页面
	 */
	@RequiresUser
	@RequestMapping (value = {"${adminPath}/ajax_403.do"})
	public void ajaxUnauthorized(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.print("403-用户权限不足，请联系管理员");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取主题方案
	 */
	@RequestMapping (value = "/theme/{theme}.do")
	public String getThemeInCookie(@PathVariable String theme,
			HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isNotBlank(theme)) {
			CookieUtils.setCookie(response, "theme", theme);
		} else {
			theme = CookieUtils.getCookie(request, "theme");
		}
		return "redirect:" + request.getParameter("url");
	}

	/**
	 * 是否是验证码登录
	 *
	 * @param useruame
	 *            用户名
	 * @param isFail
	 *            计数加1
	 * @param clean
	 *            计数清零
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static boolean isValidateCodeLogin(String useruame, boolean isFail,
			boolean clean) {
		Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils
				.get("loginFailMap");
		if (loginFailMap == null) {
			loginFailMap = Maps.newHashMap();
			CacheUtils.put("loginFailMap", loginFailMap);
		}
		Integer loginFailNum = loginFailMap.get(useruame);
		if (loginFailNum == null) {
			loginFailNum = 0;
		}
		if (isFail) {
			loginFailNum++;
			loginFailMap.put(useruame, loginFailNum);
		}
		if (clean) {
			loginFailMap.remove(useruame);
		}
		return loginFailNum >= 3;
	}

	@RequestMapping ("${adminPath}/download.do")
	public String download(@RequestParam String filePath,
			HttpServletResponse response) {
		File file = new File(filePath);
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
			response.reset();
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ file.getName() + "\"");
			OutputStream outputStream = new BufferedOutputStream(
					response.getOutputStream());
			byte[] data = new byte[1024];
			while (inputStream.read(data, 0, 1024) >= 0) {
				outputStream.write(data);
			}
			outputStream.flush();
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
