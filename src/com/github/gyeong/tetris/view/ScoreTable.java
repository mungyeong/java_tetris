package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.model.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ScoreTable extends JTable {
    private DefaultTableCellRenderer center = new DefaultTableCellRenderer();


    public ScoreTable() {
        DefaultTableModel model = new TableModel();
        center.setHorizontalAlignment(JLabel.CENTER);
        setModel(model);
        setLayout(null);
        getColumn("day").setPreferredWidth(200);
        getColumn("day").setCellRenderer(center);
        getColumn("play_time").setCellRenderer(center);
        getColumn("name").setCellRenderer(center);
        getColumn("score").setCellRenderer(center);
        setBounds(50,100,500,600);
        setForeground(Color.white);
        setRowHeight(60);
        setBackground(Color.BLACK);
        requestFocus();
    }

    public void reFresh() {
        DefaultTableModel model = new TableModel();
        setModel(model);
        getColumn("day").setPreferredWidth(200);
        getColumn("day").setCellRenderer(center);
        getColumn("play_time").setCellRenderer(center);
        getColumn("name").setCellRenderer(center);
        getColumn("score").setCellRenderer(center);
        requestFocus();
    }
}