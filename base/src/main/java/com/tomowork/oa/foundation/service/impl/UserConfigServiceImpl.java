package com.tomowork.oa.foundation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.foundation.domain.UserConfig;
import com.tomowork.oa.foundation.service.UserConfigService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserConfigServiceImpl implements UserConfigService {

	@Resource(name = "userConfigDAO")
	private GenericDAO<UserConfig> userConfigDao;

	@Resource(name = "userDAO")
	private GenericDAO<User> userDAO;

	public boolean save(UserConfig userConfig) {
		try {
			this.userConfigDao.save(userConfig);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public UserConfig getObjById(Long id) {
		UserConfig userConfig = this.userConfigDao.get(id);
		if (userConfig != null) {
			return userConfig;
		}
		return null;
	}

	public boolean delete(Long id) {
		try {
			this.userConfigDao.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean batchDelete(List<Serializable> userConfigIds) {
		for (Serializable id : userConfigIds) {
			delete((Long) id);
		}
		return true;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<UserConfig> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<UserConfig> pList = new GenericPageList<>(UserConfig.class, properties, this.userConfigDao);
		pList.doList();
		return pList;
	}

	public boolean update(UserConfig userConfig) {
		try {
			this.userConfigDao.update(userConfig);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<UserConfig> query(String query, Map<String, Object> params, int begin, int max) {
		return this.userConfigDao.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public UserConfig getUserConfig() {
		return null;
	}
}
