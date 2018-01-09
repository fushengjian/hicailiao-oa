package com.tomowork.oa.query;


import com.tomowork.oa.mv.Model;

public class BaseQueryObject extends AbstractQueryObject {
	public BaseQueryObject(String currentPage, Model mv, String orderBy, String orderType) {
		super(currentPage, mv, orderBy, orderType);
	}

	public String getQuery() {
		return super.getQuery();
	}
}
