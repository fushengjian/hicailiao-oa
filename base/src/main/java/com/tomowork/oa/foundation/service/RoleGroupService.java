package com.tomowork.oa.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.RoleGroup;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface RoleGroupService {
	boolean save(RoleGroup paramRoleGroup);

	RoleGroup getObjById(Long paramLong);

	boolean delete(Long paramLong);

	boolean batchDelete(List<Serializable> paramList);

	PageList<RoleGroup> list(QueryObject paramQueryObject);

	boolean update(RoleGroup paramRoleGroup);

	List<RoleGroup> query(String paramString, Map<String, Object> paramMap,
						  int paramInt1, int paramInt2);

	RoleGroup getObjByProperty(String paramString,
							   Object paramObject);
}
