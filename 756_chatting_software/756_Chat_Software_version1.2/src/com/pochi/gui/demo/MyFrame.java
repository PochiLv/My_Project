package com.pochi.gui.demo;

/**
 * @author admin
 * @version v1.2
 * 这个版本遇到的问题:上传文件，只能上传一次，上传完一次之后，就没法再上传了
 * 前版本问题：
 * 1.一个文档没有滚动条，可以用WB中的右键--surround with解决
 * 2.文档没办法大小随意变换，通过取消最大化，WB--Jfram--rezi***--false来解决
 * 解决的问题：
 * 今天在FileReceive的这个类中，当s.accept()执行之后，我启用了一个多线程，解决了多用户传输，和只传输一次的问题
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FileChooserUI;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
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
		/*
		 * 这个大概就是图形界面的核心 具体我也不清楚，就是用
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 它的做法是创建一个自己的构造函数，然后实际上在构造函数里面写很多东西，一new就自动运行
					MyFrame frame = new MyFrame();
					// 显示这个frame结构
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public MyFrame() throws IOException {
		setTitle("756\u804A\u5929\u5DE5\u5177");
		// 窗口不能放大缩小
		setResizable(false);
		// 把对话框上面的那个图片换成自己想要的图片，我这里是756的一张图片
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Desktop\\\u65E0\u6807\u9898.png"));
		// 这个应该是对话框上的那个关闭按钮
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置整个对话框的大小
		setBounds(100, 100, 685, 463);
		// 在Frame结构下弄一张画布，先铺在上面
		contentPane = new JPanel();
		// 这个画布也先设计一个边界
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// 画布的结构是absolute结构，可以任意拖拽的
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 657, 382);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 318, 175);
		panel.add(scrollPane);

		// 创建第一个文本框，并且加入滚动条
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(0, 0, 318, 152);
		panel.add(scrollPane_1);

		// 创建一下聊天用的接收端（这里我不把它成为服务器）
		DatagramSocket ds_rece = null;
		try {
			ds_rece = new DatagramSocket(15987);
		} catch (SocketException e1) {

			e1.printStackTrace();
		}
		// 创建一下第二个文本框，并加滚动条
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		scrollPane_1.setViewportView(textArea_1);
		Rece r = new Rece(textArea_1, ds_rece);
		Thread t1 = new Thread(r);
		t1.start();

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(334, 162, 323, 175);
		panel.add(scrollPane_2);

		// 创建第三个文本框，这个文本框是用来输入传输文件的路径用的
		JTextArea txtrfmp = new JTextArea();
		txtrfmp.setText(
				"\u82E5\u60F3\u53D1\u9001\u6587\u4EF6\uFF0C\r\n1.\u8F93\u5165\u6587\u4EF6\u8DEF\u5F84\uFF0C\u5982\uFF1A\u201Cf:\\\\1.mp4\u201D\r\n2.\u70B9\u51FB\u4E0B\u65B9\u201C\u53D1\u9001\u6587\u4EF6\u201D\u6309\u94AE");
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

		// 创建发送消息按钮
		JButton btnNewButton = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton.setBounds(204, 347, 114, 35);
		panel.add(btnNewButton);

		/*
		 * ********************** 发送消息按钮被按下之后 * **********************
		 */
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 创建聊天发送方的Socket
				DatagramSocket ds_send = null;
				try {
					// 注意，这里要带接口
					ds_send = new DatagramSocket(15986);
				} catch (SocketException e1) {

					e1.printStackTrace();
				}
				// 创建一下聊天发送方这个类，在这个类中，多线程执行一些东西
				Send s = new Send(textArea, ds_send);
				Thread t2 = new Thread(s);
				t2.start();
			}
		});
		// 创建服务器端口
		ServerSocket server = new ServerSocket(35789);
		// 以下三句说的就是多线程开启文件传输服务器，并初始化一些东西
		FileReceive fr = new FileReceive();
		fr.setServer(server);
		fr.setTxtrfmp(txtrfmp);
		new Thread(fr).start();

		// 创建发送文件按钮
		JButton button = new JButton("\u53D1\u9001\u6587\u4EF6");
		button.setBounds(543, 347, 114, 35);
		panel.add(button);

		// 创建发送Ip的文本框
		JTextArea txtrip = new JTextArea();
		txtrip.setBounds(334, 347, 198, 35);
		txtrip.setText("\u8BF7\u5728\u6B64\u5904\u8F93\u5165IP\u5730\u5740");
		panel.add(txtrip);

		JButton button_1 = new JButton("\u9009\u62E9\u6587\u4EF6");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.CANCEL_OPTION) {
//					System.out.println("取消文件");
				} else {
					File selected_file = chooser.getSelectedFile();
					txtrfmp.setText(selected_file.getAbsolutePath());
				}
			}
		});
		button_1.setBounds(543, 121, 114, 35);
		panel.add(button_1);
		// 针对Ip文本框创建监听，监听效果是鼠标点上去之后，里面原先的字消失
		txtrip.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				txtrip.setText("");

			}

		});

		// 创建发送文件按钮的监听
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取一下文件的地址
				String path = txtrfmp.getText();
				// 获取到IP
				String ip = txtrip.getText();
				// 发送完一次文件之后，把文件地址清空了
				txtrfmp.setText("");
				// 下面几句是获取传输文件的名字，并传给文件服务器
				int pos = path.lastIndexOf("\\");
				String name = path.substring(pos + 1, path.length());
				fr.setFilename(name);
				// System.out.println(path);
				// 创建文件传输客户端
				FileUpload fu = new FileUpload(path, txtrfmp);
				// 把服务器IP传给客户端
				fu.setIp(ip);
				// 线程开启文件传输客户端
				new Thread(fu).start();
			}
		});
	}
}
