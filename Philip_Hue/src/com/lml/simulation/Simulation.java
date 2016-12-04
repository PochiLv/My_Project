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
			service.switchoff("1");
			service.switchoff("2");
			service.switchoff("3");
			Thread.sleep(5000);
			service.winSleepLight("1");
			Thread.sleep(5000);
			frame.getStep1().setText("Fall into sleep");
			service.switchoff("1");
			Thread.sleep(5000);
			frame.getStep1().setEnabled(false);
			frame.getStep2().setText("Wake up at night");
			service.winter_dawn("1");
			Thread.sleep(5000);
			frame.getStep2().setEnabled(false);
			frame.getStep3().setText("Leave the room");
			/*****tomng****************/
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_fade("1");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_dawn("3");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			/**********tomg***********/
			Thread.sleep(10000);//synchronized delay
			/**delay*/
			
			/*delay*/
			/*tong*/
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_dawn("2");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_fade("3");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			/*tong*/
			/*delay long*/
			Thread.sleep(10000);//synchronized delay
			Thread.sleep(5000);
			/*delay long*/

			frame.getStep3().setEnabled(false);

			frame.getStep4().setText("Back to the room");
			/*tong*/
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_fade("2");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_dawn("3");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			/*tong*/
			Thread.sleep(10000);//synchronized delay
			service.switchoff("2");
			/*tong*/
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_dawn("1");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			new Thread(){

				@Override
				public void run() {
					try {
						service.winter_fade("3");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}.start();
			/*tong*/
			Thread.sleep(10000);//synchronized delay
			service.switchoff("3");
			service.winter_fade("1");
			Thread.sleep(5000);
			frame.getStep4().setEnabled(false);
			frame.getStep5().setText("Fall into sleep again");
			service.switchoff("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
