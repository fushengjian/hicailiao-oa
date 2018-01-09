package com.tomowork.oa.view.web.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Cookie工具类
 *
 * @author zlei
 *
 */
public class CookieUtil {

	public static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					String value = cookie.getValue();
					if (value != null)
						value = value.trim();

					return value;
				}
			}
		}
		return null;
	}
}
