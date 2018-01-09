package com.tomowork.oa.manage.admin.loader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tomowork.oa.velocity.tools.view.URIFactory;


/**
 * 注入 UriFactory
 *
 * @author zlei
 */
public class ServletContextLoaderListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);

		URIFactory frontUriFactory = wac.getBean("frontUriFactory", URIFactory.class);
		if (frontUriFactory == null)
			throw new IllegalStateException("no bean frontUriFactory found");

		URIFactory assetsUriFactory = wac.getBean("assetsUriFactory", URIFactory.class);
		if (assetsUriFactory == null)
			throw new IllegalStateException("no bean assetsUriFactory found");

		URIFactory imageUriFactory = wac.getBean("imageUriFactory", URIFactory.class);
		if (imageUriFactory == null)
			throw new IllegalStateException("no bean imageUriFactory found");

		servletContext.setAttribute("frontUriFactory", frontUriFactory);
		servletContext.setAttribute("assetsUriFactory", assetsUriFactory);
		servletContext.setAttribute("imageUriFactory", imageUriFactory);
	}

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute("imageUriFactory");
		servletContext.removeAttribute("assetsUriFactory");
		servletContext.removeAttribute("frontUriFactory");
	}
}
