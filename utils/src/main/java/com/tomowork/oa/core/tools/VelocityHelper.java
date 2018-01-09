package com.tomowork.oa.core.tools;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * Velocity 模版转换工具类
 *
 * @author zlei
 */
public class VelocityHelper {

	private static final VelocityEngine engine;

	static {
		VelocityEngine e = new VelocityEngine();

		e.setProperty(RuntimeConstants.INPUT_ENCODING, "UTF-8");
		e.setProperty(RuntimeConstants.OUTPUT_ENCODING, "UTF-8");
		e.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		e.setProperty("classpath.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		//控制不产生日志
		e.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
		e.init();

		engine = e;
	}

	public static String merge(String template, Map<String, Object> context) {
		VelocityContext ctx = new VelocityContext(context);

		StringWriter writer = new StringWriter(template.length() + 16 * context.size());
		engine.evaluate(ctx, writer, "VelocityHelper", template);

		return writer.toString();
	}

	/**
	 * 生成内容
	 * 仅支持简单的string解析
	 * @param templateFile
	 * @param params
	 */
	public static String generateContent(String templateFile,
			Map<String, Object> params) {
		Template t = engine.getTemplate("template/" + templateFile, "utf-8");
		VelocityContext ctx = new VelocityContext(params);
		StringWriter sw = new StringWriter();
		t.merge(ctx, sw);
		return sw.toString();
	}
}
