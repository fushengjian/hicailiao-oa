package com.tomowork.oa.foundation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.Res;
import com.tomowork.oa.foundation.service.ResService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

@Service
@Transactional
public class ResServiceImpl implements ResService {

	@Resource(name = "resDAO")
	private GenericDAO<Res> resDAO;

	public boolean delete(Long id) {
		try {
			this.resDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean save(Res res) {
		try {
			this.resDAO.save(res);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(Res res) {
		try {
			this.resDAO.update(res);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Res getObjById(Long id) {
		return this.resDAO.get(id);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Res> query(String query, Map<String, Object> params, int begin, int max) {
		return this.resDAO.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Res> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Res> pList = new GenericPageList<>(Res.class, properties, this.resDAO);
		pList.doList();
		return pList;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, String> getUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<>();
		Map<String, Object> params = new HashMap<>();
		params.put("type", "URL");
		List<Res> urlResources = resDAO.query(
				"select obj from Res obj where obj.type = :type", params, -1,
				-1);
		for (Res res : urlResources) {
			urlAuthorities.put(res.getValue(), res.getRoleAuthorities());
		}
		return urlAuthorities;
	}
}
