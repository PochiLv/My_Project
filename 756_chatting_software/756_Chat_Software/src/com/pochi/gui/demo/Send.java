package com.pochi.gui.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Send implements Runnable {
	private JTextArea textArea;
	private DatagramSocket ds;

	public Send(JTextArea textArea, DatagramSocket ds) {
		super();
		this.textArea = textArea;
		this.ds = ds;
	}

	@Override
	public void run() {
		String str = textArea.getText();
		byte[] buf = str.getBytes();
		int length = buf.length;
		InetAddress ip = null;

		try {
			ip = InetAddress.getByName("192.168.1.255");
		} catch (UnknownHostException e2) {

			e2.printStackTrace();
		}
		textArea.setText(null);

		// 1.
		// DatagramSocket ds = null;
		// try {
		// ds = new DatagramSocket();
		// } catch (SocketException e1) {
		//
		// e1.printStackTrace();
		// }
		// 2.
		DatagramPacket dp = new DatagramPacket(buf, length, ip, 15987);
		// 3.

		try {
			ds.send(dp);
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		ds.close();

		// System.out.println(dp.getData().toString());
		// 4.

	}

}
