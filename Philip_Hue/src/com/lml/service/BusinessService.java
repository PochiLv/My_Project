package com.lml.service;

import com.lml.baseOp.BaseOperation;
import com.lml.model.Light;
import com.lml.model.Linght_State;
import com.lml.util.HttpRequest;
import com.lml.util.PropertiesUtil;

public class BusinessService {

	BaseOperation baseOperation = new BaseOperation();
	/**
	 * 获取令牌
	 */
	public String get_token(String ip) {
		return baseOperation.get_token(ip);
	}

	/**
	 * 冬天渐亮
	 * @throws Exception 
	 */
	public void winter_dawn(String id) throws Exception {
//		BaseOperation baseOperation = new BaseOperation();
		Light winLight = getWinLight();
		winLight.setId(id);
		Linght_State state = winLight.getState();
		state.setOn(new Boolean(true));
		for (int i = 0; i < 101; i++) {
			Thread.sleep(20);
			state.setBri(i);
			baseOperation.change_state(winLight);
		}
	}

	/**
	 * 冬天渐暗
	 * @throws Exception 
	 */
	public void winter_fade(String id) throws Exception {
//		BaseOperation baseOperation = new BaseOperation();
		Light winLight = getWinLight();
		winLight.setId(id);
		Linght_State state = winLight.getState();
		for (int i = 100; i > -1; i--) {
			Thread.sleep(20);
			state.setBri(i);
			baseOperation.change_state(winLight);
		}
	}

	/**
	 * 冬天开灯
	 * @throws Exception 
	 */
	public void winter_switchon(String id) throws Exception {
//		BaseOperation baseOperation = new BaseOperation();
		Light winLight = getWinLight();
		winLight.setId(id);
		winLight.getState().setOn(new Boolean(true));
		winLight.getState().setBri(200);
		baseOperation.change_state(winLight);
	}

	/**
	 * 夏天渐亮
	 * @throws Exception 
	 */
	public void summer_dawn(String id) throws Exception {
//		BaseOperation baseOperation = new BaseOperation();
		Light sumLight = getSumLight();
		sumLight.setId(id);
		Linght_State state = sumLight.getState();
		state.setOn(new Boolean(true));
		for (int i = 0; i < 101; i++) {
			Thread.sleep(20);
			state.setBri(i);
			baseOperation.change_state(sumLight);
		}
	}

	/**
	 * 夏天渐暗
	 * @throws Exception 
	 */
	public void summer_fade(String id) throws Exception {
//		BaseOperation baseOperation = new BaseOperation();
		Light sumLight = getSumLight();
		sumLight.setId(id);
		Linght_State state = sumLight.getState();
		for (int i = 100; i > -1; i--) {
			Thread.sleep(20);
			state.setBri(i);
			baseOperation.change_state(sumLight);
		}
	}

	/**
	 * 夏天开灯
	 * @throws Exception 
	 */
	public void summer_switchon(String id) throws Exception {
		Light sumLight = getSumLight();
		sumLight.setId(id);
		sumLight.getState().setOn(new Boolean(true));
		sumLight.getState().setBri(200);
		baseOperation.change_state(sumLight);
	}

	/**
	 * 关灯
	 * @throws Exception 
	 */
	public void switchoff(String id) throws Exception {
		Light light = new Light();
		light.setId(id);
		light.getState().setOn(new Boolean(false));
		baseOperation.change_state(light);
	}

	/**
	 * 获得冬天light
	 */
	private Light getWinLight() {
		Light light = new Light();
		Linght_State state = light.getState();
		String filePath = "./src/winter.properties";
		String sat = PropertiesUtil.readValue(filePath, "sat");
		String hue = PropertiesUtil.readValue(filePath, "hue");
		state.setHue(Integer.parseInt(hue));
		state.setSat(Integer.parseInt(sat));
//		String x = PropertiesUtil.readValue(filePath, "x");
//		String y = PropertiesUtil.readValue(filePath, "y");
//		state.setXy(new Double[]{Double.parseDouble(x),Double.parseDouble(y)});
		return light;
	}

	/**
	 * 获得夏天light
	 */
	private Light getSumLight() {
		Light light = new Light();
		Linght_State state = light.getState();
		String filePath = "./src/summer.properties";
		String sat = PropertiesUtil.readValue(filePath, "sat");
		String hue = PropertiesUtil.readValue(filePath, "hue");
		state.setHue(Integer.parseInt(hue));
		state.setSat(Integer.parseInt(sat));
//		String x = PropertiesUtil.readValue(filePath, "x");
//		String y = PropertiesUtil.readValue(filePath, "y");
//		state.setXy(new Double[]{Double.parseDouble(x),Double.parseDouble(y)});
		return light;
	}
	
	public void winSleepLight(String id) throws Exception{
		Light winLight = getWinLight();
		winLight.setId(id);
		winLight.getState().setBri(1);
		winLight.getState().setOn(true);
		baseOperation.change_state(winLight);
	}
	public void sumSleepLight(String id) throws Exception{
		Light sumLight = getSumLight();
		sumLight.setId(id);
		sumLight.getState().setBri(1);
		sumLight.getState().setOn(true);
		baseOperation.change_state(sumLight);
	}

}
