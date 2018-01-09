package com.tomowork.oa.core.web.servlet.view.velocity;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

/**
 * Convenience subclass of VelocityViewResolver, adding support
 * for VelocityLayoutView and its properties.
 *
 * @author zlei
 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver
 */
public class VelocityLayoutViewResolver extends VelocityViewResolver {

	private String layoutDir;

	private String defaultLayout;

	private String layoutKey;

	private String screenContentKey;

	private boolean cacheTemplate;

	private boolean cacheLayoutTemplate;

	/**
	 * Requires VelocityLayoutView.
	 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView
	 */
	@Override
	protected Class<?> requiredViewClass() {
		return VelocityLayoutView.class;
	}

	/**
	 * Set the layout template dir to use. Default is "layout/".
	 * @param layoutDir the template location (relative to the template root directory)
	 */
	public void setLayoutDir(String layoutDir) {
		this.layoutDir = layoutDir;
	}

	/**
	 * Set the layout template to use. Default is "Default.vm".
	 * @param defaultLayout the template location (relative to the template layout directory)
	 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView#setLayoutUrl
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
	 * <p>The default key is "layout", as illustrated above.
	 * @param layoutKey the name of the key you wish to use in your
	 * screen content templates to override the layout template
	 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView#setLayoutKey
	 */
	public void setLayoutKey(String layoutKey) {
		this.layoutKey = layoutKey;
	}

	/**
	 * Set the name of the context key that will hold the content of
	 * the screen within the layout template. This key must be present
	 * in the layout template for the current screen to be rendered.
	 * <p>Default is "screen_content": accessed in VTL as
	 * {@code $screen_content}.
	 * @param screenContentKey the name of the screen content key to use
	 * @see org.springframework.web.servlet.view.velocity.VelocityLayoutView#setScreenContentKey
	 */
	public void setScreenContentKey(String screenContentKey) {
		this.screenContentKey = screenContentKey;
	}

	public boolean isCacheTemplate() {
		return cacheTemplate;
	}

	/**
	 * Set whether the Velocity template should be cached. Default is "false".
	 * @param cacheTemplate whether cache template
	 */
	public void setCacheTemplate(boolean cacheTemplate) {
		this.cacheTemplate = cacheTemplate;
	}

	public boolean isCacheLayoutTemplate() {
		return cacheLayoutTemplate;
	}

	/**
	 * Set whether the Velocity layout template should be cached. Default is "false".
	 * @param cacheLayoutTemplate whether cache layout template
	 */
	public void setCacheLayoutTemplate(boolean cacheLayoutTemplate) {
		this.cacheLayoutTemplate = cacheLayoutTemplate;
	}


	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityLayoutView view = (VelocityLayoutView) super.buildView(viewName);
		// Use not-null checks to preserve VelocityLayoutView's defaults.
		if (this.layoutDir != null) {
			view.setLayoutDir(this.layoutDir);
		}
		if (this.defaultLayout != null) {
			view.setDefaultLayout(this.defaultLayout);
		}
		if (this.layoutKey != null) {
			view.setLayoutKey(this.layoutKey);
		}
		if (this.screenContentKey != null) {
			view.setScreenContentKey(this.screenContentKey);
		}
		view.setCacheTemplate(this.cacheTemplate);
		view.setCacheLayoutTemplate(this.cacheLayoutTemplate);
		return view;
	}
}
