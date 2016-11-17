package com.pochi.gui.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.swing.JTextArea;

public class Rece implements Runnable {
	private JTextArea textArea_1;
	DatagramSocket ds;
	
	
	public Rece(JTextArea textArea_1, DatagramSocket ds) {
		super();
		this.textArea_1 = textArea_1;
		this.ds = ds;
	}


	@Override
	public void run() {
		/*
		 * 自己写的
		 */
		//1.
//		DatagramSocket ds = null;
//		try {
//			ds = new DatagramSocket(15987);
//		} catch (SocketException e1) {
//			
//			e1.printStackTrace();
//		}
		//2.
		byte[] buf=new byte[1024];
		int length =buf.length;
		DatagramPacket dp=new DatagramPacket(buf, length);
		//因为想尝试着用insert的方法，所以初始化一个pos
//		int pos=0;
		StringBuilder sb=new StringBuilder();
		//3.
		while (true) {
			try {
				ds.receive(dp);
//				System.out.println(dp.toString());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String ip_str=dp.getAddress().getHostAddress();
			String cotent=new String(dp.getData(),0,dp.getLength());
			
//			textArea_1.setText("");
			String str_temp=ip_str+":"+cotent;
			sb.append(str_temp+"\r\n");
			textArea_1.setText(sb.toString());
//			pos=str_temp.length();
			
//			System.out.println(str_temp);
			
			if (cotent.equals("886")) {
				textArea_1.append(ip_str+"已退出聊天室");
				ds.close();
				break;
			}
		}
	}

}
