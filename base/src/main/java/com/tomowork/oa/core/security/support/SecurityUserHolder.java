package com.tomowork.oa.core.security.support;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.foundation.service.UserService;


@Named
public class SecurityUserHolder {

	private static Logger log = LoggerFactory.getLogger(SecurityUserHolder.class);

	private static SecurityUserHolder instance;

	@Inject
	private UserService userService;

	@PostConstruct
	public void init() {
		instance = this;
	}


	public static Long getCurrentUserId() {
		Subject current;
		try {
			current = SecurityUtils.getSubject();
		} catch (RuntimeException e) {
			log.debug("获取用户信息失败!", e);
			current = null;
		}

		// FIXME: shop-admin 引用时 current.getPrincipal() 为 ManageUser, by hzl 2016/3/3
		// @see {com.tomowork.shop.manage.sys.security.SystemAuthorizingRealm}
		if (current != null && current.getPrincipal() instanceof Long)
			return  (Long) current.getPrincipal();
		return null;
	}

	public static User getCurrentUser() {
		Long userId = getCurrentUserId();

		if (userId != null)
			return instance.userService.getObjById(userId);

		return null;
	}
}
