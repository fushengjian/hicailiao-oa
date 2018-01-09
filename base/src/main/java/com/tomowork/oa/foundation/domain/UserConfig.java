package com.tomowork.oa.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.tomowork.oa.domain.IdEntity;

@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "oa_userconfig")
public class UserConfig extends IdEntity {

	private static final long serialVersionUID = 3441264055163304444L;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
