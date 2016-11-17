package com.pochi.gui.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class FileUpload implements Runnable {
	private JTextArea txtrfmp;
	private String file_path;
	private String ip;

	public void setIp(String ip) {
		this.ip = ip;
	}

	public FileUpload(String file_path,JTextArea txtrfmp) {
		super();
		this.txtrfmp=txtrfmp;
		this.file_path = file_path;
	}

	@Override
	public void run() {
//		System.out.println(file_path);
		File uploadingFile = new File(file_path);
		// 1.创socket
		Socket client = null;
		try {
			client = new Socket(ip, 35789);
		} catch (IOException e) {

			e.printStackTrace();
		}
		// 2.创流
		OutputStream os = null;
		try {
			os = client.getOutputStream();
		} catch (IOException e) {

			e.printStackTrace();
		}
		// 3.要读进来的本地流
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(uploadingFile);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// 4.为传输做准备
		byte[] buf = new byte[1024 * 1024];
		int len = 0;
		// 5.传输
		try {
			while ((len = fis.read(buf)) != -1) {
				os.write(buf, 0, len);
				os.flush();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		// 传输一个结尾信息
		// 关流
		try {
			client.shutdownOutput();
			BufferedReader bufr=new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str=null;
			while ((str=bufr.readLine())!=null) {
				txtrfmp.setText(str);
			}
//			System.out.println("up.........");
			fis.close();
			os.close();
			client.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
