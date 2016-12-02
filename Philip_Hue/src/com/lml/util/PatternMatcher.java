/**
 * 获取符合正则的List<String>
 */
package com.lml.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
	public static List<String> filter_Str(String regex,String str){
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		List<String> list=new ArrayList<String>();
		//int count=0;
		while(matcher.find()){
			//count++;
			list.add(matcher.group());
		}
		return list;
	}
}
