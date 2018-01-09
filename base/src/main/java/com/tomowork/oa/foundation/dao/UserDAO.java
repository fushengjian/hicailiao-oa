package com.tomowork.oa.foundation.dao;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.base.AbstractGenericDAO;
import com.tomowork.oa.foundation.domain.User;

@Repository ("userDAO")
public class UserDAO extends AbstractGenericDAO<User> {
}
