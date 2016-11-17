package com.pochi.gui.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FileReceive implements Runnable {
	private String filename; 
	private ServerSocket server;
//	public FileReceive(String filename, ServerSocket server) {
//		super();
//		this.filename = filename;
//		this.server = server;
//	}
	
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
			// 1.
//			server = new ServerSocket(35789);
			// 2.获取一下别人的Socket
			Socket s = server.accept();
			//3.搞个流
			InputStream server_in= s.getInputStream();
			//4.搞个文件关联一下
			File dir=new File("D:\\ReceivedFile");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file_temp=new File(dir.getAbsolutePath()+"\\"+filename);
			FileOutputStream fos=new FileOutputStream(file_temp);
			//5.传送前的准备工作
			byte [] buf=new byte[1024*1024];
			int len=0;
			long time=System.currentTimeMillis();
			while ((len=server_in.read(buf))!=-1) {
//				System.out.println(buf.length);
				fos.write(buf, 0, len);
				fos.flush();
			}
//			System.out.println("receiving.............");
			time=System.currentTimeMillis()-time;
			int hour=(int)time/1000/60/60;
			int min=(int)time/1000/60;
			int sec=(int)time/1000;
			int msec=(int)time%1000;
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bw.write("已收到文件！"+"\r\n本次文件传输，共用时"+hour+"小时"+min+"分钟"+sec+"秒"+msec+"毫秒");
			bw.flush();
			//关流
			bw.close();
			fos.close();
			server_in.close();
			server.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
