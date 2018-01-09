package com.tomowork.oa.manage.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.manage.common.persistence.BaseDao;
import com.tomowork.oa.manage.common.persistence.Parameter;
import com.tomowork.oa.manage.sys.entity.Office;

/**
 * 机构DAO接口
 */
@Repository
public class OfficeDao extends BaseDao<Office> {

	public List<Office> findByParentIdsLike(String parentIds) {
		return find("from Office where parentIds like :p1", new Parameter(parentIds));
	}
}
