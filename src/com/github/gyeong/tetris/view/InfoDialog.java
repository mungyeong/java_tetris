package com.github.gyeong.tetris.view;

import javax.swing.*;

public class InfoDialog extends JDialog {

    private JTextField field_IP = new JTextField();
    private JTextField field_Port = new JTextField();
    private JLabel label_IP = new JLabel("IP");
    private JLabel label_Port = new JLabel("PORT");
    private JButton INPUT = new JButton("입력");
    private JButton CANCEL = new JButton("취소");
    private JTextPane header = new JTextPane();
    private String ip = null;
    private String port = null;

    public InfoDialog(Main main, String title) {
        super(main, title,true);
        setLayout(null);
        INPUT.addActionListener(e -> {
            setInfo(field_IP.getText(),field_Port.getText());
        });
        CANCEL.addActionListener(e -> {
            main.getBd().init();
            setVisible(false);
        });

        setLocationRelativeTo(Main.getInstance());
        setSize(300, 200);

        add(field_IP).setBounds(100,0,200,50);
        add(field_Port).setBounds(100,50,200,50);
        add(label_IP).setBounds(50,0,50,50);
        add(label_Port).setBounds(50,50,50,50);
        add(INPUT).setBounds(250,100,50,50);
        add(CANCEL).setBounds(200,100,50,50);
        setVisible(true);
    }

    public void setInfo(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }
    public String[] getInfo() {
        if(ip!=null&&port!=(null)){
            return new String[]{ip, port};
        }
        return new String[2];
    }
    public void init() {
        ip = null;
        port = null;
    }


}
