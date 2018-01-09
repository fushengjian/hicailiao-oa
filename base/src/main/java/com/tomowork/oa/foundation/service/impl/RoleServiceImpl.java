package com.tomowork.oa.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.Role;
import com.tomowork.oa.foundation.service.RoleService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleDAO")
	private GenericDAO<Role> roleDAO;

	public boolean delete(Long id) {
		try {
			this.roleDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getObjById(Long id) {
		return this.roleDAO.get(id);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Role> query(String query, Map<String, Object> params, int begin, int max) {
		return this.roleDAO.query(query, params, begin, max);
	}

	public boolean save(Role role) {
		try {
			this.roleDAO.save(role);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Role role) {
		try {
			this.roleDAO.update(role);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Role> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Role> pList = new GenericPageList<>(Role.class, properties, this.roleDAO);
		pList.doList();
		return pList;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Role getObjByProperty(String propertyName, Object value) {
		return this.roleDAO.getBy(propertyName, value);
	}
}
