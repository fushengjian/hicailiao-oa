package com.tomowork.oa.manage.sys.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.manage.common.persistence.IdEntity;

/**
 * 机构Entity
 * @version 2013-05-15
 */
@Entity
@Table(name = "sys_office")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class Office extends IdEntity<Office> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound (action = NotFoundAction.IGNORE)
	@NotNull
	private Office parent;	// 父级编号

	@Column(name = "parent_ids")
	private String parentIds; // 所有父级编号

	@ManyToOne
	@JoinColumn(name = "area_id")
	@NotFound (action = NotFoundAction.IGNORE)
	@NotNull
	private Area area;		// 归属区域

	private String code; 	// 机构编码

	private String name; 	// 机构名称

	private String type; 	// 机构类型（1：公司；2：部门；3：小组）

	private String grade; 	// 机构等级（1：一级；2：二级；3：三级；4：四级）

	private String address; // 联系地址

	@Column(name = "zip_code")
	private String zipCode; // 邮政编码

	private String master; 	// 负责人

	private String phone; 	// 电话

	private String fax; 	// 传真

	private String email; 	// 邮箱

	@OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ManageUser> userList = Lists.newArrayList();   // 拥有用户列表

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy(value = "code")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Office> childList = Lists.newArrayList(); // 拥有子机构列表

	public Office() {
		super();
	}

	public Office(Long id) {
		this();
		this.id = id;
	}


	public Office getParent() {
		return parent;
	}

	public void setParent(Office parent) {
		this.parent = parent;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}


	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public List<ManageUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ManageUser> userList) {
		this.userList = userList;
	}


	public List<Office> getChildList() {
		return childList;
	}

	public void setChildList(List<Office> childList) {
		this.childList = childList;
	}

	public static void sortList(List<Office> list, List<Office> sourcelist, Long parentId) {
		for (int i = 0; i < sourcelist.size(); i++) {
			Office e = sourcelist.get(i);
			if (e.getParent() != null && e.getParent().getId() != null
					&& e.getParent().getId().equals(parentId)) {
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j = 0; j < sourcelist.size(); j++) {
					Office child = sourcelist.get(j);
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

}
