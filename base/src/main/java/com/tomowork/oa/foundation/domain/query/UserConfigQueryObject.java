package com.tomowork.oa.foundation.domain.query;

import com.tomowork.oa.mv.Model;
import com.tomowork.oa.query.AbstractQueryObject;

public class UserConfigQueryObject extends AbstractQueryObject {
	public UserConfigQueryObject(String currentPage, Model mv,
			String orderBy, String orderType) {
		super(currentPage, mv, orderBy, orderType);
	}

	public UserConfigQueryObject() {
	}
}
