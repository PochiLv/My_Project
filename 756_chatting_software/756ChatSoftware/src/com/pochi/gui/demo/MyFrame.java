package com.pochi.gui.demo;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class MyFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrame frame = new MyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public MyFrame() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Desktop\\\u65E0\u6807\u9898.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 657, 382);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 318, 175);
		panel.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 657, 152);
		panel.add(scrollPane_1);

		/*
		 * **************************************
		 * 创建完文本框之后，使得文本框收信息		*
		 * **************************************
		 */
		DatagramSocket ds_rece = null;
		try {
			ds_rece = new DatagramSocket(15987);
		} catch (SocketException e1) {
			
			e1.printStackTrace();
		}
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		Rece r = new Rece(textArea_1, ds_rece);
		Thread t1 = new Thread(r);
		t1.start();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(334, 162, 323, 175);
		panel.add(scrollPane_2);

		JTextArea txtrfmp = new JTextArea();
		txtrfmp.setText("\u82E5\u60F3\u53D1\u9001\u6587\u4EF6\uFF0C\r\n1.\u8F93\u5165\u6587\u4EF6\u8DEF\u5F84\uFF0C\u5982\uFF1A\u201Cf:\\\\1.mp4\u201D\r\n2.\u70B9\u51FB\u4E0B\u65B9\u201C\u53D1\u9001\u6587\u4EF6\u201D\u6309\u94AE");
		scrollPane_2.setViewportView(txtrfmp);
		
		/*
		 ********************************************************************
		 * 这个监听的效果是，鼠标的光标点到文本框里的时候，文本框里的字全部消失
		 ********************************************************************
		 */
		txtrfmp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				txtrfmp.setText("");
			}
			
		});

		JButton btnNewButton = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton.setBounds(204, 347, 114, 35);
		panel.add(btnNewButton);

		/*
		 * **********************
		 * 发送消息按钮被按下之后	*
		 * **********************
		 */
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DatagramSocket ds_send = null;
				try {
					ds_send = new DatagramSocket(15986);
				} catch (SocketException e1) {

					e1.printStackTrace();
				}

				Send s = new Send(textArea, ds_send);
				Thread t2 = new Thread(s);
				t2.start();

			}
		});
		//先把端口开开来
		ServerSocket server =new ServerSocket(35789);
		//开启监听接收
		FileReceive fr=new FileReceive();
		fr.setServer(server);
		new Thread(fr).start();
		
		JButton button = new JButton("\u53D1\u9001\u6587\u4EF6");
		button.setBounds(543, 347, 114, 35);
		panel.add(button);
		
		JTextArea txtrip = new JTextArea();
		txtrip.setText("\u8BF7\u5728\u6B64\u5904\u8F93\u5165IP\u5730\u5740");
		txtrip.setBounds(334, 347, 198, 35);
		panel.add(txtrip);
		txtrip.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				txtrip.setText("");
				
			}
			
		});
		
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path=txtrfmp.getText();
				String ip=txtrip.getText();
				txtrfmp.setText("");
				int pos=path.lastIndexOf("\\");
				String name=path.substring(pos+1, path.length());
				fr.setFilename(name);
//				System.out.println(path);
				FileUpload fu=new FileUpload(path,txtrfmp);
				fu.setIp(ip);
				new Thread(fu).start();
			}
		});
	}
}
