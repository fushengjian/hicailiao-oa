package com.tomowork.oa.view.web.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ALLOWDOMAINS = "allowDomains";

	private String[] allowDomains = {};

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String[] domains = allowDomains;

		resp.setContentType("text/x-cross-domain-policy");

		PrintWriter writer = resp.getWriter();
		writer.println("<?xml version=\"1.0\"?>");
		writer.println("<cross-domain-policy>");
		writer.println("<site-control permitted-cross-domain-policies=\"by-content-type\" />");

		for (String domain : domains) {
			writer.print("<allow-access-from domain=\"");
			writer.print(domain);
			writer.println("\" secure=\"true\" />");
		}

		writer.println("</cross-domain-policy>");
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		String s = config.getInitParameter(ALLOWDOMAINS);

		if (s != null) {
			s = s.trim();
			String[] arr = s.split(";");
			List<String> domains = new ArrayList<>(arr.length);
			for (String domain : arr) {
				if (domain != null && domain.trim().length() > 0) {
					domains.add(domain);
				}
			}

			allowDomains = domains.toArray(new String[domains.size()]);
		}
	}

	public void destroy() {
		allowDomains = null;
		super.destroy();
	}
}
