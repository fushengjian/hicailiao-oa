package com.tomowork.oa.manage.sys.dao;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.manage.common.persistence.BaseDao;
import com.tomowork.oa.manage.common.persistence.Parameter;
import com.tomowork.oa.manage.sys.entity.ManageRole;

/**
 * 角色DAO接口
 */
@Repository
public class ManageRoleDao extends BaseDao<ManageRole> {

	public ManageRole findByName(String name) {
		return getByHql("from ManageRole where delFlag = :p1 and name = :p2", new Parameter(ManageRole.DEL_FLAG_NORMAL, name));
	}

}
