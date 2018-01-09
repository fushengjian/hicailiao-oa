package com.tomowork.oa.query;

import java.util.HashMap;
import java.util.Map;

import com.tomowork.oa.domain.virtual.SysMap;
import com.tomowork.oa.mv.Model;
import com.tomowork.oa.query.support.QueryObject;

public abstract class AbstractQueryObject implements QueryObject {
	private static final int DEFAULT_PAGE_SIZE = 12;

	protected Integer pageSize = DEFAULT_PAGE_SIZE;

	protected Integer currentPage = 0;

	protected String orderBy;

	protected String orderType;

	protected Map<String, String> joins = new HashMap<>();

	protected Map<String, Object> params = new HashMap<>();

	protected String queryString = "1=1";

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	protected void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public Integer getCurrentPage() {
		if (this.currentPage == null) {
			this.currentPage = -1;
		}
		return this.currentPage;
	}

	public String getOrder() {
		return this.orderType;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public Integer getPageSize() {
		if (this.pageSize == null) {
			this.pageSize = -1;
		}
		return this.pageSize;
	}

	public AbstractQueryObject() {
	}

	public AbstractQueryObject(String currentPage, Model mv, String orderBy,
			String orderType) {
		if (currentPage != null && !currentPage.isEmpty()) {
			int v = 0;
			try {
				v = Integer.parseInt(currentPage);
			} catch (NumberFormatException ignore) {
			}
			setCurrentPage(v);
		}
		//setPageSize(this.pageSize); // duplicated
		if (orderBy == null || orderBy.isEmpty()) {
			setOrderBy("addTime");
			mv.addObject("orderBy", "addTime");
		} else {
			setOrderBy(orderBy);
			mv.addObject("orderBy", orderBy);
		}
		if (orderType == null || orderType.equalsIgnoreCase("desc")) {
			setOrderType("desc");
			mv.addObject("orderType", "desc");
		} else { // FIXME: check invalid orderType ?? by hzl 2016/3/9
			setOrderType(orderType);
			mv.addObject("orderType", orderType);
		}
	}

	public PageObject getPageObj() {
		PageObject pageObj = new PageObject();
		pageObj.setCurrentPage(getCurrentPage());
		pageObj.setPageSize(getPageSize());
		if (this.currentPage == null || this.currentPage <= 0) {
			pageObj.setCurrentPage(1);
		}
		return pageObj;
	}

	public Map<String, String> getJoins() {
		return joins;
	}

	public String getQuery() {
		customizeQuery();
		return this.queryString + orderString();
	}

	protected String orderString() {
		String orderString = " ";
		if (getOrderBy() != null && !getOrderBy().isEmpty()) {
			orderString += " order by obj." + getOrderBy();
		}
		if (getOrderType() != null && !getOrderType().isEmpty()) {
			orderString += " " + getOrderType();
		}
		return orderString;
	}

	public Map<String, Object> getParameters() {
		return this.params;
	}

	public QueryObject join(String field, String alias) {
		if (field != null && alias != null) {
			joins.put(field, alias);
		}
		return this;
	}

	public QueryObject addQuery(String field, SysMap para, String expression) {
		if (field != null && para != null) {
			queryString += " and " + field + " "
					+ handleExpression(expression) + ":"
					+ para.getKey();
			this.params.put(para.getKey(), para.getValue());
		}
		return this;
	}

	public QueryObject addQuery(String field, SysMap para, String expression,
								String logic) {
		if (field != null && para != null) {
			queryString += " " + logic + " " + field
					+ " " + handleExpression(expression) + ":"
					+ para.getKey();
			this.params.put(para.getKey(), para.getValue());
		}
		return this;
	}

	public QueryObject addQuery(String scope, Map<String, Object> paras) {
		if (scope != null) {
			if (scope.trim().indexOf("and") == 0
					|| scope.trim().indexOf("or") == 0) {
				queryString += " " + scope;
			} else {
				queryString += " and " + scope;
			}
			if (paras != null && !paras.isEmpty()) {
				this.params.putAll(paras);
			}
		}
		return this;
	}

	public QueryObject addQuery(String para, Object obj, String field,
			String expression) {
		if (field != null && para != null) {
			queryString += " and :" + para + " "
					+ expression + " " + field;
			this.params.put(para, obj);
		}
		return this;
	}

	public QueryObject addQuery(String para, Object obj, String field,
			String expression, String logic) {
		if (field != null && para != null) {
			queryString += " " + logic + " :" + para
					+ " " + expression + " " + field;
			this.params.put(para, obj);
		}
		return this;
	}

	private String handleExpression(String expression) {
		if (expression == null) {
			return "=";
		}
		return expression;
	}

	public void customizeQuery() {
	}
}
