package com.pochi.gui.demo;

/**
 * @author admin
 * @version v1.2
 * ����汾����������:�ϴ��ļ���ֻ���ϴ�һ�Σ��ϴ���һ��֮�󣬾�û�����ϴ���
 * ǰ�汾���⣺
 * 1.һ���ĵ�û�й�������������WB�е��Ҽ�--surround with���
 * 2.�ĵ�û�취��С����任��ͨ��ȡ����󻯣�WB--Jfram--rezi***--false�����
 * ��������⣺
 * ������FileReceive��������У���s.accept()ִ��֮����������һ�����̣߳�����˶��û����䣬��ֻ����һ�ε�����
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
		 * �����ž���ͼ�ν���ĺ��� ������Ҳ�������������
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ���������Ǵ���һ���Լ��Ĺ��캯����Ȼ��ʵ�����ڹ��캯������д�ܶණ����һnew���Զ�����
					MyFrame frame = new MyFrame();
					// ��ʾ���frame�ṹ
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
		// ���ڲ��ܷŴ���С
		setResizable(false);
		// �ѶԻ���������Ǹ�ͼƬ�����Լ���Ҫ��ͼƬ����������756��һ��ͼƬ
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\admin\\Desktop\\\u65E0\u6807\u9898.png"));
		// ���Ӧ���ǶԻ����ϵ��Ǹ��رհ�ť
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���������Ի���Ĵ�С
		setBounds(100, 100, 685, 463);
		// ��Frame�ṹ��Ūһ�Ż���������������
		contentPane = new JPanel();
		// �������Ҳ�����һ���߽�
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		// �����Ľṹ��absolute�ṹ������������ק��
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 657, 382);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 162, 318, 175);
		panel.add(scrollPane);

		// ������һ���ı��򣬲��Ҽ��������
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setEnabled(false);
		scrollPane_1.setBounds(0, 0, 318, 152);
		panel.add(scrollPane_1);

		// ����һ�������õĽ��նˣ������Ҳ�������Ϊ��������
		DatagramSocket ds_rece = null;
		try {
			ds_rece = new DatagramSocket(15987);
		} catch (SocketException e1) {

			e1.printStackTrace();
		}
		// ����һ�µڶ����ı��򣬲��ӹ�����
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEnabled(false);
		scrollPane_1.setViewportView(textArea_1);
		Rece r = new Rece(textArea_1, ds_rece);
		Thread t1 = new Thread(r);
		t1.start();

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(334, 162, 323, 175);
		panel.add(scrollPane_2);

		// �����������ı�������ı������������봫���ļ���·���õ�
		JTextArea txtrfmp = new JTextArea();
		txtrfmp.setText(
				"\u82E5\u60F3\u53D1\u9001\u6587\u4EF6\uFF0C\r\n1.\u8F93\u5165\u6587\u4EF6\u8DEF\u5F84\uFF0C\u5982\uFF1A\u201Cf:\\\\1.mp4\u201D\r\n2.\u70B9\u51FB\u4E0B\u65B9\u201C\u53D1\u9001\u6587\u4EF6\u201D\u6309\u94AE");
		scrollPane_2.setViewportView(txtrfmp);

		/*
		 ********************************************************************
		 * ���������Ч���ǣ����Ĺ��㵽�ı������ʱ���ı��������ȫ����ʧ
		 ********************************************************************
		 */
		txtrfmp.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				txtrfmp.setText("");
			}

		});

		// ����������Ϣ��ť
		JButton btnNewButton = new JButton("\u53D1\u9001\u6D88\u606F");
		btnNewButton.setBounds(204, 347, 114, 35);
		panel.add(btnNewButton);

		/*
		 * ********************** ������Ϣ��ť������֮�� * **********************
		 */
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �������췢�ͷ���Socket
				DatagramSocket ds_send = null;
				try {
					// ע�⣬����Ҫ���ӿ�
					ds_send = new DatagramSocket(15986);
				} catch (SocketException e1) {

					e1.printStackTrace();
				}
				// ����һ�����췢�ͷ�����࣬��������У����߳�ִ��һЩ����
				Send s = new Send(textArea, ds_send);
				Thread t2 = new Thread(s);
				t2.start();
			}
		});
		// �����������˿�
		ServerSocket server = new ServerSocket(35789);
		// ��������˵�ľ��Ƕ��߳̿����ļ����������������ʼ��һЩ����
		FileReceive fr = new FileReceive();
		fr.setServer(server);
		fr.setTxtrfmp(txtrfmp);
		new Thread(fr).start();

		// ���������ļ���ť
		JButton button = new JButton("\u53D1\u9001\u6587\u4EF6");
		button.setBounds(543, 347, 114, 35);
		panel.add(button);

		// ��������Ip���ı���
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
//					System.out.println("ȡ���ļ�");
				} else {
					File selected_file = chooser.getSelectedFile();
					txtrfmp.setText(selected_file.getAbsolutePath());
				}
			}
		});
		button_1.setBounds(543, 121, 114, 35);
		panel.add(button_1);
		// ���Ip�ı��򴴽�����������Ч����������ȥ֮������ԭ�ȵ�����ʧ
		txtrip.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				txtrip.setText("");

			}

		});

		// ���������ļ���ť�ļ���
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��ȡһ���ļ��ĵ�ַ
				String path = txtrfmp.getText();
				// ��ȡ��IP
				String ip = txtrip.getText();
				// ������һ���ļ�֮�󣬰��ļ���ַ�����
				txtrfmp.setText("");
				// ���漸���ǻ�ȡ�����ļ������֣��������ļ�������
				int pos = path.lastIndexOf("\\");
				String name = path.substring(pos + 1, path.length());
				fr.setFilename(name);
				// System.out.println(path);
				// �����ļ�����ͻ���
				FileUpload fu = new FileUpload(path, txtrfmp);
				// �ѷ�����IP�����ͻ���
				fu.setIp(ip);
				// �߳̿����ļ�����ͻ���
				new Thread(fu).start();
			}
		});
	}
}
