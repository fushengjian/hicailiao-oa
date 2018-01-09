package com.tomowork.oa.foundation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.SysLog;
import com.tomowork.oa.foundation.service.SysLogService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

	@Resource(name = "sysLogDAO")
	private GenericDAO<SysLog> sysLogDao;

	public boolean save(SysLog sysLog) {
		try {
			this.sysLogDao.save(sysLog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public SysLog getObjById(Long id) {
		SysLog sysLog = this.sysLogDao.get(id);
		return sysLog;
	}

	public boolean delete(Long id) {
		try {
			this.sysLogDao.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean batchDelete(List<Serializable> sysLogIds) {
		for (Serializable id : sysLogIds) {
			delete((Long) id);
		}
		return true;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<SysLog> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<SysLog> pList = new GenericPageList<>(SysLog.class, properties, this.sysLogDao);
		pList.doList();
		return pList;
	}

	public boolean update(SysLog sysLog) {
		try {
			this.sysLogDao.update(sysLog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SysLog> query(String query, Map<String, Object> params, int begin, int max) {
		return this.sysLogDao.query(query, params, begin, max);
	}
}
