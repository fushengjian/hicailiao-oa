package com.tomowork.oa.manage.sys.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.tomowork.oa.manage.common.persistence.IdEntity;
import com.tomowork.oa.manage.common.utils.Collections3;

/**
 * 用户Entity
 */
@Entity
@Table(name = "sys_user")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class ManageUser extends IdEntity<ManageUser> {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "company_id")
	@NotFound (action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message = "归属公司不能为空")
	private Office company;	// 归属公司

	@ManyToOne
	@JoinColumn(name = "office_id")
	@NotFound (action = NotFoundAction.IGNORE)
	@JsonIgnore
	@NotNull(message = "归属部门不能为空")
	private Office office;	// 归属部门

	@Column(name = "login_name")
	private String loginName; // 登录名

	private String password; // 密码

	private String no;		// 工号

	private String name;	// 姓名

	private String email;	// 邮箱

	private String phone;	// 电话

	private String mobile;	// 手机

	@Column(name = "user_type")
	private String userType; // 用户类型

	@Column(name = "login_ip")
	private String loginIp;	// 最后登陆IP

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "login_date")
	private Date loginDate;	// 最后登陆日期

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	@Where (clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@Fetch (FetchMode.SUBSELECT)
	@NotFound (action = NotFoundAction.IGNORE)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<ManageRole> roleList = Lists.newArrayList(); // 拥有角色列表

	public ManageUser() {
		super();
	}

	public ManageUser(Long id) {
		this();
		this.id = id;
	}


	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}


	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getRemarks() {
		return remarks;
	}


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public List<ManageRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<ManageRole> roleList) {
		this.roleList = roleList;
	}


	public List<Long> getRoleIdList() {
		List<Long> roleIdList = Lists.newArrayList();
		for (ManageRole manageRole : roleList) {
			roleIdList.add(manageRole.getId());
		}
		return roleIdList;
	}

	@Transient
	public void setRoleIdList(List<Long> roleIdList) {
		roleList = Lists.newArrayList();
		for (Long roleId : roleIdList) {
			ManageRole manageRole = new ManageRole();
			manageRole.setId(roleId);
			roleList.add(manageRole);
		}
	}

	/**
	 * 用户拥有的角色名称字符串, 多个角色名称用','分隔.
	 */
	public String getRoleNames() {
		return Collections3.extractToString(roleList, "name", ", ");
	}

	public boolean isAdmin() {
		return isAdmin(this.id);
	}

	public static boolean isAdmin(Long id) {
		return id != null && id.longValue() == 1;
	}

}
