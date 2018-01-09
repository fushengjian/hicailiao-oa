package com.tomowork.oa.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface AreaService {
	boolean save(Area paramArea);

	Area getObjById(Long paramLong);

	boolean delete(Long paramLong);

	boolean batchDelete(List<Serializable> paramList);

	PageList<Area> list(QueryObject paramQueryObject);

	boolean update(Area paramArea);

	List<Area> query(String paramString, Map<String, Object> paramMap,
					 int paramInt1, int paramInt2);

	/**
	 * 返回所有区域信息
	 * @return
	 */
	List<Area> findAll();
}
