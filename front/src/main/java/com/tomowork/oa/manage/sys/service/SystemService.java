/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.tomowork.oa.manage.sys.service;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.manage.common.persistence.Page;
import com.tomowork.oa.manage.common.security.Digests;
import com.tomowork.oa.manage.common.service.BaseService;
import com.tomowork.oa.manage.common.utils.Encodes;
import com.tomowork.oa.manage.common.utils.StringUtils;
import com.tomowork.oa.manage.sys.dao.ManageRoleDao;
import com.tomowork.oa.manage.sys.dao.ManageUserDao;
import com.tomowork.oa.manage.sys.dao.MenuDao;
import com.tomowork.oa.manage.sys.entity.ManageRole;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.entity.Menu;
import com.tomowork.oa.manage.sys.security.SystemAuthorizingRealm;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 系统管理，安全相关实体的管理类,包括用户、角色、菜单.
 */
@Service
@Transactional (readOnly = true)
public class SystemService extends BaseService {

	public static final String HASH_ALGORITHM = "SHA-1";

	public static final int HASH_INTERATIONS = 1024;

	public static final int SALT_SIZE = 8;

	@Autowired
	private ManageUserDao manageUserDao;

	@Autowired
	private ManageRoleDao manageRoleDao;

	@Autowired
	private MenuDao menuDao;

	@Autowired
	private SystemAuthorizingRealm systemRealm;

	// -- ManageUser Service --//

	public ManageUser getUser(Long id) {
		return manageUserDao.get(id);
	}

	public Page<ManageUser> findUser(Page<ManageUser> page, ManageUser manageUser) {
		DetachedCriteria dc = manageUserDao.createDetachedCriteria();
		ManageUser currentUser = UserUtils.getUser();
		dc.createAlias("company", "company");
		if (manageUser.getCompany() != null
				&& manageUser.getCompany().getId() != null) {
			dc.add(Restrictions.or(
					Restrictions.eq("company.id", manageUser.getCompany().getId()),
					Restrictions.like("company.parentIds", "%,"
							+ manageUser.getCompany().getId() + ",%")));
		}
		dc.createAlias("office", "office");
		if (manageUser.getOffice() != null
				&& manageUser.getOffice().getId() != null) {
			dc.add(Restrictions.or(
					Restrictions.eq("office.id", manageUser.getOffice().getId()),
					Restrictions.like("office.parentIds", "%,"
							+ manageUser.getOffice().getId() + ",%")));
		}
		// 如果不是超级管理员，则不显示超级管理员用户
		if (!currentUser.isAdmin()) {
			dc.add(Restrictions.ne("id", "1"));
		}
		dc.add(dataScopeFilter(currentUser, "office", ""));
		// System.out.println(dataScopeFilterString(currentUser, "office", ""));
		if (StringUtils.isNotEmpty(manageUser.getLoginName())) {
			dc.add(Restrictions.like("loginName", "%" + manageUser.getLoginName()
					+ "%"));
		}
		if (StringUtils.isNotEmpty(manageUser.getName())) {
			dc.add(Restrictions.like("name", "%" + manageUser.getName() + "%"));
		}
		dc.add(Restrictions.eq(ManageUser.FIELD_DEL_FLAG, ManageUser.DEL_FLAG_NORMAL));
		if (!StringUtils.isNotEmpty(page.getOrderBy())) {
			dc.addOrder(Order.asc("company.code"))
					.addOrder(Order.asc("office.code"))
					.addOrder(Order.desc("name"));
		}
		return manageUserDao.find(page, dc);
	}

	// 取用户的数据范围
	public String getDataScope(ManageUser manageUser) {
		return dataScopeFilterString(manageUser, "office", "");
	}

	public ManageUser getUserByLoginName(String loginName) {
		return manageUserDao.findByLoginName(loginName);
	}

