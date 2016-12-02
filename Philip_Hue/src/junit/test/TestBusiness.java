package junit.test;

import org.junit.Test;

import com.lml.service.BusinessService;

public class TestBusiness {
	@Test
	public void testGetToken(){
		BusinessService service=new BusinessService();
		String token=service.get_token("10.0.1.2");
		System.out.println(token);
	}
	@Test
	public void testWinDawn() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.winter_dawn("1");
	}
	@Test
	public void testWinFade() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.winter_fade("1");
	}
	@Test
	public void testSumDawn() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.summer_dawn("1");
	}
	@Test
	public void testSumFade() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.summer_fade("1");
	}
	@Test
	public void testSwitchOff() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.switchoff("3");
	}
	@Test
	public void testWinSwitchOn() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.winter_switchon("1");
	}
	@Test
	public void testSumSwitchOn() throws Exception{
		BusinessService businessService = new BusinessService();
		businessService.summer_switchon("3");
	}
}
