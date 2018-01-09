package com.tomowork.oa.manage.common.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomowork.oa.manage.common.utils.DateUtils;
import com.tomowork.oa.manage.sys.entity.ManageUser;
import com.tomowork.oa.manage.sys.utils.UserUtils;

/**
 * 数据Entity类
 */
@MappedSuperclass
public abstract class DataEntity<T> extends BaseEntity<T> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Length (min = 0, max = 255)
	protected String remarks; // 备注

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound (action = NotFoundAction.IGNORE)
	@JoinColumn(name = "create_by")
	protected ManageUser createBy; // 创建者

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	protected Date createDate; // 创建日期

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound (action = NotFoundAction.IGNORE)
	@JoinColumn(name = "update_by")
	protected ManageUser updateBy; // 更新者

	@Column(name = "update_date")
	protected Date updateDate; // 更新日期

	@Column(name = "del_flag")
	protected String delFlag; // 删除标记（0：正常；1：删除；2：审核）

	@Temporal(TemporalType.DATE)
	@Transient
	protected Date createDateStart;

	@Temporal(TemporalType.DATE)
	@Transient
	protected Date createDateEnd;

	@Temporal(TemporalType.DATE)
	@Transient
	protected Date updateDateStart;

	@Temporal(TemporalType.DATE)
	@Transient
	protected Date updateDateEnd;

	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}

	@PrePersist
	public void prePersist() {
		ManageUser manageUser = UserUtils.getUser();
		if (manageUser.getId() != null) {
			this.updateBy = manageUser;
			this.createBy = manageUser;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}

	@PreUpdate
	public void preUpdate() {
		ManageUser manageUser = UserUtils.getUser();
		if (manageUser.getId() != null) {
			this.updateBy = manageUser;
		}
		this.updateDate = new Date();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ManageUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(ManageUser createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public ManageUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(ManageUser updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateDateStart() {
		return DateUtils.getDateStart(createDateStart);
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return DateUtils.getDateEnd(createDateEnd);
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public Date getUpdateDateStart() {
		return DateUtils.getDateStart(updateDateStart);
	}

	public void setUpdateDateStart(Date updateDateStart) {
		this.updateDateStart = updateDateStart;
	}

	public Date getUpdateDateEnd() {
		return DateUtils.getDateEnd(updateDateEnd);
	}

	public void setUpdateDateEnd(Date updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}
}
