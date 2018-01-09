package com.tomowork.oa.foundation.service;

import java.util.List;
import java.util.Map;

import com.tomowork.oa.foundation.domain.Accessory;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

public interface AccessoryService {
	boolean save(Accessory paramAccessory);

	boolean delete(Long paramLong);

	boolean update(Accessory paramAccessory);

	PageList<Accessory> list(QueryObject paramQueryObject);

	Accessory getObjById(Long paramLong);

	Accessory getObjByProperty(String paramString1,
							   String paramString2);

	List<Accessory> query(String paramString, Map<String, Object> paramMap,
						  int paramInt1, int paramInt2);
}
