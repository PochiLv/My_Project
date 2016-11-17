package com.pochi.gui.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FileTrans implements Runnable {
	private Socket s;
	private String filename;

	public FileTrans(Socket s, String filename) {
		super();
		this.s = s;
		this.filename = filename;
	}

	@Override
	public void run() {
		try {
			// 3.�����
			InputStream server_in = s.getInputStream();
			// 4.����ļ�����һ��
			File dir = new File("D:\\ReceivedFile");
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File file_temp = new File(dir.getAbsolutePath() + "\\" + filename);
			FileOutputStream fos = new FileOutputStream(file_temp);
			// 5.����ǰ��׼������
			byte[] buf = new byte[1024 * 1024];
			int len = 0;
			long time = System.currentTimeMillis();
			while ((len = server_in.read(buf)) != -1) {
				// System.out.println(buf.length);
				fos.write(buf, 0, len);
				fos.flush();
			}
			// System.out.println("receiving.............");
			time = System.currentTimeMillis() - time;
			int hour = (int) time / 1000 / 60 / 60;
			int min = (int) time / 1000 / 60 % 60;
			int sec = (int) time / 1000 % 60;
			int msec = (int) time % 1000;
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bw.write("���յ��ļ���" + "\r\n�����ļ����䣬����ʱ" + hour + "Сʱ" + min + "����" + sec + "��" + msec + "����");
			bw.flush();
			// ����
			bw.close();
			fos.close();
			server_in.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
