package junit.test;

import org.junit.Test;

import com.lml.util.HttpRequest;

public class TestColor {

	
	public void testColor(int num) throws Exception {
		String url="http://10.0.1.2/api/8i3OP-ZD9qTXwE4fiMSJdeCTb6IyfHB98KAxlKWv/lights/1/state";
		String bodyMsg="{\"bri\":"+num+"}";
		HttpRequest.sendPut(url, bodyMsg);
	}
	
	@Test
	public void jianbian() throws Exception{
		testClose(true);
		/*testColor(0);
		Thread.sleep(2000);
		for(int i=0;i<101;i++){
			Thread.sleep(20);
			testColor(i);
		}
		Thread.sleep(5000);
		for(int i=100;i>-1;i--){
			Thread.sleep(20);
			testColor(i);
		}
		Thread.sleep(5000);
		testClose(false);*/
	}
	
	public void testClose(boolean on) throws Exception{
		String url="http://10.0.1.2/api/8i3OP-ZD9qTXwE4fiMSJdeCTb6IyfHB98KAxlKWv/lights/1/state";
		String bodyMsg="{\"on\":"+on+"}";
		HttpRequest.sendPut(url, bodyMsg);
	}

}
