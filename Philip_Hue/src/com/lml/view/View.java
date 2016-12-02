package com.lml.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField text_url;
	private JTextField text_ip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_get_token = new JButton("\u83B7\u53D6\u4EE4\u724C");
		btn_get_token.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_get_token.setBounds(10, 10, 111, 39);
		contentPane.add(btn_get_token);
		
		JButton btn_light_state = new JButton("\u83B7\u53D6\u706F\u72B6\u6001");
		btn_light_state.setBounds(10, 68, 111, 39);
		contentPane.add(btn_light_state);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.menu);
		editorPane.setText("\u8FD4\u56DE\u6570\u636E");
		editorPane.setBounds(159, 197, 106, 21);
		contentPane.add(editorPane);
		
		JButton btn_Get = new JButton("Get");
		btn_Get.setBounds(159, 164, 71, 23);
		contentPane.add(btn_Get);
		
		JButton btn_Post = new JButton("Post");
		btn_Post.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Post.setBounds(240, 164, 73, 23);
		contentPane.add(btn_Post);
		
		JButton btn_Put = new JButton("Put");
		btn_Put.setBounds(323, 164, 73, 23);
		contentPane.add(btn_Put);
		
		JButton btn_Delete = new JButton("Del");
		btn_Delete.setBounds(406, 164, 69, 23);
		contentPane.add(btn_Delete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(159, 55, 316, 96);
		contentPane.add(scrollPane);
		
		JTextArea txt_MsgBody = new JTextArea();
		txt_MsgBody.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_MsgBody.setText("");
			}
		});
		scrollPane.setViewportView(txt_MsgBody);
		txt_MsgBody.setText("\u8BF7\u8F93\u5165MsgBody");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(159, 226, 316, 78);
		contentPane.add(scrollPane_1);
		
		JTextArea txt_return = new JTextArea();
		scrollPane_1.setViewportView(txt_return);
		
		text_url = new JTextField();
		text_url.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_url.setText("");
			}
		});
		text_url.setText("\u8BF7\u8F93\u5165url");
		text_url.setColumns(10);
		text_url.setBounds(161, 10, 169, 33);
		contentPane.add(text_url);
		
		text_ip = new JTextField();
		text_ip.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				text_ip.setText("");
			}
		});
		text_ip.setText("\u8BF7\u8F93\u5165ip");
		text_ip.setColumns(10);
		text_ip.setBounds(340, 10, 133, 31);
		contentPane.add(text_ip);
	}
}
