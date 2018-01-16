package com.tomowork.oa.core.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Named;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class HttpClient {

	private static final Logger log = LoggerFactory.getLogger(HttpClient.class);

	public Map HttpGet(String url, Map<String, String> params, Map<String, String> header) {
		DefaultHttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager());
		HttpGet httpGet;
		if (params.size() > 0) {
			httpGet = new HttpGet(url + "?" + HttpGetParas(params));
		} else {
			httpGet = new HttpGet(url);
		}
		addHeader(httpGet, header);
		return httpClient(client, httpGet);
	}

	private String HttpGetParas(Map<String, String> params) {
		String parasString = "";
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			parasString = parasString + entry.getKey() + "=" + entry.getValue();
			if (it.hasNext()) {
				parasString = parasString + "&";
			}
		}
		return parasString;
	}

	private void addHeader(HttpRequestBase httpRequestBase, Map<String, String> header) {
		for (Map.Entry<String, String> entry: header.entrySet()) {
			httpRequestBase.addHeader(entry.getKey(), entry.getValue());
		}
	}

	private Map httpClient(DefaultHttpClient client, HttpGet httpGet) {
		Map<String, Object> map = new HashMap<>();
		HttpContext httpContext = new BasicHttpContext();
		try {
			// 将HttpContext对象作为参数传给execute()方法,则HttpClient会把请求响应交互过程中的状态信息存储在HttpContext中
			HttpResponse response = client.execute(httpGet, httpContext);
			if (response.getStatusLine().getStatusCode() == 200) {
				String result = EntityUtils.toString(response.getEntity());
				// 生成 JSON 对象
				map = Json.fromJson(Map.class, result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.getConnectionManager().shutdown();
		}
		return map;
	}
}
