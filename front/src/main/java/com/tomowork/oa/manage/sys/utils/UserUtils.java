package com.tomowork.oa.manage.sys.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

import com.google.common.collect.Maps;
import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.foundation.service.AreaService;
import com.tomowork.oa.manage.common.service.BaseService;
import com.tomowork.oa.manage.common.utils.SpringContextHolder;
import com.tomowork.oa.manage.sys.dao.ManageRoleDao;
import com.tomowork.oa.manage.sys.dao.ManageUserDao;
import com.tomowork.oa.manage.sys.dao.MenuDao;
import com.tomowork.oa.manage.sys.dao.OfficeDao;
import com.tomowork.oa.manage.sys.entity.ManageRole;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.entity.Menu;
import com.tomowork.oa.manage.sys.entity.Office;
import com.tomowork.oa.manage.sys.security.SystemAuthorizingRealm.Principal;

/**
 * 用户工具类
 */
public class UserUtils extends BaseService {

	private static ManageUserDao manageUserDao = SpringContextHolder.getBean(ManageUserDao.class);

	private static ManageRoleDao manageRoleDao = SpringContextHolder.getBean(ManageRoleDao.class);

	private static MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);

	private static AreaService areaService = SpringContextHolder.getBean(AreaService.class);

	private static OfficeDao officeDao = SpringContextHolder.getBean(OfficeDao.class);

	public static final String CACHE_USER = "user";

	public static final String CACHE_ROLE_LIST = "roleList";

	public static final String CACHE_MENU_LIST = "menuList";

	public static final String CACHE_AREA_LIST = "areaList";

	public static final String CACHE_OFFICE_LIST = "officeList";

	public static ManageUser getUser() {
		ManageUser manageUser = (ManageUser)getCache(CACHE_USER);
		if (manageUser == null) {
			try {
				Subject subject = SecurityUtils.getSubject();
				Principal principal = (Principal)subject.getPrincipal();
				if (principal != null) {
					manageUser = manageUserDao.get(principal.getId());
//					Hibernate.initialize(user.getRoleList());
					putCache(CACHE_USER, manageUser);
				}
			} catch (UnavailableSecurityManagerException e) {

			} catch (InvalidSessionException e) {

			}
		}
		if (manageUser == null) {
			manageUser = new ManageUser();
			try {
				SecurityUtils.getSubject().logout();
			} catch (UnavailableSecurityManagerException e) {

			} catch (InvalidSessionException e) {

			}
		}
		return manageUser;
	}

	public static ManageUser getUser(boolean isRefresh) {
		if (isRefresh) {
			removeCache(CACHE_USER);
		}
		return getUser();
	}

	public static List<ManageRole> getRoleList() {
		@SuppressWarnings("unchecked")
		List<ManageRole> list = (List<ManageRole>)getCache(CACHE_ROLE_LIST);
		if (list == null) {
			ManageUser manageUser = getUser();
			DetachedCriteria dc = manageRoleDao.createDetachedCriteria();
			dc.createAlias("office", "office");
			dc.createAlias("userList", "userList", JoinType.LEFT_OUTER_JOIN);
			dc.add(dataScopeFilter(manageUser, "office", "userList"));
			dc.add(Restrictions.eq(ManageRole.FIELD_DEL_FLAG, ManageRole.DEL_FLAG_NORMAL));
			dc.addOrder(Order.asc("office.code")).addOrder(Order.asc("name"));
			list = manageRoleDao.find(dc);
			putCache(CACHE_ROLE_LIST, list);
		}
		return list;
	}

	public static List<Menu> getMenuList() {
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>)getCache(CACHE_MENU_LIST);
		if (menuList == null) {
			ManageUser manageUser = getUser();
			if (manageUser.isAdmin()) {
				menuList = menuDao.findAllList();
			} else {
				menuList = menuDao.findByUserId(manageUser.getId());
			}
			putCache(CACHE_MENU_LIST, menuList);
		}
		return menuList;
	}

	public static List<Area> getAreaList() {
		@SuppressWarnings("unchecked")
		List<Area> areaList = (List<Area>)getCache(CACHE_AREA_LIST);
		if (areaList == null) {
//			ManageUser user = getUser();
//			if (user.isAdmin()) {
				areaList = areaService.findAll();
//			} else {
//				areaList = areaDao.findAllChild(user.getArea().getId(), "%,"+user.getArea().getId()+",%");
//			}
			putCache(CACHE_AREA_LIST, areaList);
		}
		return areaList;
	}

	public static List<Office> getOfficeList() {
		@SuppressWarnings("unchecked")
		List<Office> officeList = (List<Office>)getCache(CACHE_OFFICE_LIST);
		if (officeList == null) {
			ManageUser manageUser = getUser();
//			if (user.isAdmin()) {
//				officeList = officeDao.findAllList();
//			} else {
//				officeList = officeDao.findAllChild(user.getOffice().getId(), "%,"+user.getOffice().getId()+",%");
//			}
			DetachedCriteria dc = officeDao.createDetachedCriteria();
			//dc.add(dataScopeFilter(manageUser, dc.getAlias(), ""));
			dc.add(Restrictions.eq("delFlag", Office.DEL_FLAG_NORMAL));
			dc.addOrder(Order.asc("code"));
			officeList = officeDao.find(dc);
			putCache(CACHE_OFFICE_LIST, officeList);
		}
		return officeList;
	}


	public static ManageUser getUserById(String id) {
		if (StringUtils.isNotBlank(id)) {
			return manageUserDao.get(id);
		} else {
			return null;
		}
	}

	// ============== ManageUser Cache ==============

	public static Object getCache(String key) {
		return getCache(key, null);
	}

	public static Object getCache(String key, Object defaultValue) {
		Object obj = getCacheMap().get(key);
		return obj == null ? defaultValue : obj;
	}

	public static void putCache(String key, Object value) {
		getCacheMap().put(key, value);
	}

	public static void removeCache(String key) {
		getCacheMap().remove(key);
	}

	public static Map<String, Object> getCacheMap() {
		Map<String, Object> map = Maps.newHashMap();
		try {
			Subject subject = SecurityUtils.getSubject();
			Principal principal = (Principal)subject.getPrincipal();
			return principal != null ? principal.getCacheMap() : map;
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return map;
	}

}
