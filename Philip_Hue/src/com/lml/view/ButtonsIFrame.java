package com.lml.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.lml.service.BusinessService;
import com.lml.simulation.Simulation;
import com.lml.util.CreatedIcon;

public class ButtonsIFrame extends JFrame {
	private BusinessService service = new BusinessService();
	private JButton on, off, up, down;
	private JButton next;

	private JPanel imagePanel, panel;

	class OnAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				service.summer_switchon("1");
				service.summer_switchon("2");
				service.summer_switchon("3");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class OffAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				service.switchoff("1");
				service.switchoff("2");
				service.switchoff("3");

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class UpAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				service.sum_multi_dawn();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class DownAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				service.sum_multi_fade();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	class NextAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				ButtonsIFrame.this.setVisible(false);
				DemoIFrame frame = DemoIFrame.getInstance();
				frame.setVisible(true);
				Simulation simulation = new Simulation();
				Thread t=new Thread(simulation);
				t.start();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	public ButtonsIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("按钮体验");
		setBounds(560, 150, 900, 500);
		// setVisible(false);
		ImageIcon background = CreatedIcon.add("button.jpg");
		JLabel backgroundp = new JLabel(background);// 把背景图片显示在一个标签里面
		// 把标签的大小位置设置为图片刚好填充整个面板
		backgroundp.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		// 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		// 内容窗格默认的布局管理器为BorderLayout

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

		on = new JButton();
		on.addActionListener(new OnAction());
		// on.setText("登录");
		on.setBounds(0, 0, 487, 128);
		panel.add(on);
		on.setVisible(true);
		on.setOpaque(false);// 不可见
		on.setContentAreaFilled(false);// 区域不可填充

		off = new JButton();
		off.addActionListener(new OffAction());
		// on.setText("登录");
		off.setBounds(0, 128, 487, 129);
		panel.add(off);
		off.setVisible(true);
		off.setOpaque(false);//
		off.setContentAreaFilled(false);

		up = new JButton();
		up.addActionListener(new UpAction());
		// on.setText("登录");
		up.setBounds(0, 257, 487, 128);
		panel.add(up);
		up.setVisible(true);
		up.setOpaque(false);//
		up.setContentAreaFilled(false);

		down = new JButton();
		down.addActionListener(new DownAction());
		// on.setText("登录");
		down.setBounds(0, 385, 487, 128);
		panel.add(down);
		down.setVisible(true);
		down.setOpaque(false);//
		down.setContentAreaFilled(false);

		next = new JButton();
		next.addActionListener(new NextAction());
		// on.setText("登录");
		next.setBounds(0, 513, 487, 182);
		panel.add(next);
		next.setVisible(true);
		next.setOpaque(false);//
		next.setContentAreaFilled(false);

		// 把背景图片添加到分层窗格的最底层作为背景
		this.getLayeredPane().add(backgroundp, new Integer(Integer.MIN_VALUE));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(background.getIconWidth() + 16, background.getIconHeight() + 38);
		this.setVisible(true);
	}

}
