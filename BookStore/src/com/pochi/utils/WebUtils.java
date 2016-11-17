package com.pochi.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import sun.misc.BASE64Encoder;

public class WebUtils {
	public static String takeUUID() {
		UUID id = UUID.randomUUID();
		String str = id.toString();
		return str;
	}

	public static String md5(String str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("md5");
		byte[] buff = md.digest(str.getBytes());
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(buff);
	}

}

