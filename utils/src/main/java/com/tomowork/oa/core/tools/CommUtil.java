package com.tomowork.oa.core.tools;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.tomowork.oa.assets.ImageManager;
import com.tomowork.oa.assets.RemoteFSManager;
import com.tomowork.oa.assets.tools.ImageInfo;
import com.tomowork.oa.assets.tools.ImageInfoTools;
import com.tomowork.oa.assets.tools.ImageType;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Named
public class CommUtil {
	private static final Logger log = LoggerFactory.getLogger(CommUtil.class);

	private static final ThreadLocal<DateFormat> dateFormat = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private static final Whitelist user_content_filter = Whitelist.relaxed();

	private static volatile CommUtil instance;

	@Inject
	private RemoteFSManager fsManager;

	@Inject
	private ImageManager imageManager;

	static {
		user_content_filter.addTags(new String[] { "embed", "object", "param",
				"span", "div", "font" });
		user_content_filter.addAttributes(":all", new String[] { "style",
				"class", "id", "name" });
		user_content_filter.addAttributes("object", new String[] { "width",
				"height", "classid", "codebase" });
		user_content_filter.addAttributes("param", new String[] { "name",
				"value" });
		user_content_filter.addAttributes("embed",
				new String[] { "src", "quality", "width", "height",
						"allowFullScreen", "allowScriptAccess", "flashvars",
						"name", "type", "pluginspage" });

		//instance = new CommUtil();
	}

	public CommUtil() {
	}

	@PostConstruct
	public void postConstruct() {
		instance = this;
	}

	public static CommUtil getInstance() {
		return instance;
	}

	public static String first2low(String str) {
		String s = "";
		s = str.substring(0, 1).toLowerCase() + str.substring(1);
		return s;
	}

	public static String first2upper(String str) {
		String s = "";
		s = str.substring(0, 1).toUpperCase() + str.substring(1);
		return s;
	}

	public static List<String> str2list(String s) throws IOException {
		List<String> list = new ArrayList<>();
		if ((s != null) && (!(s.equals("")))) {
			StringReader fr = new StringReader(s);
			BufferedReader br = new BufferedReader(fr);
			String aline = "";
			while ((aline = br.readLine()) != null) {
				list.add(aline);
			}
		}
		return list;
	}

	public static Date formatDate(String s) {
		Date d = null;
		try {
			d = dateFormat.get().parse(s);
		} catch (Exception localException) {
		}
		return d;
	}

	public static Date formatDate(String s, String format) {
		Date d = null;
		try {
			SimpleDateFormat dFormat = new SimpleDateFormat(format);
			d = dFormat.parse(s);
		} catch (Exception localException) {
		}
		return d;
	}

	public static String formatTime(String format, Object v) {
		if (v == null)
			return null;
		if (v.equals(""))
			return "";
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(v);
	}

	public static String formatLongDate(Object v) {
		if ((v == null) || (v.equals("")))
			return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(v);
	}

	public static String formatShortDate(Object v) {
		if (v == null)
			return null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(v);
	}

