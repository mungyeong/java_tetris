package com.github.gyeong.tetris.view;

import com.github.gyeong.tetris.controller.Tetris;
import com.github.gyeong.tetris.model.Tetromino;
import com.github.gyeong.tetris.controller.support.Colors;

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
            int width = getWidth() / (next.getWidth()+2);
            int height = getHeight() / (next.getHeight()+2);
            int t_widht = next.getWidth(), t_height = next.getHeight();
            int t_x = next.getX(), t_y = next.getY();
            for (int h = 0; h < t_height; h++) {
                for (int w = 0; w < t_widht; w++) {
                    if (next.getBlock()[h][w] > 0) {
                        Color temp = Colors.getColor(next.getType());
                        g.setColor(temp);
                        g.fillRect((w) * width, (h) * height, width, height);
                        g.setColor(Color.BLACK);
                        g.drawLine((w*width),(h*height),((w+1)*width),((h)*height));
                        g.drawLine((w*width),(h*height),((w)*width),((h+1)*height));
                        g.drawLine((w+1*width),(h*height),((w+1)*width),((h)*height));
                        g.drawLine((w*width),(h+1*height),((w)*width),((h+1)*height));
                    }
                }
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
