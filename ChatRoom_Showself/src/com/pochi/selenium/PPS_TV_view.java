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
    //这部分是最底下那个Frame框架
    private JFrame frame;
    private JTextField text_url;

    /**
     * Launch the application.
     */
    //这个main都是windowBuilder自己弄的，不用管
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
     * 初始化
     */
    public PPS_TV_view() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * 初始化frame
     */
    private void initialize() {
        frame = new JFrame();
        //使得窗口锁死，不能最大化
        frame.setResizable(false);
        //窗口上的那个奇秀的图标
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Eclipse\\Documents\\Pro_PPS\\87258PICzud_1024.jpg"));
        //奇秀挂机精灵
        frame.setTitle("\u5947\u79C0\u6302\u673A\u7CBE\u7075");
        //窗口大小
        frame.setBounds(100, 100, 450, 188);
        //点右上角的叉可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //没有设layout，里面的组件可以随意拖动
        frame.getContentPane().setLayout(null);

        //创建一个文本框，用来输入主播房间的地址
        text_url = new JTextField();
        //每次点到这个文本框，就把里面的内容先清空一下
        text_url.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                text_url.setText("");
            }
        });
        //这个文本框的大小设定
        text_url.setBounds(196, 22, 228, 36);
        //将这个文本框添加到窗口上原有的那个Pane上
        frame.getContentPane().add(text_url);
        //设置文本框的长度
        text_url.setColumns(10);

        //创建一个button按钮，上面写“开始挂机”
        JButton button_start = new JButton("\u5F00\u59CB\u6302\u673A");
        //添加监听器
        button_start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //1. 拿到别人填的主播房间的地址
                String anchor_url=text_url.getText();
                //2. 先健壮性判断下有没有人就没写
                if(anchor_url==null||anchor_url.equals("")){
                    //没写就在文本框提示不能为空，然后这个方法就结束
                    text_url.setText("主播房间地址不能为空！");
                    return;
                }
                //3. 创建PPS_sent这个类，把获得的主播地址传进去
                PPS_sent pps_sent = new PPS_sent(anchor_url);
                //4. 因为这个类是实现了Runnable接口的，所以打开多线程，运行
                Thread t1=new Thread(pps_sent);
                t1.start();
            }
        });
        // 设置其大小
        button_start.setBounds(196, 93, 93, 23);
        // 将这个按钮添加到pane上
        frame.getContentPane().add(button_start);

        // 另一个停止挂机的按钮
        JButton button_stop = new JButton("\u505C\u6B62");
        // 监听到点击就直接退出
        button_stop.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // 设置大小
        button_stop.setBounds(318, 93, 93, 23);
        // 添加到pane
        frame.getContentPane().add(button_stop);

        //这部分是写字的请输入主播房间地址
        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.control);
        textPane.setFont(new Font("宋体", Font.PLAIN, 14));
        textPane.setForeground(Color.BLACK);
        textPane.setText("\u8BF7\u8F93\u5165\u4E3B\u64AD\u623F\u95F4\u5730\u5740");
        textPane.setBounds(19, 30, 167, 48);
        frame.getContentPane().add(textPane);
    }
}