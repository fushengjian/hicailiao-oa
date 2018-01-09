package com.tomowork.oa.foundation.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tomowork.oa.domain.IdEntity;

/**
 * 地区表
 *
 */
@Cacheable
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "oa_area")
public class Area extends IdEntity {
	private static final long serialVersionUID = -609901758319846092L;

	/**
	 * 区域名称
	 */
	private String areaName;

	/**
	 * 上级区域
	 */
	@OneToMany(mappedBy = "parent", cascade = { javax.persistence.CascadeType.REMOVE })
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	@OrderBy("sequence asc")
	private List<Area> childs = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private Area parent;

	private int sequence; //排序

	/**
	 * 区域级别,0第一级，1第二级，2第三级
	 */
	private int level;

	/**
	 * 常用地区：true是，false否
	 */
	@Column(columnDefinition = "bit default false")
	private boolean common;

	public boolean isCommon() {
		return this.common;
	}

	public void setCommon(boolean common) {
		this.common = common;
	}

	public List<Area> getChilds() {
		return this.childs;
	}

	public void setChilds(List<Area> childs) {
		this.childs = childs;
	}

	public Area getParent() {
		return this.parent;
	}

	public void setParent(Area parent) {
		this.parent = parent;
	}

	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Set<Long> genericIds() {
		Set<Long> ids = new HashSet<>();
		ids.add(getId()); // FIXME: check deleteStatus??? by hzl 2016/3/10

		for (Area child : getChilds()) {
			ids.addAll(child.genericIds());
		}

		return ids;
	}
}
