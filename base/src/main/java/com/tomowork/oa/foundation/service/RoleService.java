package com.tomowork.oa.foundation.service;

import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.Role;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;


public interface RoleService {
	boolean save(Role paramRole);

	boolean delete(Long paramLong);

	boolean update(Role paramRole);

	Role getObjById(Long paramLong);

	List<Role> query(String paramString, Map<String, Object> paramMap,
					 int paramInt1, int paramInt2);

	PageList<Role> list(QueryObject paramQueryObject);

	Role getObjByProperty(String paramString, Object paramObject);
}
