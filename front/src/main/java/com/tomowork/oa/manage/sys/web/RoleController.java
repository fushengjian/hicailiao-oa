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
import com.tomowork.oa.manage.sys.entity.ManageRole;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.entity.Office;
import com.tomowork.oa.manage.sys.service.OfficeService;
import com.tomowork.oa.manage.sys.service.SystemService;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 角色Controller
 */
@Controller
@RequestMapping (value = "${adminPath}/sys/role")
public class RoleController extends BaseController {

	@Autowired
	private SystemService systemService;

	@Autowired
	private OfficeService officeService;

	@ModelAttribute ("manageRole")
	public ManageRole get(@RequestParam (required = false) Long id) {
		if (id != null && id.longValue() > 0) {
			return systemService.getRole(id);
		} else {
			return new ManageRole();
		}
	}

	@RequiresPermissions("sys:role:view")
	@RequestMapping (value = {"list.do", ""})
	public String list(ManageRole manageRole, Model model) {
		List<ManageRole> list = systemService.findAllRole();
		model.addAttribute("list", list);
		return "modules/sys/roleList";
	}

	@RequiresPermissions("sys:role:view")
	@RequestMapping (value = "form")
	public String form(ManageRole manageRole, Model model) {
		if (manageRole.getOffice() == null) {
			manageRole.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("manageRole", manageRole);
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/roleForm";
	}

	@RequiresPermissions("sys:role:edit")
	@RequestMapping (value = "save.do")
	public String save(ManageRole manageRole, Model model, String oldName, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			return "redirect:" + Global.getAdminPath() + "/sys/role/list.do?repage";
		}
		if (!beanValidator(model, manageRole)) {
			return form(manageRole, model);
		}
		if (!"true".equals(checkName(oldName, manageRole.getName()))) {
			addMessage(model, "保存角色'" + manageRole.getName() + "'失败, 角色名已存在");
			return form(manageRole, model);
		}
		systemService.saveRole(manageRole);
		addMessage(redirectAttributes, "保存角色'" + manageRole.getName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/sys/role/list.do?repage";
	}

	@RequiresPermissions("sys:role:edit")
	@RequestMapping (value = "delete")
	public String delete(@RequestParam Long id, RedirectAttributes redirectAttributes) {

		if (ManageRole.isAdmin(id)) {
			addMessage(redirectAttributes, "删除角色失败, 不允许内置角色或编号空");
		} else {
			systemService.deleteRole(id);
			addMessage(redirectAttributes, "删除角色成功");
		}
		return "redirect:" + Global.getAdminPath() + "/sys/role/list.do?repage";
	}

	@RequiresPermissions("sys:role:edit")
	@RequestMapping (value = "assign.do")
	public String assign(ManageRole manageRole, Model model) {
		List<ManageUser> manageUsers = manageRole.getUserList();
		model.addAttribute("users", manageUsers);
		return "modules/sys/roleAssign";
	}

	@RequiresPermissions("sys:role:view")
	@RequestMapping (value = "usertorole.do")
	public String selectUserToRole(ManageRole manageRole, Model model) {
		model.addAttribute("role", manageRole);
		model.addAttribute("selectIds", manageRole.getUserIds());
		model.addAttribute("officeList", officeService.findAll());
		return "modules/sys/selectUserToRole";
	}

	@RequiresPermissions("sys:role:view")
	@ResponseBody
	@RequestMapping (value = "users.do")
	public List<Map<String, Object>> users(Long officeId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		Office office = officeService.get(officeId);
		List<ManageUser> userList = office.getUserList();
		for (ManageUser manageUser : userList) {
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", manageUser.getId());
			map.put("pId", 0);
			map.put("name", manageUser.getName());
			mapList.add(map);
		}
		return mapList;
	}

	@RequiresPermissions("sys:role:edit")
	@RequestMapping (value = "outrole.do")
	public String outrole(Long userId, Long roleId, RedirectAttributes redirectAttributes) {
		ManageRole manageRole = systemService.getRole(roleId);
		ManageUser manageUser = systemService.getUser(userId);
		if (manageUser.equals(UserUtils.getUser())) {
			addMessage(redirectAttributes, "无法从角色【" + manageRole.getName() + "】中移除用户【" + manageUser.getName() + "】自己！");
		} else {
			Boolean flag = systemService.outUserInRole(manageRole, userId);
			if (!flag) {
				addMessage(redirectAttributes, "用户【" + manageUser.getName() + "】从角色【" + manageRole.getName() + "】中移除失败！");
			} else {
				addMessage(redirectAttributes, "用户【" + manageUser.getName() + "】从角色【" + manageRole.getName() + "】中移除成功！");
			}
		}
		return "redirect:" + Global.getAdminPath() + "/sys/role/assign.do?id=" + manageRole.getId();
	}

	@RequiresPermissions("sys:role:edit")
	@RequestMapping (value = "assignrole.do")
	public String assignRole(ManageRole manageRole, String[] idsArr, RedirectAttributes redirectAttributes) {
		StringBuilder msg = new StringBuilder();
		int newNum = 0;
		for (int i = 0; i < idsArr.length; i++) {
			ManageUser manageUser = systemService.assignUserToRole(manageRole, idsArr[i]);
			if (null != manageUser) {
				msg.append("<br/>新增用户【" + manageUser.getName() + "】到角色【" + manageRole.getName() + "】！");
				newNum++;
			}
		}
		addMessage(redirectAttributes, "已成功分配 " + newNum + " 个用户" + msg);
		return "redirect:" + Global.getAdminPath() + "/sys/role/assign.do?id=" + manageRole.getId();
	}

	@RequiresUser
	@ResponseBody
	@RequestMapping (value = "checkName.do")
	public String checkName(String oldName, String name) {
		if (name != null && name.equals(oldName)) {
			return "true";
		} else if (name != null && systemService.findRoleByName(name) == null) {
			return "true";
		}
		return "false";
	}

}
