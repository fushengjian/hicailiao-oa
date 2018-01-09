package com.tomowork.oa.foundation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tomowork.oa.core.tools.CommUtil;
import com.tomowork.oa.core.tools.StringUtils;
import com.tomowork.oa.dao.GenericDAO;
import com.tomowork.oa.foundation.domain.User;
import com.tomowork.oa.foundation.service.SysConfigService;
import com.tomowork.oa.foundation.service.UserService;
import com.tomowork.oa.query.GenericPageList;
import com.tomowork.oa.query.support.PageList;
import com.tomowork.oa.query.support.QueryObject;
import com.tomowork.oa.tools.MsgTools;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private GenericDAO<User> userDAO;

	@Autowired
	private MsgTools msgTool;

	@Autowired
	private SysConfigService configService;

	@Value ("${assets.url}")
	private String miscWebServer;

	@Value ("${front.url}")
	private String frontUrl;

	public boolean delete(Long id) {
		try {
			this.userDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getObjById(Long id) {
		return this.userDAO.get(id);
	}

	public boolean save(User user) {
		try {
			this.userDAO.save(user);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	public boolean update(User user) {
		try {
			this.userDAO.update(user);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> query(String query, Map<String, Object> params,
			int begin, int max) {
		return this.userDAO.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<User> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<User> pList = new GenericPageList<>(User.class, properties,
				this.userDAO);
		pList.doList();
		return pList;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getObjByProperty(String propertyName, String value) {
		return this.userDAO.getBy(propertyName, value);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByUsername(String username) {
		return userDAO.getBy("userName", username);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isEmailExists(String email, String userId) {
		if (StringUtils.isEmpty(email)) { // 跳过前端校验的默认为已经存在
			return true;
		}
		email = email.trim();
		boolean ret = false;
		Map<String, Object> params = new HashMap<>();
		params.put("email", email);
		params.put("id", CommUtil.null2Long(userId));
		List<User> users = this
				.query("select obj from User obj where obj.email=:email and obj.id!=:id",
						params, -1, -1);
		if ((users != null) && (users.size() > 0)) {
			ret = true;
		}
		return ret;
	}
}
