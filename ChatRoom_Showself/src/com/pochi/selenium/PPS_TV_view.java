package com.pochi.selenium;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PPS_TV_view {
    //�ⲿ����������Ǹ�Frame���
    private JFrame frame;
    private JTextField text_url;

    /**
     * Launch the application.
     */
    //���main����windowBuilder�Լ�Ū�ģ����ù�
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PPS_TV_view window = new PPS_TV_view();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     * ��ʼ��
     */
    public PPS_TV_view() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * ��ʼ��frame
     */
    private void initialize() {
        frame = new JFrame();
        //ʹ�ô����������������
        frame.setResizable(false);
        //�����ϵ��Ǹ������ͼ��
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Eclipse\\Documents\\Pro_PPS\\87258PICzud_1024.jpg"));
        //����һ�����
        frame.setTitle("\u5947\u79C0\u6302\u673A\u7CBE\u7075");
        //���ڴ�С
        frame.setBounds(100, 100, 450, 188);
        //�����ϽǵĲ���Թر�
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //û����layout�������������������϶�
        frame.getContentPane().setLayout(null);

        //����һ���ı�������������������ĵ�ַ
        text_url = new JTextField();
        //ÿ�ε㵽����ı��򣬾Ͱ���������������һ��
        text_url.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                text_url.setText("");
            }
        });
        //����ı���Ĵ�С�趨
        text_url.setBounds(196, 22, 228, 36);
        //������ı�����ӵ�������ԭ�е��Ǹ�Pane��
        frame.getContentPane().add(text_url);
        //�����ı���ĳ���
        text_url.setColumns(10);

        //����һ��button��ť������д����ʼ�һ���
        JButton button_start = new JButton("\u5F00\u59CB\u6302\u673A");
        //��Ӽ�����
        button_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //1. �õ����������������ĵ�ַ
                String anchor_url=text_url.getText();
                //2. �Ƚ�׳���ж�����û���˾�ûд
                if(anchor_url==null||anchor_url.equals("")){
                    //ûд�����ı�����ʾ����Ϊ�գ�Ȼ����������ͽ���
                    text_url.setText("���������ַ����Ϊ�գ�");
                    return;
                }
                //3. ����PPS_sent����࣬�ѻ�õ�������ַ����ȥ
                PPS_sent pps_sent = new PPS_sent(anchor_url);
                //4. ��Ϊ�������ʵ����Runnable�ӿڵģ����Դ򿪶��̣߳�����
                Thread t1=new Thread(pps_sent);
                t1.start();
            }
        });
        // �������С
        button_start.setBounds(196, 93, 93, 23);
        // �������ť��ӵ�pane��
        frame.getContentPane().add(button_start);

        // ��һ��ֹͣ�һ��İ�ť
        JButton button_stop = new JButton("\u505C\u6B62");
        // �����������ֱ���˳�
        button_stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // ���ô�С
        button_stop.setBounds(318, 93, 93, 23);
        // ��ӵ�pane
        frame.getContentPane().add(button_stop);

        //�ⲿ����д�ֵ����������������ַ
        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.control);
        textPane.setFont(new Font("����", Font.PLAIN, 14));
        textPane.setForeground(Color.BLACK);
        textPane.setText("\u8BF7\u8F93\u5165\u4E3B\u64AD\u623F\u95F4\u5730\u5740");
        textPane.setBounds(19, 30, 167, 48);
        frame.getContentPane().add(textPane);
    }
}