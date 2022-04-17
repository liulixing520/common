package com.jt.lux.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	public static final String UTF8 = "UTF-8";

	public static String bytes2Str(byte[] bytes, String charset) {
		if (bytes == null || bytes.length == 0) {
			return "";
		}
		try {
			return new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String bytes2Str(byte[] bytes) {
		return bytes2Str(bytes, UTF8);
	}

	public static byte[] str2Bytes(String str, String charset) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			return str.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] str2Bytes(String str) {
		return str2Bytes(str, UTF8);
	}

	public static String urlEncode(String str) {
		if (str == null) {
			str = "";
		}
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String urlDecode(String str) {
		if (str == null) {
			str = "";
		}
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String urlEncode(String str, String encoding) {
		if (str == null) {
			str = "";
		}
		try {
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String urlDecode(String str, String encoding) {
		if (str == null) {
			str = "";
		}
		try {
			return URLDecoder.decode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void appendSignPara(StringBuffer buf, String string, String productName) {
		if (productName != null && productName.trim().length() > 0) {
			buf.append(string).append("=").append(productName + "&");
		}
	}

	public static void appendLastSignPara(StringBuffer buf, String string, String md5key) {
		buf.append(string).append("=").append(md5key);
	}

	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1) return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 按照固定格式返回系统当前时间
	 * @param pattern
	 * @return
	 */
	public static String date2Datetime(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}


	/*
	* 时间格式转换工具
	* */
	public static String  formatDate(Object value, String fmt) {  // yyyy-MM-dd'T'hh:mm:ss
		try {
			String strDate = null;
			if (value instanceof org.apache.xml.dtm.ref.DTMNodeIterator) {
				strDate = ((org.apache.xml.dtm.ref.DTMNodeIterator)value).getDTMIterator() + "";
			} else {
				strDate = value + "";
			}
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			String now = new SimpleDateFormat(fmt).format(date);
			return now;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


	public static String getEncoding(String str) {
		String encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
				String s = encode;
				return s; //是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}

		return ""; //如果都不是，说明输入的内容不属于常见的编码格式。
	}
}
