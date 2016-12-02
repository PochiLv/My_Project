package com.lml.baseOp;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.lml.exception.GetTokenException;
import com.lml.exception.NullIPException;
import com.lml.exception.SaveTokenException;
import com.lml.model.Light;
import com.lml.model.Linght_State;
import com.lml.util.HttpRequest;
import com.lml.util.PatternMatcher;
import com.lml.util.PropertiesUtil;

public class BaseOperation {
	public String get_token(String ip){
		String result=null;
		String url=null;
		//健壮性判断
		if(ip==null||ip.equals("")){
			throw new NullIPException();
		}
		//判断是不是以http开头
		if(ip.startsWith("http://")){
			url=ip+"/api";
		}else{
			url="http://"+ip+"/api";
		}
		String bodyMsg="{\"devicetype\":\"my_hue_app#pc_lml\"}";
		try {
			result=HttpRequest.sendPost(url, bodyMsg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//提取出其中的token值，保存起来
		return saveToken(result);
	}

	/**
	 * 保存令牌，即把获得的返回数据做处理之后，切割出需要的id那部分，保存到properties中
	 * @param result
	 * @return
	 */
	private String saveToken(String result) {
		String token=null;
		// 健壮性判断，返回的是不是error信息
		if(result.contains("error")){
			throw new GetTokenException();
		}
		String regex=":\"\\S+\"";
		List<String> filtered_list=PatternMatcher.filter_Str(regex, result);
		if(filtered_list==null||(filtered_list.size()==0)){
			throw new SaveTokenException();
		}
		token=filtered_list.get(filtered_list.size()-1);
		token=token.substring(2,token.length()-1);
		PropertiesUtil.writeProperties("token", token);
		return token;
	}
	
	public void change_state(Light light) throws Exception{
		String str_token =PropertiesUtil.getValue("token");
		String str_ip=PropertiesUtil.getValue("ip");
		String light_id =light.getId();
		String url="http://"+str_ip+"/api/"+str_token+"/lights/"+light_id+"/state";
		Linght_State state = light.getState();
		Class<? extends Linght_State> class_light = state.getClass();
		
		Field[] fields = class_light.getDeclaredFields();
		StringBuilder sBuilder=new StringBuilder();
		sBuilder.append('{');
		for(Field f:fields){
			f.setAccessible(true);
			Object value = f.get(state);
			if(value==null)
				continue;
			if(f.getType() == Double[].class){
				sBuilder.append("\"");
				sBuilder.append(f.getName());
				sBuilder.append("\":[");
				Double[] real_value=(Double[]) value;
				sBuilder.append(real_value[0]);
				sBuilder.append(',');
				sBuilder.append(real_value[1]);
				sBuilder.append("],");
				continue;
			}
			sBuilder.append("\"");
			sBuilder.append(f.getName());
			sBuilder.append("\":");
			sBuilder.append(value);
			sBuilder.append(',');
		}
		sBuilder.replace(sBuilder.length()-1, sBuilder.length(), "}");
		String bodyMsg=sBuilder.toString();
		System.out.println(bodyMsg);
		HttpRequest.sendPut(url, bodyMsg);
	}
}
