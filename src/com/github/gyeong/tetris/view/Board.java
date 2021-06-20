package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.controller.support.Event;
import com.github.gyeong.tetris.controller.support.Score;
import com.github.gyeong.tetris.model.network.SocketServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Board extends JPanel {

    private int type = 2; // 0:클라이언트 1:서버 2:싱글플레이

    private static Board board;

    private Main main = Main.getInstance();

    private Score score;

    private Tetris tetris;

    private Preview preview;

    private JButton btn[] = {
            new JButton("메뉴"),
            new JButton("시작"),
            new JButton("재도전"),
            new JButton("멀티플레이")
    };

    private JTextPane serverInfo = new JTextPane();

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    private Board() {
        score = Score.getInstance();
        tetris = new Tetris(this, 400, 800);
        preview = new Preview(tetris);
        add(score);
        add(tetris);
        add(preview);
        setBounds(0, 0, 600, 800);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        getRootPane();
        init();
    }

    public void event_Set() {
        for (int i = 0; i < btn.length; i++) {
            int index = i, x = 0, y = 0, w = 0, h = 50;
            for (ActionListener e : btn[i].getActionListeners()){
                btn[i].removeActionListener(e);
            }
            btn[i].addActionListener(e -> {
                event(index);
            });
            if (i == 0) {
                x = 550;
                y = 0;
                w = 50;
            } else if (i == 1) {
                x = 450;
                y = 600;
                w = 100;
            } else if (i == 2) {
                x = 550;
                y = 700;
                w = 50;
            } else if (i == 3) {
                x = 450;
                y = 700;
                w = 100;
            }
            add(btn[i]).setBounds(x, y, w, h);
        }
    }

    public void event(int index) {
        btn[index].setEnabled(false);
        if (index == 0) {
            tetris.stop();
            init();
            Event.change(2);
        }
        if (index == 1) {
            switch (tetris.getState().getState()) {
                case 3:
                case 0:
                    tetris.start();
                    break;
                case 1:
                    tetris.pause();
                    break;
                case 2:
                    tetris.resume();
                    break;
            }
            if (btn[1].getText() == "중지") {
                Btn_init();
            } else {
                btn[1].setText("중지");
            }
        }
        if (index == 2) {
            if (JOptionPane.showConfirmDialog(main, "재도전하시겠습니까?", "재도전",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                type = 2;
                init();
            }
        }
        if (index == 3) {
            if(tetris.getState().getState() == 1){
                tetris.pause();
                Btn_init();
            }
            Object[] options = {"Client", "Server"};
            type = JOptionPane.showOptionDialog(main, "서버 or 클라이언트?", "멀티플레이 선택",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, -1);
            System.out.println(this.type);
            if (type == 0) {
                setClient();
                init();
            }
            if (type == 1) {
                setServer();
                init();
            }
            if(type == -1){
                type = 2;
                if(tetris.getState().getState() == 2){
                    tetris.resume();
                    Btn_init();
                }
            }

        }
        tetris.requestFocus();
        btn[index].setEnabled(true);
    }

    public void init() {
        event_Set();
        Btn_init();
        score.init();
        preview.init();
        tetris.init();
        updateBtn(true);
        setType(2);
    }

    public void Btn_init() {
        String text = "시작";
        if (type != 2) {
            text = "준비";
        }
        btn[1].setText(text);
    }

    public void update() {
        score.update_Score(tetris.getScore().getIntScore());
        preview.update(tetris.getNext());
    }

    public void updateBtn(boolean enable) {
        btn[1].setEnabled(enable);
    }

    public void setClient() {
        String info[] = null;
        InfoDialog popup = new InfoDialog(main, "서버 접속정보 입력창");
        updateBtn(false);
        info = popup.getInfo();
        if(SocketServer.ipCheck(info[0])) {
            setType(info[0], info[1], type);
            setReady();
        }
        else if (info[0]==null&&info[1]==null) {
            JOptionPane.showMessageDialog(main, "재입력 부탁드립니다.", "잘못된 정보 입력", JOptionPane.WARNING_MESSAGE);
            setClient();
        }
    }

    public void setServer() {
        updateBtn(false);
        setType(type);
        String[] info = tetris.getInfo();
        JOptionPane.showMessageDialog(main, "IP: " + info[0] + "\nPORT: " + info[1]+"\n60초 동안 접속 가능\n 접속시 버튼 활성화",
                "서버 접속정보", JOptionPane.INFORMATION_MESSAGE);
        setReady();
    }

    public void setType(int type) {
        this.type = type;
        if (type == 1) {
            tetris.setType();
        }
    }

    public void setType(String ip, String port, int type) {
        this.type = type;
        tetris.setType(ip, port);
    }

    public void showError(String errormsg) {
        JOptionPane.showMessageDialog(main, errormsg, "에러", JOptionPane.ERROR_MESSAGE);
    }

    public void showScore(String msg) {
        JOptionPane.showMessageDialog(main, msg, "점수", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setReady() {
        for (ActionListener e : btn[1].getActionListeners()){
            btn[1].removeActionListener(e);
        }
        btn[1].addActionListener(e -> {
            tetris.start();
            btn[1].setText("준비완료");
            btn[1].repaint();
            btn[1].validate();
        });
    }
}
