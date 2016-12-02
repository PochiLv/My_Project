package junit.test;

import org.junit.Test;

import com.lml.util.PropertiesUtil;

public class TestPropertiesUtil {

	@Test
	public void testwrite(){
		PropertiesUtil.writeProperties("avc", "123");
	}
	@Test
	public void testRead(){
		System.out.println(PropertiesUtil.getValue("avc"));
	}
	@Test
	public void testUpdate(){
		PropertiesUtil.updateProperties("avc", "1213364");
	}
}
