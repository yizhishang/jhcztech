package com.jhcz.trade.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * @fileName MD5Util.java
 * @date 2016-03-30
 * @time 上午10:56:54
 * @author aibo
 *
 */
public final class MD5Util {
	/**
	 * 
	 * @date 2016-03-30
	 * @time 上午10:57:03
	 * @author aibo
	 * @param s
	 * @return String
	 */
	public static final String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		byte[] btInput = s.getBytes();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
