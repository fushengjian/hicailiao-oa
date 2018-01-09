package com.tomowork.oa.annotation;

public enum LogType {
	/**
	 * 新建保存/更新
	 */
	SAVE,

	/**
	 * 删除/批量删除
	 */
	REMOVE,

	/**
	 * 查阅
	 */
	LIST,

	/**
	 * 查阅单个记录
	 */
	VIEW,

	/**
	 * 登录系统
	 */
	LOGIN,

	/**
	 * 退出系统
	 */
	LOGOUT,

	/**
	 * 还原系统数据
	 */
	RESTORE,

	/**
	 * 从本地导入数据覆盖系统数据库
	 */
	IMPORT,

	/**
	 * 发送站内短信息
	 */
	SEND,

	/**
	 * 修改密码
	 */
	UPDATEPWS,

	/**
	 * not used
	 */
	ROLE
}
