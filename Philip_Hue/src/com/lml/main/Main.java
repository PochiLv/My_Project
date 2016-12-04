package com.lml.main;

import java.awt.Button;
import java.awt.EventQueue;

import com.lml.view.ButtonsIFrame;
import com.lml.view.DemoIFrame;
import com.lml.view.IPIFrame;
import com.lml.view.View;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//View frame = new View();
					//frame.setVisible(true);
					new IPIFrame();
					//new DemoIFrame();
					//new ButtonsIFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
