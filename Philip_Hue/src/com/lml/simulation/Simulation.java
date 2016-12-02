package com.lml.simulation;

import com.lml.service.BusinessService;
import com.lml.view.DemoIFrame;

public class Simulation implements Runnable  {
	private BusinessService service=new BusinessService();
	private DemoIFrame frame=DemoIFrame.getInstance();
	public Simulation() {
	}

	@Override
	public void run() {
		try {
			
			Thread.sleep(5000);
			service.winSleepLight("1");
			Thread.sleep(5000);
			frame.getStep1().setText("检测体验者睡着");
			service.switchoff("1");
			Thread.sleep(5000);
			frame.getStep1().setEnabled(false);
			frame.getStep2().setText("夜间检测体验者醒来");
			service.winter_dawn("1");
			Thread.sleep(5000);
			frame.getStep2().setEnabled(false);
			frame.getStep3().setText("检测体验者离开房间");
			service.winter_dawn("2");
			service.winter_dawn("3");
			service.winter_fade("1");
			service.winter_fade("2");
			Thread.sleep(5000);
			frame.getStep3().setEnabled(false);
			frame.getStep4().setText("检测体验者回到房间");
			service.winter_dawn("2");
			service.winter_dawn("1");
			service.winter_fade("3");
			service.winter_fade("2");
			service.winter_fade("1");
			service.switchoff("3");
			service.switchoff("2");
			Thread.sleep(5000);
			frame.getStep4().setEnabled(false);
			frame.getStep5().setText("检测体验者再次睡着");
			service.switchoff("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
