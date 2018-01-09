package com.tomowork.oa.manage.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import com.tomowork.oa.manage.common.persistence.IdEntity;

import com.google.common.collect.Lists;

/**
 * 角色Entity
 * @author ThinkGem
 * @version 2013-05-15
 */
@Entity
@Table(name = "sys_role")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManageRole extends IdEntity<ManageRole> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "office_id")
	@NotFound (action = NotFoundAction.IGNORE)
	private Office office;	// 归属机构

	private String name; 	// 角色名称

	@Column(name = "data_scope")
	private String dataScope; // 数据范围

	@ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ManageUser> userList = Lists.newArrayList(); // 拥有用户列表

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "sys_role_menu", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "menu_id") })
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Menu> menuList = Lists.newArrayList(); // 拥有菜单列表

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sys_role_office", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = { @JoinColumn(name = "office_id") })
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Office> officeList = Lists.newArrayList(); // 按明细设置数据范围

	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）
	public static final String DATA_SCOPE_ALL = "1";

	public static final String DATA_SCOPE_COMPANY_AND_CHILD = "2";

	public static final String DATA_SCOPE_COMPANY = "3";

	public static final String DATA_SCOPE_OFFICE_AND_CHILD = "4";

	public static final String DATA_SCOPE_OFFICE = "5";

	public static final String DATA_SCOPE_SELF = "8";

	public static final String DATA_SCOPE_CUSTOM = "9";

	public ManageRole() {
		super();
		this.dataScope = DATA_SCOPE_CUSTOM;
	}

	public ManageRole(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}


	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataScope() {
		return dataScope;
	}

	public void setDataScope(String dataScope) {
		this.dataScope = dataScope;
	}


	public List<ManageUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ManageUser> userList) {
		this.userList = userList;
	}

	public List<Long> getUserIdList() {
		List<Long> nameIdList = Lists.newArrayList();
		for (ManageUser manageUser : userList) {
			nameIdList.add(manageUser.getId());
		}
		return nameIdList;
	}

	public String getUserIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (ManageUser manageUser : userList) {
			nameIdList.add(manageUser.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}


	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public List<Long> getMenuIdList() {
		List<Long> menuIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			menuIdList.add(menu.getId());
		}
		return menuIdList;
	}

	public void setMenuIdList(List<Long> menuIdList) {
		menuList = Lists.newArrayList();
		for (Long menuId : menuIdList) {
			Menu menu = new Menu();
			menu.setId(menuId);
			menuList.add(menu);
		}
	}

	public String getMenuIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (Menu menu : menuList) {
			nameIdList.add(menu.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	public void setMenuIds(String menuIds) {
		menuList = Lists.newArrayList();
		if (menuIds != null) {
			String[] ids = StringUtils.split(menuIds, ",");
			for (String menuId : ids) {
				Menu menu = new Menu();
				menu.setId(new Long(menuId));
				menuList.add(menu);
			}
		}
	}


	public List<Office> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}


	public List<Long> getOfficeIdList() {
		List<Long> officeIdList = Lists.newArrayList();
		for (Office office : officeList) {
			officeIdList.add(office.getId());
		}
		return officeIdList;
	}


	public void setOfficeIdList(List<Long> officeIdList) {
		officeList = Lists.newArrayList();
		for (Long officeId : officeIdList) {
			Office office = new Office();
			office.setId(officeId);
			officeList.add(office);
		}
	}

	public String getOfficeIds() {
		List<Long> nameIdList = Lists.newArrayList();
		for (Office office : officeList) {
			nameIdList.add(office.getId());
		}
		return StringUtils.join(nameIdList, ",");
	}

	public void setOfficeIds(String officeIds) {
		officeList = Lists.newArrayList();
		if (officeIds != null) {
			String[] ids = StringUtils.split(officeIds, ",");
			for (String officeId : ids) {
				Office office = new Office();
				office.setId(new Long(officeId));
				officeList.add(office);
			}
		}
	}

//	@ElementCollection
//	@CollectionTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id") })
//	@Column(name = "user_id")
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//	public List<Long> getUserIdList() {
//		return userIdList;
//	}
//
//	public void setUserIdList(List<Long> userIdList) {
//		this.userIdList = userIdList;
//	}

	/**
	 * 获取权限字符串列表
	 */
	public List<String> getPermissions() {
		List<String> permissions = Lists.newArrayList();
		for (Menu menu : menuList) {
			if (menu.getPermission() != null && !"".equals(menu.getPermission())) {
				permissions.add(menu.getPermission());
			}
		}
		return permissions;
	}


	public boolean isAdmin() {
		return isAdmin(this.id);
	}

	public static boolean isAdmin(Long id) {
		return id != null && id.longValue() == 1;
	}

//	@Transient
//	public String getMenuNames() {
//		List<String> menuNameList = Lists.newArrayList();
//		for (Menu menu : menuList) {
//			menuNameList.add(menu.getName());
//		}
//		return StringUtils.join(menuNameList, ",");
//	}
}
