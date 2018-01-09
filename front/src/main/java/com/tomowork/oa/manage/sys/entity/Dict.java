package com.tomowork.oa.manage.sys.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.tomowork.oa.manage.common.persistence.IdEntity;


/**
 * 字典Entity
 */
@Entity
@Table(name = "sys_dict")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends IdEntity<Dict> {

	private static final long serialVersionUID = 1L;

	private String label;	// 标签名

	private String value;	// 数据值

	private String type;	// 类型

	private String description; // 描述

	private Integer sort;	// 排序

	public Dict() {
		super();
	}

	public Dict(Long id) {
		this();
		this.id = id;
	}


	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
