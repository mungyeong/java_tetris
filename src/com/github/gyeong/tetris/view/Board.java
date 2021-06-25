package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.controller.MultiPlays;
import com.github.gyeong.tetris.controller.SinglePlays;
import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.controller.support.Event;
import com.github.gyeong.tetris.controller.support.Score;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Board extends JPanel {

    private int type = 2; // 0:클라이언트 1:서버 2:싱글플레이

    private static Board board;

    private Main main = Main.getInstance();

    private MultiPlays multi = null;

    private SinglePlays single = null;

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
        single = new SinglePlays(this, 400, 800);
        tetris = single;
        preview = new Preview(tetris);
        add(score);
        add(tetris);
        add(preview);
        setBounds(0, 0, 600, 800);
        setLayout(null);
        setBackground(Color.BLACK);
        setVisible(true);
        init();
    }

    public void init() {
        event_Set();
        score.init();
        preview.init();
        tetris.init();
        btn[1].setText("시작");
        if(type != 2){
            Btn_Change();
        } else {
            updateBtn(true);
        }
        repaint();
    }

    public void event_Set() {
        for (int i = 0; i < btn.length; i++) {
            int index = i, x = 0, y = 0, w = 0, h = 50;
            for (ActionListener e : btn[i].getActionListeners()) {
                btn[i].removeActionListener(e);
            }
            btn[i].addActionListener(e -> {
                event(index);
            });
            if (i == 0) {
                x = 500;
                y = 0;
                w = 70;
            } else if (i == 1) {
                x = 450;
                y = 600;
                w = 100;
            } else if (i == 2) {
                x = 500;
                y = 700;
                w = 100;
            } else if (i == 3) {
                x = 400;
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
            setTetris(false);
            init();
            Event.change(2);
        }
        if (index == 1) {
            if (type != 2) {
                tetris.sendState();
            } else {
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
            }
            Btn_Change();
        }
        if (index == 2) {
            if (JOptionPane.showConfirmDialog(null, "재도전하시겠습니까?", "재도전",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == 0) {
                type = 2;
                init();
            }
        }
        if (index == 3) {
            if (tetris.getState().getState() == 1) {
                tetris.pause();
                Btn_Change();
            }
            Object[] options = {"Client", "Server"};
            type = JOptionPane.showOptionDialog(null, "서버 or 클라이언트?", "멀티플레이 선택",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, -1);
            System.out.println(this.type);
            updateBtn(false);
            if (type == 0) {
                setTetris(true);
                init();
                setClient();
            }
            if (type == 1) {
                setTetris(true);
                init();
                setServer();
            }
            if (type == -1) {
                setType();
                if (tetris.getState().getState() == 2) {
                    tetris.resume();
                    Btn_Change();
                } else {
                    btn[1].setText("시작");
                }
                updateBtn(true);
            }
        }
        tetris.requestFocus();
        btn[index].setEnabled(true);
    }

    public void Btn_Change() {
        String text;
        if (type != 2) {
            if (btn[1].getText() != "준비") {
                text = "준비";
            } else {
                text = "준비완료";
            }
        } else {
            if (btn[1].getText() != "시작") {
                text = "시작";
            } else {
                text = "중지";
            }
        }
        btn[1].setText(text);
    }

    public void showError(String errormsg) {
        JOptionPane.showMessageDialog(null, errormsg, "에러", JOptionPane.ERROR_MESSAGE);
    }

    public void showScore(Integer opponent) {
        Integer score = tetris.getScore().getIntScore();
        String msg = "상대방 점수는 " + opponent + "입니다.";
        String title = null;
        if(score > opponent){
            title = "게임 승리";
        }
        if(score < opponent){
            title = "게임 패배";
        }
        if(score == opponent){
            title = "게임 비김";
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
        tetris.setRead(null);
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
        InfoDialog popup = new InfoDialog(null, "서버 접속정보 입력창");
        info = popup.getInfo();
        if (info != null&&!(info[0].equals("") && info[1].equals(""))) {
            tetris.setType(info[0], info[1]);
        } else {
            setType();
            setTetris(false);
            btn[1].setText("시작");
            updateBtn(true);
        }
    }

    public void setServer() {
        tetris.setType();
        String[] info = tetris.getInfo();
        JOptionPane.showMessageDialog(null, "IP: " + info[0] + "\nPORT: " + info[1] + "\n60초 동안 접속 가능\n 접속시 버튼 활성화",
                "서버 접속정보", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setTetris(boolean mode) {
        remove(this.tetris);
        if (mode) {
            multi = new MultiPlays(this, 400, 800);
            this.tetris = multi;
        } else {
            single = new SinglePlays(this, 400, 800);
            this.tetris = single;
        }
        add(this.tetris);
        init();
        repaint();
    }

    public void setType() {
        type = 2;
    }
}
