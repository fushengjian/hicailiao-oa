package com.tomowork.oa.core.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置
 *
 * @author qml
 */
// FIXME 线程安全
public class Global {
	/**
	 * 保存全局属性变更
	 */
	private static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader(
			"config.properties");

	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null) {
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/**
	 * 系统全局编码
	 */
	public static String GLOBAL_ENCODING = "utf-8";

}
