package com.tomowork.oa.foundation.service;

import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.Res;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;


public interface ResService {
	boolean save(Res paramRes);

	boolean delete(Long paramLong);

	boolean update(Res paramRes);

	Res getObjById(Long paramLong);

	List<Res> query(String paramString, Map<String, Object> paramMap,
					int paramInt1, int paramInt2);

	PageList<Res> list(QueryObject paramQueryObject);

	Map<String, String> getUrlAuthorities();
}
