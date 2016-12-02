package com.lml.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.lml.util.CreatedIcon;
import com.lml.util.PropertiesUtil;

public class IPIFrame extends JFrame {

	private JTextField text_ip;
	private JButton login;
	private JPanel imagePanel, panel;

	class IDLoginAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			String str_ip=text_ip.getText();
			if (str_ip.equals("������ƾߵ�IP��ַ") || str_ip.equals("")) {
				JOptionPane.showMessageDialog(null, "����������IP��ַ");
			} else {
				try {
					PropertiesUtil.writeProperties("ip", str_ip);
					ButtonsIFrame frame = new ButtonsIFrame();
					frame.setVisible(true);
					IPIFrame.this.setVisible(false);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public IPIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("��¼");
		setBounds(670, 200, 900, 500);
		// setVisible(false);
		ImageIcon background = CreatedIcon.add("IP2.jpg");
		JLabel backgroundp = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		backgroundp.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// ���ݴ���Ĭ�ϵĲ��ֹ�����ΪBorderLayout

		// final JPanel panel = new JPanel();

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		// getContentPane().add(panel);
		panel.setOpaque(false);
		getContentPane().add(panel, BorderLayout.CENTER);
		// panel.setPreferredSize(new Dimension(30, 50));
		final JLabel labelS = new JLabel();
		labelS.setHorizontalAlignment(SwingConstants.CENTER);
		// labelS.setPreferredSize(new Dimension(3,1));
		panel.add(labelS);

		text_ip = new JTextField();
		text_ip.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				text_ip.setText("");
				// text_ip.setHorizontalAlignment(JTextField.CENTER);
			}
		});
		text_ip.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		text_ip.setText("PLEASE INPUT THE IP");
		text_ip.setHorizontalAlignment(JTextField.CENTER);
		text_ip.setForeground(new Color(206, 206, 206));
		text_ip.setColumns(200);
		text_ip.setBounds(160, 490, 212, 40);
		panel.add(text_ip);
		text_ip.setOpaque(false);
		text_ip.setBorder(new MatteBorder(0, 0, 2, 0, new Color(0, 0, 0, 0)));//

		login = new JButton();
		login.addActionListener(new IDLoginAction());

		// login.setText("��¼");
		login.setBounds(195, 547, 97, 35);
		panel.add(login);
		// login.setVisible(false);
		login.setOpaque(false);//
		login.setContentAreaFilled(false);

		// �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		this.getLayeredPane().add(backgroundp, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(background.getIconWidth() + 16, background.getIconHeight() + 38);
		this.setVisible(true);
	}

}
