package com.tomowork.oa.manage.sys.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.tomowork.oa.manage.common.config.Global;
import com.tomowork.oa.manage.common.persistence.Page;
import com.tomowork.oa.manage.common.utils.StringUtils;
import com.tomowork.oa.manage.common.web.BaseController;
import com.tomowork.oa.manage.sys.entity.ManageRole;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.entity.Office;
import com.tomowork.oa.manage.sys.service.SystemService;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 用户Controller
 * @author ThinkGem
 * @version 2013-5-31
 */
@Controller
@RequestMapping (value = "${adminPath}/sys/user")
public class ManageUserController extends BaseController {

	@Autowired
	private SystemService systemService;

	@ModelAttribute
	public ManageUser get(@RequestParam (required = false) Long id) {
		if (id != null && id.longValue() > 0) {
			return systemService.getUser(id);
		} else {
			return new ManageUser();
		}
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping (value = {"list.do", ""})
	public String list(ManageUser manageUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ManageUser> page = systemService.findUser(new Page<ManageUser>(request, response), manageUser);
		model.addAttribute("page", page);
		return "modules/sys/userList";
	}

	@RequiresPermissions("sys:user:view")
	@RequestMapping (value = "form.do")
	public String form(ManageUser manageUser, Model model) {
		if (manageUser.getCompany() == null || manageUser.getCompany().getId() == null) {
			manageUser.setCompany(UserUtils.getUser().getCompany());
		}
		if (manageUser.getOffice() == null || manageUser.getOffice().getId() == null) {
			manageUser.setOffice(UserUtils.getUser().getOffice());
		}

		//判断显示的用户是否在授权范围内
		Long officeId = manageUser.getOffice().getId();
		ManageUser currentUser = UserUtils.getUser();
		if (!currentUser.isAdmin()) {
			String dataScope = systemService.getDataScope(currentUser);
			//System.out.println(dataScope);
			if (dataScope.indexOf("office.id=") != -1) {
				String AuthorizedOfficeId = dataScope.substring(dataScope.indexOf("office.id=") + 10, dataScope.indexOf(" or"));
				if (!AuthorizedOfficeId.equalsIgnoreCase(officeId.toString())) {
					return "error/403";
				}
			}
		}

		model.addAttribute("user", manageUser);
		model.addAttribute("allRoles", systemService.findAllRole());
		return "modules/sys/userForm";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping (value = "save.do")
	public String save(ManageUser manageUser, String oldLoginName, String newPassword, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/sys/user/list.do?repage";
		}
		// 修正引用赋值问题，不知道为何，Company和Office引用的一个实例地址，修改了一个，另外一个跟着修改。
		manageUser.setCompany(new Office(new Long(request.getParameter("company.id"))));
		manageUser.setOffice(new Office(new Long(request.getParameter("office.id"))));
		// 如果新密码为空，则不更换密码
		if (StringUtils.isNotBlank(newPassword)) {
			manageUser.setPassword(SystemService.entryptPassword(newPassword));
		}
		if (!beanValidator(model, manageUser)) {
			return form(manageUser, model);
		}
		if (!"true".equals(checkLoginName(oldLoginName, manageUser.getLoginName()))) {
			addMessage(model, "保存用户'" + manageUser.getLoginName() + "'失败，登录名已存在");
			return form(manageUser, model);
		}
		// 角色数据有效性验证，过滤不在授权内的角色
		List<ManageRole> roleList = Lists.newArrayList();
		List<Long> roleIdList = manageUser.getRoleIdList();
		for (ManageRole r : systemService.findAllRole()) {
			if (roleIdList.contains(r.getId())) {
				roleList.add(r);
			}
		}
		manageUser.setRoleList(roleList);
		// 保存用户信息
		systemService.saveUser(manageUser);
		// 清除当前用户缓存
		if (manageUser.getLoginName().equals(UserUtils.getUser().getLoginName())) {
			UserUtils.getCacheMap().clear();
		}
		addMessage(redirectAttributes, "保存用户'" + manageUser.getLoginName() + "'成功");
		return "redirect:" + Global.getAdminPath() + "/sys/user/list.do?repage";
	}

	@RequiresPermissions("sys:user:edit")
	@RequestMapping (value = "delete")
	public String delete(Long id, RedirectAttributes redirectAttributes) {
		if (Global.isDemoMode()) {
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + Global.getAdminPath() + "/sys/user/?repage";
		}
		if (UserUtils.getUser().getId().equals(id)) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除当前用户");
		} else if (ManageUser.isAdmin(id)) {
			addMessage(redirectAttributes, "删除用户失败, 不允许删除超级管理员用户");
		} else {
			systemService.deleteUser(id);
			addMessage(redirectAttributes, "删除用户成功");
		}
		return "redirect:" + Global.getAdminPath() + "/sys/user/list.do?repage";
	}


	@ResponseBody
	@RequiresPermissions("sys:user:edit")
	@RequestMapping (value = "checkLoginName.do")
	public String checkLoginName(String oldLoginName, String loginName) {
		if (loginName != null && loginName.equals(oldLoginName)) {
			return "true";
		} else if (loginName != null && systemService.getUserByLoginName(loginName) == null) {
			return "true";
		}
		return "false";
	}

	@RequiresUser
	@RequestMapping (value = "info.do")
	public String info(ManageUser manageUser, Model model) {
		ManageUser currentUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(manageUser.getName())) {
			currentUser = UserUtils.getUser(true);
			currentUser.setEmail(manageUser.getEmail());
			currentUser.setPhone(manageUser.getPhone());
			currentUser.setMobile(manageUser.getMobile());
			currentUser.setRemarks(manageUser.getRemarks());
			systemService.saveUser(currentUser);
			model.addAttribute("message", "保存用户信息成功");
		}
		model.addAttribute("manageUser", currentUser);
		return "modules/sys/userInfo";
	}

	@RequiresUser
	@RequestMapping (value = "modifyPwd.do")
	public String modifyPwd(String oldPassword, String newPassword, Model model) {
		ManageUser manageUser = UserUtils.getUser();
		if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)) {
			if (Global.isDemoMode()) {
				model.addAttribute("message", "演示模式，不允许操作！");
				return "modules/sys/userModifyPwd";
			}
			if (SystemService.validatePassword(oldPassword, manageUser.getPassword())) {
				systemService.updatePasswordById(manageUser.getId(), manageUser.getLoginName(), newPassword);
				model.addAttribute("message", "修改密码成功");
			} else {
				model.addAttribute("message", "修改密码失败，旧密码错误");
			}
		}
		model.addAttribute("user", manageUser);
		return "modules/sys/userModifyPwd";
	}

}
