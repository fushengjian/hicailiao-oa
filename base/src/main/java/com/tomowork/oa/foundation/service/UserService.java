package com.tomowork.oa.foundation.service;

import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface UserService {
	boolean save(User paramUser);

	boolean delete(Long paramLong);

	boolean update(User paramUser);

	PageList<User> list(QueryObject paramQueryObject);

	User getObjById(Long paramLong);

	User getObjByProperty(String paramString1,
						  String paramString2);

	List<User> query(String paramString, Map<String, Object> paramMap,
					 int paramInt1, int paramInt2);

	User getUserByUsername(String username);
}