	@Transactional (readOnly = false)
	public void saveUser(ManageUser manageUser) {
		manageUserDao.clear();
		manageUserDao.save(manageUser);
		systemRealm.clearAllCachedAuthorizationInfo();
	}

	@Transactional (readOnly = false)
	public void deleteUser(Long id) {
		manageUserDao.deleteById(id);
	}

	@Transactional (readOnly = false)
	public void updatePasswordById(Long id, String loginName,
			String newPassword) {
		manageUserDao.updatePasswordById(entryptPassword(newPassword), id);
		systemRealm.clearCachedAuthorizationInfo(loginName);
	}

	@Transactional (readOnly = false)
	public void updateUserLoginInfo(Long id) {
		manageUserDao.updateLoginInfo(SecurityUtils.getSubject().getSession()
				.getHost(), new Date(), id);
	}

	/**
	 * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
	}

	/**
	 * 验证密码
	 *
	 * @param plainPassword
	 *            明文密码
	 * @param password
	 *            密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String password) {
		byte[] salt = Encodes.decodeHex(password.substring(0, 16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt,
				HASH_INTERATIONS);
		return password.equals(Encodes.encodeHex(salt)
				+ Encodes.encodeHex(hashPassword));
	}

	// -- Role Service --//

	public ManageRole getRole(Long id) {
		return manageRoleDao.get(id);
	}

	public ManageRole findRoleByName(String name) {
		return manageRoleDao.findByName(name);
	}

	public List<ManageRole> findAllRole() {
		return UserUtils.getRoleList();
	}

	@Transactional (readOnly = false)
	public void saveRole(ManageRole manageRole) {
		manageRoleDao.clear();
		manageRoleDao.save(manageRole);
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
	}

	@Transactional (readOnly = false)
	public void deleteRole(Long id) {
		manageRoleDao.deleteById(id);
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
	}

	@Transactional (readOnly = false)
	public Boolean outUserInRole(ManageRole manageRole, Long userId) {
		ManageUser manageUser = manageUserDao.get(userId);
		List<Long> roleIds = manageUser.getRoleIdList();
		List<ManageRole> manageRoles = manageUser.getRoleList();
		//
		if (roleIds.contains(manageRole.getId())) {
			manageRoles.remove(manageRole);
			saveUser(manageUser);
			return true;
		}
		return false;
	}

	@Transactional (readOnly = false)
	public ManageUser assignUserToRole(ManageRole manageRole, String userId) {
		ManageUser manageUser = manageUserDao.get(userId);
		List<Long> roleIds = manageUser.getRoleIdList();
		if (roleIds.contains(manageRole.getId())) {
			return null;
		}
		manageUser.getRoleList().add(manageRole);
		saveUser(manageUser);
		return manageUser;
	}

	// -- Menu Service --//

	public Menu getMenu(Long id) {
		return menuDao.get(id);
	}

	public List<Menu> findAllMenu() {
		return UserUtils.getMenuList();
	}

	@Transactional (readOnly = false)
	public void saveMenu(Menu menu) {
		menu.setParent(this.getMenu(menu.getParent().getId()));
		String oldParentIds = menu.getParentIds(); // 获取修改前的parentIds，用于更新子节点的parentIds
		menu.setParentIds(menu.getParent().getParentIds()
				+ menu.getParent().getId() + ",");
		menuDao.clear();
		menuDao.save(menu);
		// 更新子节点 parentIds
		List<Menu> list = menuDao.findByParentIdsLike("%," + menu.getId()
				+ ",%");
		for (Menu e : list) {
			e.setParentIds(e.getParentIds().replace(oldParentIds,
					menu.getParentIds()));
		}
		menuDao.save(list);
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}

	@Transactional (readOnly = false)
	public void deleteMenu(Long id) {
		menuDao.deleteById(id, "%," + id.toString() + ",%");
		systemRealm.clearAllCachedAuthorizationInfo();
		UserUtils.removeCache(UserUtils.CACHE_MENU_LIST);
	}
}
