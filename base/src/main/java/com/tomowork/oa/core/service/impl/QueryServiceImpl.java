package com.tomowork.oa.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.base.GenericEntityDao;
import com.tomowork.oa.core.service.QueryService;

@Service
@Transactional
public class QueryServiceImpl implements QueryService {

	@SuppressWarnings("rawtypes")
	@Inject
	@Named("genericEntityDao")
	private GenericEntityDao geDao;

	@SuppressWarnings("rawtypes")
	public GenericEntityDao getGeDao() {
		return this.geDao;
	}

	@SuppressWarnings("rawtypes")
	public void setGeDao(GenericEntityDao geDao) {
		this.geDao = geDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(String scope, Map params, int page, int pageSize) {
		return this.geDao.query(scope, params, page, pageSize);
	}
}
