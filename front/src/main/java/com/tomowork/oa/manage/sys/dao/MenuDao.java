package com.tomowork.oa.manage.sys.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.manage.common.persistence.BaseDao;
import com.tomowork.oa.manage.common.persistence.Parameter;
import com.tomowork.oa.manage.sys.entity.Dict;
import com.tomowork.oa.manage.sys.entity.Menu;


/**
 * 菜单DAO接口
 */
@Repository
public class MenuDao extends BaseDao<Menu> {

	public List<Menu> findAllActivitiList() {
		return find("from Menu where delFlag=:p1 and isActiviti = :p2 order by sort", new Parameter(Dict.DEL_FLAG_NORMAL, Menu.YES));
	}

	public List<Menu> findByParentIdsLike(String parentIds) {
		return find("from Menu where parentIds like :p1", new Parameter(parentIds));
	}

	public List<Menu> findAllList() {
		return find("from Menu where delFlag=:p1 order by sort", new Parameter(Dict.DEL_FLAG_NORMAL));
	}

	public List<Menu> findByUserId(Long userId) {
		return find("select distinct m from Menu m, ManageRole r, ManageUser u where m in elements (r.menuList) and r in elements (u.roleList)" +
				" and m.delFlag=:p1 and r.delFlag=:p1 and u.delFlag=:p1 and u.id=:p2" + // or (m.user.id=:p2  and m.delFlag=:p1)" +
				" order by m.sort", new Parameter(Menu.DEL_FLAG_NORMAL, userId));
	}

	public List<Menu> findAllActivitiList(String userId) {
		return find("select distinct m from Menu m, ManageRole r, ManageUser u where m in elements (r.menuList) and r in elements (u.roleList)" +
				" and m.delFlag=:p1 and r.delFlag=:p1 and u.delFlag=:p1 and m.isActiviti=:p2 and u.id=:p3 order by m.sort",
				new Parameter(Menu.DEL_FLAG_NORMAL, Menu.YES, userId));
	}

}
