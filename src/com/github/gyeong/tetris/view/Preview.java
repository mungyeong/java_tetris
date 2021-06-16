package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.model.data.Tetromino;
import com.github.gyeong.tetris.view.support.Colors;

import javax.swing.*;
import java.awt.*;

public class Preview extends JPanel {

    private Tetris tetris;

    private Tetromino next;

    public Preview(Tetris tetris) {
        this.tetris = tetris;
        setBackground(Color.DARK_GRAY);
        setBounds(400, 150, 200, 200);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (next != null) {
            int width = getWidth() / next.getWidth();
            int height = getHeight() / next.getHeight();
            int t_widht = next.getWidth(), t_height = next.getHeight();
            int t_x = next.getX(), t_y = next.getY();
            for (int h = 0; h < t_height; h++) {
                for (int w = 0; w < t_widht; w++) {
                    if (next.getBlock()[h][w] > 0) {
                        Color temp = Colors.getColor(next.getType());
                        g.setColor(temp);
                        g.fillRect((w) * width, (h) * height, width, height);
                    }
                }
            }
            g.setColor(Color.WHITE);
            for (int w = 0; w <= getWidth(); w += width) {         //게임보드 선 출력
                g.drawLine(w, 0, w, getHeight());
            }
            for (int h = 0; h <= getHeight(); h += height) {
                g.drawLine(0, h, getWidth(), h);
            }
        } else {
            g.clearRect(0,0,getWidth(),getHeight());
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }

    public void update(Tetromino next) {
        this.next = next;
        repaint();
    }

    public void init() {
        this.next = null;
        repaint();
    }

}
