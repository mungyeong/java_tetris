package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.model.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreTable extends JTable {

    public ScoreTable() {
        DefaultTableModel model = new TableModel();
        setModel(model);
        setLayout(null);
        setBounds(50,100,500,600);
        setRowHeight(50);
        setBackground(Color.GRAY);
        requestFocus();
    }

    public void reFresh() {
        DefaultTableModel model = new TableModel();
        setModel(model);
    }
}
