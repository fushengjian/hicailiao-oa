package com.tomowork.oa.view.web.tools;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.runtime.log.Log;
import org.apache.velocity.tools.config.DefaultKey;

import com.tomowork.oa.core.tools.StringUtils;
import com.tomowork.oa.foundation.domain.SysConfig;

/**
 * 全局工具类
 *
 * @author gaogh
 *
 */
@DefaultKey("globalTools")
public class GlobalTools {
	protected Log LOG;

	protected ServletContext application;

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	public void setLog(Log log) {
		if (log == null) {
			throw new NullPointerException("log should not be set to null");
		}
		this.LOG = log;
	}

	public void setRequest(HttpServletRequest request) {
		if (request == null) {
			throw new NullPointerException("request should not be null");
		}
		this.request = request;
	}

	public void setResponse(HttpServletResponse response) {
		if (response == null) {
			throw new NullPointerException("response should not be null");
		}
		this.response = response;
	}

	public void setServletContext(ServletContext application) {
		if (application == null) {
			throw new NullPointerException("servlet context should not be null");
		}
		this.application = application;
	}

	/**
	 * 获取系统全局设置
	 *
	 * @return
	 */
	public SysConfig getSysConfig() {
		if ((!(this.request instanceof HttpServletRequest))
				|| (!(this.response instanceof HttpServletResponse))) {
			LOG.error("Relative import from non-HTTP request not allowed");
			return null;
		}
		SysConfig config = (SysConfig) request.getAttribute("gobalConfig");
		if (config == null) {
			config = new SysConfig();
		}
		return config;
	}

	/**
	 * 用于判断字符串是否是空
	 * @param str
	 * @return
	 */
	public boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}

	/**
	 * 用于判断字符串是否是空
	 * @param str
	 * @return
	 */
	public boolean isNotEmpty(String str) {
		return StringUtils.isNotEmpty(str);
	}

	/**
	 * 字符加星号
	 * @param content
	 * @param index
	 * @param count
	 * @return
	 */
	public String asterisk(String content, int index, int count) {
		return StringUtils.asterisk(content, index, count);
	}
}
