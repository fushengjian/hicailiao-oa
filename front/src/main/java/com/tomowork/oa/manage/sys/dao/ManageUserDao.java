/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.tomowork.oa.manage.sys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.manage.common.persistence.BaseDao;
import com.tomowork.oa.manage.common.persistence.Parameter;
import com.tomowork.oa.manage.sys.entity.ManageUser;

/**
 * 用户DAO接口
 * @author ThinkGem
 * @version 2013-8-23
 */
@Repository
public class ManageUserDao extends BaseDao<ManageUser> {

	public List<ManageUser> findAllList() {
		return find("from ManageUser where delFlag=:p1 order by id", new Parameter(ManageUser.DEL_FLAG_NORMAL));
	}

	public ManageUser findByLoginName(String loginName) {
		return getByHql("from ManageUser where loginName = :p1 and delFlag = :p2", new Parameter(loginName, ManageUser.DEL_FLAG_NORMAL));
	}

	public int updatePasswordById(String newPassword, Long id) {
		return update("update ManageUser set password=:p1 where id = :p2", new Parameter(newPassword, id));
	}

	public int updateLoginInfo(String loginIp, Date loginDate, Long id) {
		return update("update ManageUser set loginIp=:p1, loginDate=:p2 where id = :p3", new Parameter(loginIp, loginDate, id));
	}

}
