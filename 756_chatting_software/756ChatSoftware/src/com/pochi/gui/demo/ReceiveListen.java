//package com.pochi.gui.demo;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//public class ReceiveListen implements Runnable {
//	ServerSocket serverlisten;
//	
//	public ReceiveListen(ServerSocket serverlisten) {
//		super();
//		this.serverlisten = serverlisten;
//	}
//
//	@Override
//	public void run() {
//		try {
//			Socket s=serverlisten.accept();
//			BufferedReader bufr=new BufferedReader(new  InputStreamReader(s.getInputStream()));
//			String path=bufr.readLine();
////			String [] str_suipian=path.split(".");
//			FileReceive fr=new FileReceive(path, serverlisten);
//			new Thread(fr).start();
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//	}
//
//}
