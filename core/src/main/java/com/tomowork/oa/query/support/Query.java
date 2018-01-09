package com.tomowork.oa.query.support;

import java.util.List;
import java.util.Map;

public interface Query<T> {
	long getRows(String paramString);

	List<T> getResult(String paramString, Map<String, String> joins);

	void setFirstResult(int paramInt);

	void setMaxResults(int paramInt);

	//void setParaValues(Map<String, Object> paramMap);

	//List<T> getResult(String paramString, int paramInt1, int paramInt2);
}
