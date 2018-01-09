/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.tomowork.oa.manage.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tomowork.oa.manage.common.config.Global;
import com.tomowork.oa.manage.common.web.BaseController;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.entity.Office;
import com.tomowork.oa.manage.sys.service.OfficeService;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 机构Controller
 * @author ThinkGem
 * @version 2013-5-15
 */
@Controller
@RequestMapping (value = "${adminPath}/sys/office")
public class OfficeController extends BaseController {

	@Autowired
	private OfficeService officeService;

	@ModelAttribute ("office")
	public Office get(@RequestParam (required = false) Long id) {
		if (id != null) {
			return officeService.get(id);
		} else {
			return new Office();
		}
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping (value = {"list.do", ""})
	public String list(Office office, Model model) {
		office.setId(1L);
		model.addAttribute("office", office);
		List<Office> list = Lists.newArrayList();
		List<Office> sourcelist = officeService.findAll();
		Office.sortList(list, sourcelist, office.getId());
		model.addAttribute("list", list);
		return "modules/sys/officeList";
	}

	@RequiresPermissions("sys:office:view")
	@RequestMapping (value = "form.do")
	public String form(Office office, Model model) {
		ManageUser manageUser = UserUtils.getUser();
		if (office.getParent() == null || office.getParent().getId() == null) {
			office.setParent(manageUser.getOffice());
		}
		office.setParent(officeService.get(office.getParent().getId()));
		if (office.getArea() == null) {
			office.setArea(office.getParent().getArea());
		}
		model.addAttribute("office", office);
		return "modules/sys/officeForm";
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping (value = "save")
	public String save(Office office, Model model, RedirectAttributes redirectAttributes) {

		if (!beanValidator(model, office)) {
			return form(office, model);
		}
		officeService.save(office);
		addMessage(redirectAttributes, "保存机构'" + office.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/sys/office/list.do";
	}

	@RequiresPermissions("sys:office:edit")
	@RequestMapping (value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {

		if (Office.isRoot(id)) {
			addMessage(redirectAttributes, "删除机构失败, 不允许删除顶级机构或编号空");
		} else {
			officeService.delete(id);
			addMessage(redirectAttributes, "删除机构成功");
		}
		return "redirect:" + Global.getAdminPath() + "/sys/office/list.do";
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping (value = "treeData.do")
	public List<Map<String, Object>> treeData(@RequestParam (required = false) Long extId, @RequestParam (required = false) Long type,
											  @RequestParam (required = false) Long grade, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
//		ManageUser user = UserUtils.getUser();
		List<Office> list = officeService.findAll();
		for (int i = 0; i < list.size(); i++) {
			Office e = list.get(i);
			if ((extId == null || (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && Integer.parseInt(e.getType()) <= type.intValue()))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
//				map.put("pId", !user.isAdmin() && e.getId().equals(user.getOffice().getId())?0:e.getParent() != null?e.getParent().getId():0);
				map.put("pId", e.getParent() != null ? e.getParent().getId() : 0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
