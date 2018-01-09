package com.tomowork.oa.manage.common.persistence;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * Entity支持类
 */
@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 当前用户
	 */
	@JsonIgnore
	@XmlTransient
	@Transient
	protected ManageUser currentUser;

	/**
	 * 当前实体分页对象
	 */
	@JsonIgnore
	@XmlTransient
	@Transient
	protected Page<T> page;

	/**
	 * 自定义SQL（SQL标识，SQL内容）
	 */
	@JsonIgnore
	@XmlTransient
	@Transient
	protected Map<String, String> sqlMap;

	/**
	 *从页面搜索还是从菜单搜索（用于非页面搜索时设置默认搜索条件）
	 */
	@Transient
	private boolean searchFromPage;

	/**
	 *用于搜索多个ID的时候设置搜索条件
	 */
	@Transient
	private String ids;


	public ManageUser getCurrentUser() {
		if (currentUser == null) {
			currentUser = UserUtils.getUser();
		}
		return currentUser;
	}

	public void setCurrentUser(ManageUser currentUser) {
		this.currentUser = currentUser;
	}


	public Page<T> getPage() {
		if (page == null) {
			page = new Page<T>();
		}
		return page;
	}

	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}


	public Map<String, String> getSqlMap() {
		if (sqlMap == null) {
			sqlMap = Maps.newHashMap();
		}
		return sqlMap;
	}

	public void setSqlMap(Map<String, String> sqlMap) {
		this.sqlMap = sqlMap;
	}


	public boolean isSearchFromPage() {
		return searchFromPage;
	}

	public void setSearchFromPage(boolean searchFromPage) {
		this.searchFromPage = searchFromPage;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}


	// 显示/隐藏
	public static final String SHOW = "1";
	public static final String HIDE = "0";

	// 是/否
	public static final String YES = "1";
	public static final String NO = "0";

	// 删除标记（0：正常；1：删除；2：审核；）
	public static final String FIELD_DEL_FLAG = "delFlag";
	public static final String DEL_FLAG_NORMAL = "0";
	public static final String DEL_FLAG_DELETE = "1";
	public static final String DEL_FLAG_AUDIT = "2";

}
