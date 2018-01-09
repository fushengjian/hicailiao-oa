package com.tomowork.oa.query.support;

import java.io.Serializable;
import java.util.List;

public interface PageList<T> extends Serializable {
	List<T> getResult();

	int getPages();

	int getRowCount();

	int getCurrentPage();

	int getPageSize();

	void doList();

	void doList(int currentPage, int pageSize);

	//void doList(int paramInt1, int paramInt2, String paramString1, String paramString2);

	//void doList(int paramInt1, int paramInt2,
	//		String paramString1, String paramString2, Map<String, Object> paramMap);
}
