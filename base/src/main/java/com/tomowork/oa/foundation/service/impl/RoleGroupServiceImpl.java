package com.tomowork.oa.foundation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.RoleGroup;
import com.tomowork.oa.foundation.service.RoleGroupService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleGroupServiceImpl implements RoleGroupService {

	@Resource(name = "roleGroupDAO")
	private GenericDAO<RoleGroup> roleGroupDao;

	public boolean save(RoleGroup roleGroup) {
		try {
			this.roleGroupDao.save(roleGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public RoleGroup getObjById(Long id) {
		RoleGroup roleGroup = this.roleGroupDao.get(id);
		if (roleGroup != null) {
			return roleGroup;
		}
		return null;
	}

	public boolean delete(Long id) {
		try {
			this.roleGroupDao.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean batchDelete(List<Serializable> roleGroupIds) {
		for (Serializable id : roleGroupIds) {
			delete((Long) id);
		}
		return true;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<RoleGroup> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<RoleGroup> pList = new GenericPageList<>(RoleGroup.class, properties, this.roleGroupDao);
		pList.doList();
		return pList;
	}

	public boolean update(RoleGroup roleGroup) {
		try {
			this.roleGroupDao.update(roleGroup);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<RoleGroup> query(String query, Map<String, Object> params, int begin, int max) {
		return this.roleGroupDao.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public RoleGroup getObjByProperty(String propertyName, Object value) {
		return this.roleGroupDao.getBy(propertyName, value);
	}
}
