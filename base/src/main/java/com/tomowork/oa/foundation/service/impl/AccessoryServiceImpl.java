package com.tomowork.oa.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.Accessory;
import com.tomowork.oa.foundation.service.AccessoryService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

	@Resource(name = "accessoryDAO")
	private GenericDAO<Accessory> accessoryDAO;

	public boolean delete(Long id) {
		try {
			this.accessoryDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Accessory getObjById(Long id) {
		return this.accessoryDAO.get(id);
	}

	public boolean save(Accessory acc) {
		try {
			this.accessoryDAO.save(acc);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	public boolean update(Accessory acc) {
		try {
			this.accessoryDAO.update(acc);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Accessory> query(String query, Map<String, Object> params, int begin, int max) {
		return this.accessoryDAO.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Accessory> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Accessory> pList = new GenericPageList<>(Accessory.class, properties, this.accessoryDAO);
		pList.doList();
		return pList;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Accessory getObjByProperty(String propertyName, String value) {
		return this.accessoryDAO.getBy(propertyName, value);
	}
}
