package com.fh.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * All rights Reserved
 * 
 * @Title: MD5Util.java
 * @Package com.fh.movie.util
 * @Description: TODO(用一句话描述该文件)
 * @author: 郑州飞狐
 * @date: 2018年4月3日 上午11:21:32
 * @version V1.0
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 * @注意: 本内容仅限于郑州飞狐，禁止外泄以及用于其他的商业目。
 */
public class MD5Util {

	public static String EncoderByMd5(String str) {
		MessageDigest md5;
		BASE64Encoder base64en = new BASE64Encoder();
		String newstr = "";
		try {
			md5 = MessageDigest.getInstance("MD5");
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newstr;
	}

	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			String md5 = new BigInteger(1, md.digest()).toString(16);
			// BigInteger会把0省略掉，需补全至32位
			return fillMD5(md5);
		} catch (Exception e) {
			throw new RuntimeException("MD5加密错误:" + e.getMessage(), e);
		}
	}

	public static String fillMD5(String md5) {
		return md5.length() == 32 ? md5 : fillMD5("0" + md5);
	}

	public static void main(String[] args) {
		System.out.println(getMD5("123456"));
	}
}
