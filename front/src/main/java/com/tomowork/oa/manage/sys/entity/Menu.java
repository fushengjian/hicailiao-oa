package com.tomowork.oa.manage.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.google.common.collect.Lists;
import com.tomowork.oa.manage.common.persistence.IdEntity;

/**
 * 菜单Entity
 */
@Entity
@Table(name = "sys_menu")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends IdEntity<Menu> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound (action = NotFoundAction.IGNORE)
	@NotNull
	private Menu parent;	// 父级菜单

	@Column(name = "parent_ids")
	private String parentIds; // 所有父级编号

	private String name; 	// 名称

	private String href; 	// 链接

	private String target; 	// 目标（ mainFrame、_blank、_self、_parent、_top）

	private String icon; 	// 图标

	private Integer sort; 	// 排序

	@Column(name = "is_show")
	private String isShow; 	// 是否在菜单中显示（1：显示；0：不显示）

	private int type; 	// 是否同步到工作流（1：同步；0：不同步）

	private String permission; // 权限标识

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "sort")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Menu> childList = Lists.newArrayList(); // 拥有子菜单列表

	@ManyToMany(mappedBy = "menuList", fetch = FetchType.LAZY)
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ManageRole> roleList = Lists.newArrayList(); // 拥有角色列表

	public Menu() {
		super();
		this.sort = 30;
	}

	public Menu(Long id) {
		this();
		this.id = id;
	}


	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}


	public List<Menu> getChildList() {
		return childList;
	}

	public void setChildList(List<Menu> childList) {
		this.childList = childList;
	}


	public List<ManageRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<ManageRole> roleList) {
		this.roleList = roleList;
	}

	public static void sortList(List<Menu> list, List<Menu> sourcelist, Long parentId) {
		for (int i = 0; i < sourcelist.size(); i++) {
			Menu e = sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null
					&& e.getParent().getId().equals(parentId)) {
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j = 0; j < sourcelist.size(); j++) {
					Menu child = sourcelist.get(j);
					if (child.getParent() != null && child.getParent().getId() != null
							&& child.getParent().getId().equals(e.getId())) {
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}

	public boolean isRoot() {
		return isRoot(this.id);
	}

	public static boolean isRoot(Long id) {
		return id != null && id.longValue() == 1;
	}

	public String getActivitiGroupId() {
		return ObjectUtils.toString(getPermission());
	}

	public String getActivitiGroupName() {
		return ObjectUtils.toString(getId());
	}

}
