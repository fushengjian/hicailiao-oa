package com.tomowork.oa.view.web.tools;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import com.tomowork.oa.foundation.domain.Accessory;
import com.tomowork.oa.foundation.domain.SysConfig;
import com.tomowork.oa.foundation.service.SysConfigService;
import com.tomowork.oa.velocity.tools.view.URIFactory;

import org.springframework.beans.factory.annotation.Value;


@Named
public class ImageViewTools {
	private static volatile ImageViewTools instance;

	@Inject
	private SysConfigService configService;

	@Inject
	@Named("imageUriFactory")
	private URIFactory imageUriFactory;

	@Value ("${images.url}")
	private String imagesUrl;

	private volatile String[] imageWebServers;

	public String random_login_img(String fallbackUrl) {
		String img = "";
		SysConfig config = this.configService.getSysConfig();
		if (config.getLogin_imgs().size() > 0) {
			Random random = new Random();
			Accessory acc = config.getLogin_imgs().get(
					random.nextInt(config.getLogin_imgs().size()));
			img = imageUrl(fallbackUrl, acc);
		}
		return img;
	}

	public String imageUrl(String fallbackUrl, Accessory accessory) {
		return imageUrl(fallbackUrl, accessory, null);
	}

	public String imageUrl(String fallbackUrl, Accessory accessory, String suffix) {
		StringBuilder sb = new StringBuilder(128);

		sb.append('/');
		sb.append(accessory.getUrl()); // store/32769/2015/04/30/08820626-ba8e-4f73-b924-0434c472a16b.png

		if (suffix != null && !suffix.trim().isEmpty()) {
			sb.append('_');
			sb.append(suffix.trim());
			sb.append('.');
			sb.append(accessory.getExt());
		}

		URI uri = imageUriFactory.createURI(sb.toString(), null, null);
		return uri.toString();
	}

	public String imageUrl(String fallbackUrl, String path, String suffix) {
		StringBuilder sb = new StringBuilder(128);

		sb.append('/');
		sb.append(path); // store/32769/2015/04/30/08820626-ba8e-4f73-b924-0434c472a16b.png

		if (suffix != null && !suffix.trim().isEmpty()) {
			sb.append('_');
			sb.append(suffix.trim());
		}

		URI uri = imageUriFactory.createURI(sb.toString(), null, null);
		return uri.toString();
	}

	public String imageUrl(String fallbackUrl, String path) {
		return imageUrl(fallbackUrl, path, null);
	}


	public String dnsPrefetch() {
		String[] servers = imageWebServers;
		if (servers == null) {
			servers = processUrl(imagesUrl);
			imageWebServers = servers;
		}

		StringBuilder sb = new StringBuilder(128 + 64 * servers.length);
		for (String s : servers) {
			// <link rel="dns-prefetch" href="//i13.clcache.com">

			sb.append("<link rel=\"dns-prefetch\" href=\"");
			sb.append(s);
			sb.append("\">\r\n");
		}
		return sb.toString();
	}

	@PostConstruct
	public void init() {
		instance = this;
	}

	private String[] processUrl(String url) {
		String[] servers = url.trim().split(";");
		List<String> list = new ArrayList<>(servers.length);
		for (String s : servers) {
			s = s.trim();
			if (!s.isEmpty())
				list.add(s);
		}
		return list.toArray(new String[list.size()]);
	}

	@PreDestroy
	public void preDestory() {
		instance = null;
	}

	public static ImageViewTools getInstance() {
		if (instance == null)
			throw new IllegalStateException("ImageViewTools not initialized or is already destoryed");
		return instance;
	}
}
