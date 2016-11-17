package com.pochi.utils;

public class ELUtils {
	public static String subStr(String source,Integer length){
		if(source.length()>length){
			return source.substring(0, length)+"...";
		}
		return source;
	}
}

