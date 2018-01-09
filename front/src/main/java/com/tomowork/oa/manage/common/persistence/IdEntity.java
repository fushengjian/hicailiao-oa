package com.tomowork.oa.manage.common.persistence;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;


/**
 * 数据Entity类
 */
@MappedSuperclass
public abstract class IdEntity<T> extends DataEntity<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;		// 编号

	public IdEntity() {
		super();
	}

	@PrePersist
	public void prePersist() {
		super.prePersist();
		//this.id = IdGen.uuid();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
