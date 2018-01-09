package com.tomowork.oa.core.web.servlet.view.velocity;

import java.io.StringWriter;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.core.NestedIOException;

/**
 * VelocityLayoutView emulates the functionality offered by Velocity's
 * VelocityLayoutServlet to ease page composition from different templates.
 *
 * @author zlei
 *
 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView
 */
public class VelocityLayoutView extends VelocityToolsView {

	/**
	 * The default {@link #setLayoutDir(String) layout dir}.
	 */
	public static final String DEFAULT_LAYOUT_DIR = "layout/";

	/**
	 * The default {@link #setDefaultLayout(String) layout layoutUrl}.
	 */
	public static final String DEFAULT_LAYOUT_URL = "Default.vm";

	/**
	 * The default {@link #setLayoutKey(String) layout key}.
	 */
	public static final String DEFAULT_LAYOUT_KEY = "layout";

	/**
	 * The default {@link #setScreenContentKey(String) screen content key}.
	 */
	public static final String DEFAULT_SCREEN_CONTENT_KEY = "screen_content";

	private String layoutDir = DEFAULT_LAYOUT_DIR;

	private String defaultLayout = DEFAULT_LAYOUT_URL;

	private String layoutKey = DEFAULT_LAYOUT_KEY;

	private String screenContentKey = DEFAULT_SCREEN_CONTENT_KEY;

	private boolean cacheLayoutTemplate = false;

	private final AtomicReference<Entry> layoutTemplateRef = new AtomicReference<>();


	/**
	 * Set the layout dir to use. Default is {@link #DEFAULT_LAYOUT_DIR "layout/"}.
	 * @param layoutDir the template dir (relative to the template root directory)
	 */
	public void setLayoutDir(String layoutDir) {
		if (!layoutDir.endsWith("/"))
			layoutDir += '/';

		this.layoutDir = layoutDir;
	}

	/**
	 * Set the layout template to use. Default is {@link #DEFAULT_LAYOUT_URL "Default.vm"}.
	 * @param defaultLayout the template location (relative to the template dir)
	 */
	public void setDefaultLayout(String defaultLayout) {
		this.defaultLayout = defaultLayout;
	}

	/**
	 * Set the context key used to specify an alternate layout to be used instead
	 * of the default layout. Screen content templates can override the layout
	 * template that they wish to be wrapped with by setting this value in the
	 * template, for example:<br>
	 * {@code #set($layout = "MyLayout.vm" )}
	 * <p>Default key is {@link #DEFAULT_LAYOUT_KEY "layout"}, as illustrated above.
	 * @param layoutKey the name of the key you wish to use in your
	 * screen content templates to override the layout template
	 */
	public void setLayoutKey(String layoutKey) {
		this.layoutKey = layoutKey;
	}

	/**
	 * Set the name of the context key that will hold the content of
	 * the screen within the layout template. This key must be present
	 * in the layout template for the current screen to be rendered.
	 * <p>Default is {@link #DEFAULT_SCREEN_CONTENT_KEY "screen_content"}:
	 * accessed in VTL as {@code $screen_content}.
	 * @param screenContentKey the name of the screen content key to use
	 */
	public void setScreenContentKey(String screenContentKey) {
		this.screenContentKey = screenContentKey;
	}

	/**
	 * Set whether the Velocity layout template should be cached. Default is "false".
	 * @param cacheLayoutTemplate whether cache layout template
	 */
	public void setCacheLayoutTemplate(boolean cacheLayoutTemplate) {
		this.cacheLayoutTemplate = true;
	}

	/**
	 * Overrides {@code VelocityView.checkTemplate()} to additionally check
	 * that both the layout template and the screen content template can be loaded.
	 * Note that during rendering of the screen content, the layout template
	 * can be changed which may invalidate any early checking done here.
	 */
	@Override
	public boolean checkResource(Locale locale) throws Exception {
		if (!super.checkResource(locale)) {
			return false;
		}

		try {
			// Check that we can get the template, even if we might subsequently get it again.
			getTemplate(this.layoutDir + this.defaultLayout);
			return true;
		} catch (ResourceNotFoundException ex) {
			throw new NestedIOException("Cannot find Velocity template for URL ["
					+ this.layoutDir + this.defaultLayout
					+ "]: Did you specify the correct resource loader path?", ex);
		} catch (Exception ex) {
			throw new NestedIOException(
					"Could not load Velocity template for URL [" + this.layoutDir + this.defaultLayout + "]", ex);
		}
	}

	/**
	 * Overrides the normal rendering process in order to pre-process the Context,
	 * merging it with the screen template into a single value (identified by the
	 * value of screenContentKey). The layout template is then merged with the
	 * modified Context in the super class.
	 */
	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		renderScreenContent(context);

		String layout = (String) context.get(this.layoutKey);
		if (layout != null) {
			if (logger.isDebugEnabled()) {
				logger.debug("Screen content template has requested layout [" + layout + "]");
			}
		} else {
			layout = this.defaultLayout;
		}

		mergeTemplate(getLayoutTemplate(layout), context, response);
	}

	private void renderScreenContent(Context velocityContext) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Rendering screen content template [" + getUrl() + "]");
		}

		StringWriter sw = new StringWriter();
		Template screenContentTemplate = getTemplate();
		screenContentTemplate.merge(velocityContext, sw);

		velocityContext.put(this.screenContentKey, sw.toString());
	}

	private Template getLayoutTemplate(String layoutUrl) throws Exception {
		if (cacheLayoutTemplate) {
			Entry entry = layoutTemplateRef.get();
			if (entry != null && layoutUrl.equals(entry.layoutUrl))
				return entry.template;

			Template layoutTemplate = getTemplate(this.layoutDir + layoutUrl);
			layoutTemplateRef.compareAndSet(entry, new Entry(layoutUrl, layoutTemplate));
			return layoutTemplate;
		} else
			return getTemplate(this.layoutDir + layoutUrl);
	}

	private static class Entry {
		String layoutUrl;

		Template template;

		Entry(String layoutUrl, Template template) {
			this.layoutUrl = layoutUrl;
			this.template = template;
		}
	}
}
