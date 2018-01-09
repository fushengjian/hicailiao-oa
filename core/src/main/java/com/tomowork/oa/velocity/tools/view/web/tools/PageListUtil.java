package com.tomowork.oa.velocity.tools.view.web.tools;

import com.tomowork.oa.mv.Model;
import com.tomowork.oa.query.support.PageList;

/**
 * 分页工具类
 *
 * @author zlei
 *
 */
public class PageListUtil {

	@SuppressWarnings("rawtypes")
	public static void saveIPageList2ModelAndView(String url, String staticURL,
			String params, PageList pList, Model mv) {
		if (pList != null) {
			mv.addObject("objs", pList.getResult());
			mv.addObject("totalPage", new Integer(pList.getPages()));
			mv.addObject("pageSize", Integer.valueOf(pList.getPageSize()));
			mv.addObject("rows", new Integer(pList.getRowCount()));
			mv.addObject("currentPage", new Integer(pList.getCurrentPage()));
			mv.addObject(
					"gotoPageHTML",
					showPageHtml(url, params, pList.getCurrentPage(),
							pList.getPages()));
			mv.addObject("gotoPageFormHTML",
					showPageFormHtml(pList.getCurrentPage(), pList.getPages()));
			mv.addObject(
					"gotoPageStaticHTML",
					showPageStaticHtml(staticURL, pList.getCurrentPage(),
							pList.getPages()));
			mv.addObject(
					"gotoPageAjaxHTML",
					showPageAjaxHtml(url, params, pList.getCurrentPage(),
							pList.getPages()));
		}
	}

	public static String showPageStaticHtml(String url, int currentPage,
			int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "_1.htm'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a href='" + url + "_" + (currentPage - 1)
							+ ".htm'>上一页</a> ";
				}
			}
			int beginPage = (currentPage - 3 < 1) ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); ++j) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url + "_" + i
								+ ".htm'>" + i + "</a> ";
					else
						s = s + "<a href='" + url + "_" + i + ".htm'>" + i
								+ "</a> ";
					++i;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "_" + (currentPage + 1)
							+ ".htm'>下一页</a> ";
				}
				s = s + "<a href='" + url + "_" + pages + ".htm'>末页</a> ";
			}
		}
		return s;
	}

	public static String showPageHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s + "<a href='" + url + "?currentPage=1" + params
						+ "'>首页</a> ";
				if (currentPage > 1) {
					s = s + "<a href='" + url + "?currentPage="
							+ (currentPage - 1) + params + "'>上一页</a> ";
				}
			}
			int beginPage = (currentPage - 3 < 1) ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); ++j) {
					if (i == currentPage)
						s = s + "<a class='this' href='" + url
								+ "?currentPage=" + i + params + "'>" + i
								+ "</a> ";
					else
						s = s + "<a href='" + url + "?currentPage=" + i
								+ params + "'>" + i + "</a> ";
					++i;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s + "<a href='" + url + "?currentPage="
							+ (currentPage + 1) + params + "'>下一页</a> ";
				}
				s = s + "<a href='" + url + "?currentPage=" + pages + params
						+ "'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageFormHtml(int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage(1)'>首页</a> ";
				if (currentPage > 1) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage - 1) + ")'>上一页</a> ";
				}
			}
			int beginPage = (currentPage - 3 < 1) ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); ++j) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return gotoPage("
								+ i + ")'>" + i + "</a> ";
					++i;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				if (currentPage < pages) {
					s = s
							+ "<a href='javascript:void(0);' onclick='return gotoPage("
							+ (currentPage + 1) + ")'>下一页</a> ";
				}
				s = s
						+ "<a href='javascript:void(0);' onclick='return gotoPage("
						+ pages + ")'>末页</a> ";
			}
		}

		return s;
	}

	public static String showPageAjaxHtml(String url, String params,
			int currentPage, int pages) {
		String s = "";
		if (pages > 0) {
			String address = url + "?1=1" + params;
			if (currentPage >= 1) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\",1,this)'>首页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage - 1)
						+ ",this)'>上一页</a> ";
			}

			int beginPage = (currentPage - 3 < 1) ? 1 : currentPage - 3;
			if (beginPage <= pages) {
				s = s + "第　";
				int i = beginPage;
				for (int j = 0; (i <= pages) && (j < 6); ++j) {
					if (i == currentPage)
						s = s
								+ "<a class='this' href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					else
						s = s
								+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
								+ address + "\"," + i + ",this)'>" + i
								+ "</a> ";
					++i;
				}

				s = s + "页　";
			}
			if (currentPage <= pages) {
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + (currentPage + 1)
						+ ",this)'>下一页</a> ";
				s = s
						+ "<a href='javascript:void(0);' onclick='return ajaxPage(\""
						+ address + "\"," + pages + ",this)'>末页</a> ";
			}
		}

		return s;
	}
}
