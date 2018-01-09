package com.tomowork.oa.foundation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.Area;
import com.tomowork.oa.foundation.service.AreaService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	@Resource(name = "areaDAO")
	private GenericDAO<Area> areaDao;

	public boolean save(Area area) {
		try {
			this.areaDao.save(area);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public Area getObjById(Long id) {
		Area area = this.areaDao.get(id);
		if (area != null) {
			return area;
		}
		return null;
	}

	public boolean delete(Long id) {
		try {
			this.areaDao.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean batchDelete(List<Serializable> areaIds) {
		for (Serializable id : areaIds) {
			delete((Long) id);
		}
		return true;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Area> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Area> pList = new GenericPageList<>(Area.class, properties,
				this.areaDao);
		pList.doList();
		return pList;
	}

	public boolean update(Area area) {
		try {
			this.areaDao.update(area);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Area> query(String query, Map<String, Object> params,
			int begin, int max) {
		return this.areaDao.query(query, params, begin, max);
	}

	/**
	 * 返回所有区域信息
	 *
	 * @return
	 */
	public List<Area> findAll() {
		return this.areaDao.query("from Area order by sequence", null, 1,
				Integer.MAX_VALUE);
	}
}