	public static Date nextDayMidnight(Date current) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.DATE, 1);

		Calendar calnew = Calendar.getInstance();
		calnew.clear();
		calnew.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

		return calnew.getTime();
	}

	public static String decode(String s) {
		String ret = s;
		try {
			ret = URLDecoder.decode(s.trim(), "UTF-8");
		} catch (Exception localException) {
		}
		return ret;
	}

	public static String encode(String s) {
		String ret = s;
		try {
			ret = URLEncoder.encode(s.trim(), "UTF-8");
		} catch (Exception localException) {
		}
		return ret;
	}

	public static String convert(String str, String coding) {
		String newStr = "";
		if (str != null)
			try {
				newStr = new String(str.getBytes("ISO-8859-1"), coding);
			} catch (Exception e) {
				return newStr;
			}
		return newStr;
	}

	public static boolean deleteFileFromServer(String remotePath) {
		try {
			instance.fsManager.delete(remotePath);
			return true;
		} catch (IOException e) {
			log.error("error delete remote file: " + remotePath, e);
		}
		return false;
	}

	public static Map<String, Object> saveFileToServer(HttpServletRequest request,
			String filePath, String saveFilePathName, String saveFileName,
			String[] extendes) throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile(filePath);
		Map<String, Object> map = new HashMap<>();

		map.put("width", Integer.valueOf(0));
		map.put("height", Integer.valueOf(0));
		map.put("mime", "");
		map.put("fileName", "");
		map.put("fileSize", Float.valueOf(0));

		if ((file != null) && (!(file.isEmpty()))) {
			try (InputStream is = file.getInputStream()) {
				Map<String, Object> r = saveFileToServer(saveFilePathName, saveFileName, is, extendes);
				r.put("oldName", file.getOriginalFilename());

				map.putAll(r);
			}
		}
		return map;
	}

	public static Map<String, Object> saveFileToServer(String path, String saveFileName, InputStream is, String[] extendes) throws IOException {
		List<String> errors = new ArrayList<>();

		Map<String, Object> map = new HashMap<>();

		map.put("error", errors);

		Path tmp = null;
		try {
			tmp = createTempFile(is);

			ImageInfo info = ImageInfoTools.getImageInfo(tmp);

			if (info == null)
				throw new IOException("unsupported image file");

			boolean isValidExtend = true;
			if (extendes != null) {
				isValidExtend = false;
				for (String s : extendes) {
					ImageType type = ImageType.typeOf(s);
					if (info.getType() == type) {
						isValidExtend = true;
						break;
					}
				}
			}

			if (!isValidExtend)
				throw new IOException("denied image file type " + info.getType().toString().toLowerCase());

			if (saveFileName == null || saveFileName.trim().isEmpty()) {
				saveFileName = UUID.randomUUID().toString() + "." + info.getType().toString().toLowerCase();
			}

			// FIXME get remote file system pathSeparator, by hzl
			try (OutputStream os = getInstance().fsManager.newRMIOutputStream(path + File.separator + saveFileName, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
					InputStream is2 = Files.newInputStream(tmp, StandardOpenOption.READ)) {
				copy(is2, os);

				info.setName(saveFileName);

				map.put("mime", info.getType().toString().toLowerCase());
				map.put("fileName", info.getName());
				map.put("fileSize", Float.valueOf(info.getSize()));
				map.put("width", Integer.valueOf((int)info.getWidth()));
				map.put("height", Integer.valueOf((int)info.getHeight()));

			}

		} catch (IOException e) {
			throw e;
		} finally {
			if (tmp != null) {
				try {
					Files.delete(tmp);
				} catch (IOException e2) { // ignore
				}
			}
		}
		return map;
	}

	private static Path createTempFile(InputStream in) throws IOException {
		Path tmp = Files.createTempFile("image", "tmp");

		try (OutputStream out = Files.newOutputStream(tmp,
				StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
			copy(in, out);
		}

		return tmp;
	}

	private static long copy(InputStream source, OutputStream sink)
			throws IOException {
		long nread = 0L;
		byte[] buf = new byte[8192];
		int n;
		while ((n = source.read(buf)) > 0) {
			sink.write(buf, 0, n);
			nread += n;
		}
		return nread;
	}

	public static boolean isImg(String extend) {
		boolean ret = false;
		List<String> list = new ArrayList<>();

		list.add("jpg");
		list.add("jpeg");
		list.add("bmp");
		list.add("gif");
		list.add("png");
		list.add("tif");
		for (String s : list) {
			if (s.equals(extend))
				ret = true;
		}
		return ret;
	}

	public static final boolean waterMarkWithImage(String pressImg,
			String targetImg, int pos, float alpha) {
		try {
			instance.imageManager.waterMarkWithImage(pressImg, targetImg, pos, alpha);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	//创建小图
	public static boolean createSmall(String source, String target, int width,
			int height) {
		try {
			instance.imageManager.imageScale(source, target, width, height);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean waterMarkWithText(String filePath, String outPath,
			String text, String markContentColor, Font font, int pos,
			float qualNum) {
		try {
			instance.imageManager.waterMarkWithText(filePath, outPath, text, getColor(markContentColor), font, pos, qualNum);
			return true;
		} catch (IOException e) {
			log.error("error waterMarkWithText", e);
		}
		return false;
	}

	public static boolean waterMarkWithText(String filePath, String outPath, String text, String markContentColor, Font font, int left, int top, float qualNum) {
		try {
			instance.imageManager.waterMarkWithText(filePath, outPath, text, getColor(markContentColor), font, left, top, qualNum);
			return true;
		} catch (IOException e) {
			log.error("error waterMarkWithText", e);
		}
		return false;
	}

	public static boolean createFolder(String folderPath) {
		boolean ret = true;
		try {
			instance.fsManager.createDirectories(folderPath);
		} catch (Exception e) {
			log.error("创建文件夹出错", e);
			ret = false;
		}
		return ret;
	}

	public static <E> List<List<E>> toRowChildList(List<E> list, int perNum) {
		List<List<E>> l = new ArrayList<>();
		if (list == null) {
			return l;
		}

		for (int i = 0; i < list.size(); i += perNum) {
			List<E> cList = new ArrayList<>();
			for (int j = 0; j < perNum; ++j) {
				if (i + j < list.size())
					cList.add(list.get(i + j));
			}
			l.add(cList);
		}
		return l;
	}

	public static <E> List<E> copyList(List<E> list, int begin, int end) {
		List<E> l = new ArrayList<>();
		if (list == null)
			return l;
		if (end > list.size())
			end = list.size();

		for (int i = begin; i < end; i++) {
			l.add(list.get(i));
		}
		return l;
	}

	public static boolean isNotNull(Object obj) {
		return (obj != null) && (!(obj.toString().equals("")));
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];

				while ((byteread = inStream.read(buffer)) != -1) {
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();
		}
	}

	public static boolean deleteFolder(String path) {
		boolean flag = false;
		File file = new File(path);

		if (!(file.exists())) {
			return flag;
		}

		if (file.isFile()) {
			return deleteFile(path);
		}
		return deleteDirectory(path);
	}

	public static boolean deleteFile(String path) {
		boolean flag = false;
		File file = new File(path);

		if ((file.isFile()) && (file.exists())) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	public static boolean deleteDirectory(String path) {
		if (!(path.endsWith(File.separator))) {
			path = path + File.separator;
		}
		File dirFile = new File(path);

		if ((!(dirFile.exists())) || (!(dirFile.isDirectory()))) {
			return false;
		}
		boolean flag = true;

		File[] files = dirFile.listFiles();

		for (int i = 0; i < files.length; i++) {
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (flag)
					continue;
				break;
			}

			flag = deleteDirectory(files[i].getAbsolutePath());
			if (!flag) {
				break;
			}
		}
		if (!flag) {
			return false;
		}

		return dirFile.delete();
	}

	public static char randomChar() {
		char[] chars = { 'a', 'A', 'b', 'B', 'c', 'C', 'd', 'D', 'e', 'E', 'f',
				'F', 'g', 'G', 'h', 'H', 'i', 'I', 'j', 'J', 'k', 'K', 'l',
				'L', 'm', 'M', 'n', 'N', 'o', 'O', 'p', 'P', 'q', 'Q', 'r',
				'R', 's', 'S', 't', 'T', 'u', 'U', 'v', 'V', 'w', 'W', 'x',
				'X', 'y', 'Y', 'z', 'Z' };
		int index = (int) (Math.random() * 52.0D) - 1;
		if (index < 0) {
			index = 0;
		}
		return chars[index];
	}

	public static String[] splitByChar(String s, String c) {
		String[] list = s.split(c);
		return list;
	}

	public static Object requestByParam(HttpServletRequest request, String param) {
		if (!(request.getParameter(param).equals(""))) {
			return request.getParameter(param);
		}
		return null;
	}

	public static String substring(String s, int maxLength) {
		if (s == null || s.isEmpty())
			return s;
		if (s.length() <= maxLength) {
			return s;
		}
		return s.substring(0, maxLength) + "...";
	}

	public static String substringfrom(String s, String from) {
		if (s.indexOf(from) < 0)
			return "";
		return s.substring(s.indexOf(from) + from.length());
	}

	public static int null2Int(Object s) {
		int v = 0;
		if (s != null)
			try {
				v = Integer.parseInt(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static float null2Float(Object s) {
		float v = 0.0F;
		if (s != null)
			try {
				v = Float.parseFloat(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static double null2Double(Object s) {
		double v = 0.0D;
		if (s != null)
			try {
				v = Double.parseDouble(null2String(s));
			} catch (Exception localException) {
			}
		return v;
	}

	public static boolean null2Boolean(Object s) {
		boolean v = false;
		if (s != null)
			try {
				v = Boolean.parseBoolean(s.toString());
			} catch (Exception localException) {
			}
		return v;
	}

	public static String null2String(Object s) {
		return (s == null) ? "" : s.toString().trim();
	}

	public static Long null2Long(Object s) {
		Long v = Long.valueOf(-1L);
		if (s != null)
			try {
				v = Long.valueOf(Long.parseLong(s.toString()));
			} catch (Exception localException) {
			}
		return v;
	}

	public static String getTimeInfo(long time) {
		int hour = (int) time / 3600000;
		long balance = time - (hour * 1000 * 60 * 60);
		int minute = (int) balance / 60000;
		balance -= minute * 1000 * 60;
		int seconds = (int) balance / 1000;
		String ret = "";
		if (hour > 0)
			ret = ret + hour + "小时";
		if (minute > 0)
			ret = ret + minute + "分";
		else if ((minute <= 0) && (seconds > 0))
			ret = ret + "零";
		if (seconds > 0)
			ret = ret + seconds + "秒";
		return ret;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0)
				|| ("unknown".equalsIgnoreCase(ip))) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static int indexOf(String s, String sub) {
		return s.trim().indexOf(sub.trim());
	}

	public static Map<String, Long> cal_time_space(Date begin, Date end) {
		long l = end.getTime() - begin.getTime();
		long day = l / 86400000L;
		long hour = l / 3600000L - (day * 24L);
		long min = l / 60000L - (day * 24L * 60L) - (hour * 60L);
		long second = l / 1000L - (day * 24L * 60L * 60L) - (hour * 60L * 60L)
				- (min * 60L);
		Map<String, Long> map = new HashMap<>();
		map.put("day", Long.valueOf(day));
		map.put("hour", Long.valueOf(hour));
		map.put("min", Long.valueOf(min));
		map.put("second", Long.valueOf(second));
		return map;
	}

	public static final String randomString(int length) {
		char[] numbersAndLetters = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				.toCharArray();
		if (length < 1) {
			return "";
		}
		Random randGen = new Random();
		char[] randBuffer = new char[length];

		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomInt(int length) {
		if (length < 1) {
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters = "0123456789".toCharArray();

		char[] randBuffer = new char[length];

		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(10)];
		}
		return new String(randBuffer);
	}

	public static long getDateDistance(String time1, String time2) {
		long quot = 0L;
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = ft.parse(time1);
			Date date2 = ft.parse(time2);
			quot = date1.getTime() - date2.getTime();
			quot = quot / 1000L / 60L / 60L / 24L;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return quot;
	}

	public static double div(Object a, Object b) {
		double ret = 0.0D;
		if ((!(null2String(a).equals(""))) && (!(null2String(b).equals("")))) {
			BigDecimal e = new BigDecimal(null2String(a));
			BigDecimal f = new BigDecimal(null2String(b));
			if (null2Double(f) > 0.0D)
				ret = e.divide(f, 3, 1).doubleValue();
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double subtract(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.subtract(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double add(Object a, Object b) {
		double ret = 0.0D;
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		ret = e.add(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static BigDecimal addBigDecimal(BigDecimal a, BigDecimal b) {
		BigDecimal ret = a.add(b);
		return ret;
	}

	public static BigDecimal subtractBigDecimal(BigDecimal a, BigDecimal b) {
		BigDecimal ret = a.subtract(b);
		return ret;
	}




	public static double mul(Object a, Object b) {
		BigDecimal e = new BigDecimal(null2Double(a));
		BigDecimal f = new BigDecimal(null2Double(b));
		double ret = e.multiply(f).doubleValue();
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(ret)).doubleValue();
	}

	public static double formatMoney(Object money) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Double.valueOf(df.format(money)).doubleValue();
	}

	public static int M2byte(float m) {
		float a = m * 1024.0F * 1024.0F;
		return (int) a;
	}

	public static boolean convertIntToBoolean(int intValue) {
		return intValue != 0;
	}

	public static String getURL(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder(32);

		sb.append(request.getScheme());
		sb.append("://");
		sb.append(request.getServerName());

		int port = request.getServerPort();
		boolean isSecure = request.isSecure();
		if (!isSecure && port != 80 || isSecure && port != 443) {
			sb.append(':');
			sb.append(port);
		}

		String contextPath = request.getContextPath();
		if (contextPath.length() > 1) {
			sb.append(contextPath);
		}

		return sb.toString();
	}

	public static String filterHTML(String content) {
		String s = Jsoup.clean(content, user_content_filter);
		return s;
	}

	public static int parseDate(String type, Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (type.equals("y")) {
			return cal.get(1);
		}
		if (type.equals("M")) {
			return cal.get(2) + 1;
		}
		if (type.equals("d")) {
			return cal.get(5);
		}
		if (type.equals("H")) {
			return cal.get(11);
		}
		if (type.equals("m")) {
			return cal.get(12);
		}
		if (type.equals("s")) {
			return cal.get(13);
		}
		return 0;
	}

	public static boolean fileExist(String path) throws IOException {
		return instance.fsManager.exist(path, LinkOption.NOFOLLOW_LINKS);
	}

	public static int splitLength(String s, String c) {
		int v = 0;
		if (!(s.trim().equals(""))) {
			v = s.split(c).length;
		}
		return v;
	}

	public static double fileSize(String remoteFolder) throws IOException {
		return 1.0 * instance.fsManager.folderSize(remoteFolder) / 1024;
	}

	public static OutputStream newRemoteFileOutputStream(String remoteFile, OpenOption... options) throws IOException {
		return instance.fsManager.newRMIOutputStream(remoteFile, options);
	}

	public static int fileCount(File file) {
		if (file == null) {
			return 0;
		}
		if (!(file.isDirectory())) {
			return 1;
		}
		int fileCount = 0;
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.isFile()) {
				++fileCount;
			} else if (f.isDirectory()) {
				++fileCount;
				fileCount += fileCount(file);
			}
		}
		return fileCount;
	}

	public static String get_all_url(HttpServletRequest request) {
		String query_url = request.getRequestURI();
		if ((request.getQueryString() != null)
				&& (!(request.getQueryString().equals("")))) {
			query_url = query_url + "?" + request.getQueryString();
		}
		return query_url;
	}

	public static Color getColor(String color) {
		if (color.charAt(0) == '#') {
			color = color.substring(1);
		}
		if (color.length() != 6)
			return null;
		try {
			int r = Integer.parseInt(color.substring(0, 2), 16);
			int g = Integer.parseInt(color.substring(2, 4), 16);
			int b = Integer.parseInt(color.substring(4), 16);
			return new Color(r, g, b);
		} catch (NumberFormatException nfe) {
		}
		return null;
	}

	public static Set<Integer> randomInt(int a, int length) {
		Set<Integer> list = new TreeSet<>();
		int size = length;
		if (length > a) {
			size = a;
		}
		Random random = new Random();
		while (list.size() < size) {
			int b = random.nextInt(a);
			list.add(Integer.valueOf(b));
		}
		return list;
	}

	public static Double formatDouble(Object obj, int len) {
		String format = "0.0";
		for (int i = 1; i < len; i++) {
			format = format + "0";
		}
		DecimalFormat df = new DecimalFormat(format);
		return Double.valueOf(df.format(obj));
	}

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

		return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS)
				|| (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A)
				|| (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
				|| (ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) || (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
	}

	public static boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0.0F;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if ((Character.isLetterOrDigit(c)) || (isChinese(c)))
				continue;
			count += 1.0F;
		}

		float result = count / chLength;

		return result > 0.4D;
	}

	public static String trimSpaces(String IP) {
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}

	// FIXME IP 检测 by hzl 2015/6/10
	public static boolean isIp(String IP) {
		boolean b = false;
		IP = trimSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String[] s = IP.split("\\.");
			if ((Integer.parseInt(s[0]) < 255)
					&& (Integer.parseInt(s[1]) < 255)
					&& (Integer.parseInt(s[2]) < 255)
					&& (Integer.parseInt(s[3]) < 255))
				b = true;
		}
		return b;
	}

	// FIXME 生成二级域名算法bug处理 by hzl 2015/6/10
	public static String generic_domain(HttpServletRequest request) {
		String system_domain = "localhost";
		String serverName = request.getServerName();
		if (isIp(serverName))
			system_domain = serverName;
		else {
			system_domain = serverName.substring(serverName.indexOf(".") + 1);
		}

		return system_domain;
	}

	public static Date trimToMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.setTime(date);

		cal2.clear();
		cal2.setTimeZone(cal.getTimeZone());
		cal2.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

		return cal2.getTime();
	}
}
