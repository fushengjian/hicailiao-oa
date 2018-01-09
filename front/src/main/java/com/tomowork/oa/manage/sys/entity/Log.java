package com.tomowork.oa.manage.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.manage.common.persistence.BaseEntity;

/**
 * 日志Entity
 */
@Entity
@Table(name = "sys_log")
@DynamicInsert
@DynamicUpdate
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
public class Log extends BaseEntity<Area> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;			// 日志编号

	private String type; 		// 日志类型（1：接入日志；2：错误日志）

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound (action = NotFoundAction.IGNORE)
	@JoinColumn(name = "create_by")
	private ManageUser createBy;		// 创建者

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_date")
	private Date createDate;	// 日志创建时间

	@Column(name = "remote_addr")
	private String remoteAddr; 	// 操作用户的IP地址

	@Column(name = "request_uri")
	private String requestUri; 	// 操作的URI

	@Column(name = "method")
	private String method; 		// 操作的方式

	@Column(name = "params")
	private String params; 		// 操作提交的数据

	@Column(name = "user_agent")
	private String userAgent;	// 操作用户代理信息

	@Column(name = "exception")
	private String exception; 	// 异常信息

	public static final String TYPE_ACCESS = "1";

	public static final String TYPE_EXCEPTION = "2";

	public Log() {
		super();
	}

	public Log(Long id) {
		this();
		this.id = id;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}
