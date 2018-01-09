package com.tomowork.oa.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.SysLog;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface SysLogService {
	boolean save(SysLog paramSysLog);

	SysLog getObjById(Long paramLong);

	boolean delete(Long paramLong);

	boolean batchDelete(List<Serializable> paramList);

	PageList<SysLog> list(QueryObject paramQueryObject);

	boolean update(SysLog paramSysLog);

	List<SysLog> query(String paramString, Map<String, Object> paramMap,
					   int paramInt1, int paramInt2);
}
