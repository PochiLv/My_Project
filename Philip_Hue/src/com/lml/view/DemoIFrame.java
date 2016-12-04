package com.lml.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;




import com.lml.util.CreatedIcon;
import com.lml.view.ButtonsIFrame.NextAction;

public class DemoIFrame extends JFrame{
	private static DemoIFrame demoIFrame=new DemoIFrame();
	private JPanel imagePanel,panel;
	private ImageIcon background;
	private JLabel step1;
	private JLabel step2;
	private JLabel step3;
	private JLabel step4;
	private JLabel step5;
	private JButton back;

	

	public JLabel getStep1() {
		return step1;
	}



	public void setStep1(JLabel step1) {
		this.step1 = step1;
	}



	public JLabel getStep2() {
		return step2;
	}



	public void setStep2(JLabel step2) {
		this.step2 = step2;
	}



	public JLabel getStep3() {
		return step3;
	}



	public void setStep3(JLabel step3) {
		this.step3 = step3;
	}



	public JLabel getStep4() {
		return step4;
	}



	public void setStep4(JLabel step4) {
		this.step4 = step4;
	}



	public JLabel getStep5() {
		return step5;
	}



	public void setStep5(JLabel step5) {
		this.step5 = step5;
	}

	class BackAction implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			try {
				ButtonsIFrame frame = new ButtonsIFrame();
				frame.setVisible(true);
				DemoIFrame.this.setVisible(false);
				step1.setText("");
				step2.setText("");
				step3.setText("");
				step4.setText("");
				step5.setText("");
				step1.setEnabled(true);
				step2.setEnabled(true);
				step3.setEnabled(true);
				step4.setEnabled(true);
				step5.setEnabled(true);
				
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public DemoIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		borderLayout.setVgap(10);
		getContentPane().setLayout(borderLayout);
		setTitle("示例展示");
		setBounds(560, 120, 900, 500);
		//setVisible(false);
	 	ImageIcon background=CreatedIcon.add("demo.jpg");
        JLabel backgroundp = new JLabel(background);//把背景图片显示在一个标签里面
        //把标签的大小位置设置为图片刚好填充整个面板  
        backgroundp.setBounds(0,0,background.getIconWidth(),background.getIconHeight());
        //把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
        imagePanel = (JPanel)this.getContentPane();
        imagePanel.setOpaque(false);
        //内容窗格默认的布局管理器为BorderLayout
	

	//	final JPanel panel = new JPanel();

        panel=new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
	//	getContentPane().add(panel);
		panel.setOpaque(false);
		getContentPane().add(panel,BorderLayout.CENTER);
		//panel.setPreferredSize(new Dimension(30, 50));
		final JLabel labelS = new JLabel();
		labelS.setHorizontalAlignment(SwingConstants.CENTER);
		//labelS.setPreferredSize(new Dimension(3,1));
		panel.add(labelS);
		
		//-------------------基本信息-------------------------//
		this.step2 = new JLabel();
		step2.setHorizontalAlignment(SwingConstants.CENTER);
		step2.setBounds(100,140,288,32);
		panel.add(step2);
		//step2.setText("夜间检测体验者醒来");
		//step2.setText("Wake up at night");
		step2.setForeground(new Color(81,83,85));
		step2.setFont(new Font("微软雅黑", Font.PLAIN, 25)); 


		
		this.step3 = new JLabel();
		step3.setHorizontalAlignment(SwingConstants.CENTER);
		step3.setBounds(100,200,288,32);
		panel.add(step3);
		//step3.setText("\u4F53\u9A8C\u8005\u79BB\u5F00\u623F\u95F4");
		//step3.setText("Leave the room");
		step3.setForeground(new Color(81,83,85));
		step3.setFont(new Font("微软雅黑", Font.PLAIN, 25)); 
		
		this.step1 = new JLabel("\u68C0\u6D4B\u4F53\u9A8C\u8005\u7761\u7740");
		this.step1 = new JLabel();
		step1.setHorizontalAlignment(SwingConstants.CENTER);
		step1.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		step1.setBounds(100, 80, 288,32);
		step1.setForeground(new Color(81,83,85));
		panel.add(step1);
		
		this.step4 = new JLabel();
		//step4.setText("\u4F53\u9A8C\u8005\u56DE\u5230\u623F\u95F4");
	//	step4.setText("Back to the room");
		step4.setHorizontalAlignment(SwingConstants.CENTER);
		step4.setForeground(new Color(81,83,85));
		step4.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		step4.setBounds(100, 260, 288,32);
		panel.add(step4);
		
		this.step5 = new JLabel();
		//step5.setText("\u4F53\u9A8C\u8005\u518D\u6B21\u7761\u7740");
		//step5.setText("Fall in sleep again");
		step5.setHorizontalAlignment(SwingConstants.CENTER);
		step5.setForeground(new Color(81,83,85));
		step5.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		step5.setBounds(100, 320, 288,32);
		panel.add(step5);
		
		back=new JButton();
		back.setBounds(151,575,195,150);
		back.addActionListener(new BackAction());
		// on.setText("登录");
		
		panel.add(back);
		back.setVisible(true);
		back.setOpaque(false);//
		back.setContentAreaFilled(false);
	
		
		
   
	     
        //把背景图片添加到分层窗格的最底层作为背景
        this.getLayeredPane().add(backgroundp,new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth()+16,background.getIconHeight()+38);
        this.setVisible(true);	
	}
	
	public static DemoIFrame getInstance(){
		return demoIFrame;
	}
}


