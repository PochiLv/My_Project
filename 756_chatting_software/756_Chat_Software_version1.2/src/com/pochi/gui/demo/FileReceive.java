package com.pochi.gui.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class FileReceive implements Runnable {
	private String filename; 
	private ServerSocket server;
	private JTextArea txtrfmp;
//	public FileReceive(String filename, ServerSocket server) {
//		super();
//		this.filename = filename;
//		this.server = server;
//	}
	
	public void setTxtrfmp(JTextArea txtrfmp) {
		this.txtrfmp = txtrfmp;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ServerSocket getServer() {
		return server;
	}

	public void setServer(ServerSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			while (true) {
				Socket s = server.accept();
				txtrfmp.setText(s.getInetAddress().getHostAddress()+"正在给你传送文件...\r\n文件名："+filename+
						        "\r\n文件保存路径：D:\\ReceivedFile");
				new Thread(new FileTrans(s,filename)).start();
				
			}
//			server.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
