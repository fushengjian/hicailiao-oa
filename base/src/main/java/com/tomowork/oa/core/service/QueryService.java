package com.tomowork.oa.core.service;

import java.util.List;
import java.util.Map;

public interface QueryService {
	@SuppressWarnings("rawtypes")
	List query(String paramString, Map<String, Object> paramMap, int paramInt1, int paramInt2);
}
