package com.tomowork.oa.view.web.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IpAddress {
	private static final Logger log = LoggerFactory.getLogger(IpAddress.class);

	private static final Charset charset = Charset.forName("GB18030");

	private static final IpAddress instance = new IpAddress();

	private RandomAccessFile cachedIpFile = null;

	private long ipBegin = 0L;

	private long ipEnd = 0L;

	private long ipSum = 0L;

	private String country = "";

	/*private String area = "";

	private static final int RECORD_LENGTH = 7;
	private static final byte AREA_FOLLOWED = 1;
	private static final byte NO_AREA = 2;
	*/

	private IpAddress() {
		InputStream iis = IpAddress.class.getResourceAsStream("/QQWry.dat");
		if (iis == null) {
			log.warn("IP地址信息文件没有找到，IP显示功能将无法使用");
			return;
		}

		final File dataFile;
		try (InputStream is = iis) {
			Path tmp = Files.createTempFile("QQWry.dat", null);
			tmp.toFile().deleteOnExit();

			try (OutputStream out = Files.newOutputStream(tmp, StandardOpenOption.WRITE)) {
				byte[] buf = new byte[8192];
				int n;
				while ((n = is.read(buf)) > 0) {
					out.write(buf, 0, n);
				}
			}

			dataFile = tmp.toFile();
		} catch (IOException e1) {
			log.warn("读取IP地址信息文件失败，IP显示功能将无法使用");
			throw new RuntimeException(e1);
		}

		try (RandomAccessFile ipFile = new RandomAccessFile(dataFile, "r")) {
			this.ipBegin = byteArrayToLong(readBytes(ipFile, 0L, 4));
			this.ipEnd = byteArrayToLong(readBytes(ipFile, 4L, 4));
			if (this.ipBegin != -1L && this.ipEnd != -1L) {
				cachedIpFile = new RandomAccessFile(dataFile, "r");
			}

		} catch (FileNotFoundException e) {
			log.warn("IP地址信息文件没有找到，IP显示功能将无法使用", e);
		} catch (IOException e) {
			log.warn("IP地址信息文件格式有错误，IP显示功能将无法使用", e);
		}

		this.ipSum = (this.ipEnd - this.ipBegin) / 7L + 1L;
	}

	private byte[] readBytes(RandomAccessFile ipFile, long offset, int num) throws IOException {
		byte[] ret = new byte[num];
		try {
			ipFile.seek(offset);
			for (int i = 0; i < num; i++) {
				ret[i] = ipFile.readByte();
			}
			return ret;
		} catch (IOException e) {
			throw e;
		}
	}

	private byte[] readBytes(RandomAccessFile ipFile, int num) throws IOException {
		byte[] ret = new byte[num];
		try {
			for (int i = 0; i < num; i++) {
				ret[i] = ipFile.readByte();
			}
			return ret;
		} catch (IOException e) {
			throw e;
		}
	}

	private long byteArrayToLong(byte[] b) {
		long ret = 0L;
		for (int i = 0; i < b.length; i++) {
			ret |= ((long)b[i] & 0xffL) << 8 * i;
		}
		return ret;
	}

	/*
	private String byteArrayToStringIp(byte[] ip) {
		StringBuffer sb = new StringBuffer();
		for (int i = ip.length - 1; i >= 0; --i) {
			sb.append(ip[i] & 0xFF);
			sb.append(".");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	*/

	private long stingIpToLong(String ip) {
		String[] arr = ip.split("\\.");
		try {
			return (Long.parseLong(arr[0]) & 0xffL) << 24
					| (Long.parseLong(arr[1]) & 0xffL) << 16
					| (Long.parseLong(arr[2]) & 0xffL) << 8
					| (Long.parseLong(arr[3]) & 0xffL);
		} catch (Exception ignore) {
		}
		return -1L;
	}

	private long seekIp(RandomAccessFile ipFile, String ip) {
		long tmp = stingIpToLong(ip);
		long i = 0L;
		long j = this.ipSum;
		long m = 0L;
		long lm = 0L;
		try {
			while (i < j) {
				m = (i + j) / 2L;
				lm = m * 7L + this.ipBegin;
				if (tmp == byteArrayToLong(readBytes(ipFile, lm, 4)))
					return byteArrayToLong(readBytes(ipFile, 3));
				if (j == i + 1L)
					return byteArrayToLong(readBytes(ipFile, 3));
				if (tmp > byteArrayToLong(readBytes(ipFile, lm, 4)))
					i = m;
				else {
					j = m;
				}
			}
		} catch (IOException e) {
			log.error("error seekIp", e);
		}
		log.debug("没有找到ip {}", ip);
		return -1L;
	}

	/*
	private String readArea(long offset) throws IOException {
		ipFile.seek(offset);
		byte b = ipFile.readByte();
		if ((b == 1) || (b == 2)) {
			long areaOffset = byteArrayToLong(readBytes(offset + 1L, 3));

			return readString(areaOffset);
		}
		return readString(offset);
	}
	*/

	private String seekCountryArea(RandomAccessFile ipFile, long offset) {
		try {
			ipFile.seek(offset + 4L);
			byte b = ipFile.readByte();
			if (b == 1) {
				long countryOffset = byteArrayToLong(readBytes(ipFile, 3));
				ipFile.seek(countryOffset);
				b = ipFile.readByte();
				if (b == 2) {
					this.country = readString(ipFile, byteArrayToLong(readBytes(ipFile, 3)));
					ipFile.seek(countryOffset + 4L);
				} else {
					this.country = readString(ipFile, countryOffset);
				}
			} else if (b == 2) {
				this.country = readString(ipFile, byteArrayToLong(readBytes(ipFile, 3)));
			} else {
				this.country = readString(ipFile, ipFile.getFilePointer() - 1L);
			}

			if ((this.country.indexOf("省") > 0)
					&& (this.country.indexOf("市") > 0)) {
				return readText(this.country, "省(.+?)市");
			}
			if ((this.country.indexOf("省") < 0)
					&& (this.country.indexOf("市") > 0)) {
				return readText(this.country, "(.+?)市");
			}
			if ((this.country.indexOf("省") > 0)
					&& (this.country.indexOf("市") < 0)) {
				return readText(this.country, "(.+?)省");
			}
			return this.country;
		} catch (IOException e) {
		}
		return null;
	}

	private static String readText(String result, String identifier) {
		Pattern shopNumberPattern = Pattern.compile(identifier);
		Matcher shopNamMatcher = shopNumberPattern.matcher(result);
		if (shopNamMatcher.find())
			return shopNamMatcher.group(1);
		return "";
	}

	private String readString(RandomAccessFile ipFile, long offset) {
		try {
			ipFile.seek(offset);
			byte[] b = new byte[128];

			int i = 0;
			while ((b.length != i++) && ((b[i] = ipFile.readByte()) != 0))
				;

			String ret = new String(b, 0, i, charset);
			ret = ret.trim();
			return ((ret.equals("")) || (ret.indexOf("CZ88.NET") != -1)) ? "未知"
					: ret;
		} catch (IOException e) {
			log.warn("readString", e);
		}
		return "";
	}

	/*
	public ArrayList<IpRecord> stringToIp(String addr) {
		ArrayList<IpRecord> ret = new ArrayList<>();
		try {
			FileChannel fc = ipFile.getChannel();
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0L,
					ipFile.length());
			mbb.order(ByteOrder.LITTLE_ENDIAN);

			for (long i = this.ipBegin + 4L; i != this.ipEnd + 4L; i += 7L) {
				String sca = seekCountryArea(byteArrayToLong(readBytes(i, 3)));
				if (sca.indexOf(addr) != -1) {
					IpRecord rec = new IpRecord();
					rec.address = sca;
					rec.beginIp = byteArrayToStringIp(readBytes(i - 4L, 4));
					rec.endIp = byteArrayToStringIp(readBytes(i + 3L, 4));
					ret.add(rec);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}
	*/

	public static IpAddress getInstance() {
		return instance;
	}

	public String ipStringToAddress(String ip) {
		String ret = "";
		if (cachedIpFile != null) {
			long ipOffset = seekIp(cachedIpFile, ip);
			ret = seekCountryArea(cachedIpFile, ipOffset);
		}
		return ret;
	}

//	public long getIpSum() {
//		return this.ipSum;
//	}

	public static void main(String[] args) throws UnknownHostException {
		IpAddress ipAddr = getInstance();

		long l = ipAddr.ipSum;
		System.out.println("ip 数量: " + l);

		String str = ipAddr.ipStringToAddress("255.255.255.0");
		System.out.println(str);

		str = ipAddr.ipStringToAddress("222.88.59.214");
		System.out.println(str);
		str = ipAddr.ipStringToAddress("222.248.70.78");
		System.out.println(str);
		str = ipAddr.ipStringToAddress("188.1.255.255");
		System.out.println(str);
		str = ipAddr.ipStringToAddress("220.168.59.166");
		System.out.println(str);
		str = ipAddr.ipStringToAddress("221.10.61.90");
		System.out.println(str);
		str = ipAddr.ipStringToAddress("119.119.91.147");
		System.out.println(str);
		InetAddress inet = InetAddress.getLocalHost();
		System.out.println("本机的ip=" + inet.getHostAddress());

		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip = addr.getHostAddress().toString();
		System.out.print(ip);
		String address = addr.getHostName().toString();
		System.out.print(address);
		str = ipAddr.ipStringToAddress(ip);
		System.out.println(str);
		/*
		ArrayList<IpRecord> al = ipAddr.stringToIp("网吧");
		Iterator<IpRecord> it = al.iterator();

		File f = new File("ipdata.txt");
		try {
			if (!(f.exists())) {
				f.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, true)));
			int i = 0;
			while (it.hasNext()) {
				out.write(it.next().toString());
				out.newLine();
				++i;
			}
			out.write(new Date().toString());
			out.write("总共搜索到 " + i);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	/*
	private class IpRecord {
		public String beginIp;
		public String endIp;
		public String address;

		public IpRecord() {
			this.beginIp = (this.endIp = this.address = "");
		}

		public String toString() {
			return this.beginIp + " - " + this.endIp + " " + this.address;
		}
	}
	*/
}
