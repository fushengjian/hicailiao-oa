package com.tomowork.oa.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.UserConfig;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface UserConfigService {
	boolean save(UserConfig paramUserConfig);

	UserConfig getObjById(Long paramLong);

	boolean delete(Long paramLong);

	boolean batchDelete(List<Serializable> paramList);

	PageList<UserConfig> list(QueryObject paramQueryObject);

	boolean update(UserConfig paramUserConfig);

	List<UserConfig> query(String paramString, Map<String, Object> paramMap,
			int paramInt1, int paramInt2);

	UserConfig getUserConfig();
}
