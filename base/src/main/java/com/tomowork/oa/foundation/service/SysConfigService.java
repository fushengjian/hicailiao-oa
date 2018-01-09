package com.tomowork.oa.foundation.service;

import com.tomowork.oa.foundation.domain.SysConfig;

public interface SysConfigService {
	boolean save(SysConfig paramSysConfig);

	boolean delete(SysConfig paramSysConfig);

	boolean update(SysConfig paramSysConfig);

	SysConfig getSysConfig();
}
