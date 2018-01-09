package com.tomowork.oa.foundation.dao;

import org.springframework.stereotype.Repository;

import com.tomowork.oa.base.AbstractGenericDAO;
import com.tomowork.oa.foundation.domain.UserConfig;

@Repository ("userConfigDAO")
public class UserConfigDAO extends AbstractGenericDAO<UserConfig> {
}
