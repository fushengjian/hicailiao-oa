package com.tomowork.oa.manage.admin.action;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tomowork.oa.core.mv.JModelAndView;
import com.tomowork.oa.core.mv.ModelAndViewFactory;
import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.core.tools.HttpClient;
import com.tomowork.oa.manage.common.config.Global;

@Controller
public class WorkAttendanceAction {

	private String access_token = getDingtalkToken();

	@RequiresPermissions ("punchInAndOut")
	@RequestMapping ({ "/admin/punchInAndOut.htm" })
	public ModelAndView punchInAndOut(HttpServletRequest request, HttpServletResponse response) {
		JModelAndView mv = ModelAndViewFactory.createModelAndView("admin/blue/punchInAndOut.html", 2, request, response);
		System.out.println(access_token);
		return mv;
	}

	private String getDingtalkToken() {
		String gettokenUrl = Global.getConfig("dingtalk.gettoken");
		Map<String, String> params = new HashMap<>();
		params.put("corpid", Global.getConfig("dingtalk.corpid"));
		params.put("corpsecret", Global.getConfig("dingtalk.corpSecret"));
		Map<String, String> header = new HashMap<>();
		header.put("Content-Type", "application/json");
		HttpClient httpClient = new HttpClient();
		Map result = httpClient.HttpGet(gettokenUrl, params, header);
		if (CommUtil.null2String(result.get("access_token")).isEmpty()) {
			return "";
		}
		return CommUtil.null2String(result.get("access_token"));
	}
}
