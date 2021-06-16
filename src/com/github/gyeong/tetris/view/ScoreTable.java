package com.github.gyeong.tetris.view;


import com.github.gyeong.tetris.model.TableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

public class ScoreTable extends JTable {
    private DefaultTableCellRenderer center = new DefaultTableCellRenderer();

    String[] header_name = new String[]{"rank", "name", "score", "day", "play_time"};

    JTableHeader header = null;


    public ScoreTable() {
        header = getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        DefaultTableModel model = new TableModel();
        center.setHorizontalAlignment(JLabel.CENTER);
        setModel(model);
        setLayout(null);
        setup();
        setBounds(50, 100, 500, 600);
        setForeground(Color.white);
        setRowHeight(60);
        setBackground(new Color(56, 61, 63));
    }

    public void reFresh() {
        DefaultTableModel model = new TableModel();
        setModel(model);
        setup();

    }

    private void setup() {
        for (int i = 0; i < getColumnCount(); i++) {
            TableColumn tableColumn = getColumn(header_name[i]);

            tableColumn.setCellRenderer(center);
            if (header_name[i].equals("day")) {
                tableColumn.setPreferredWidth(200);

            }
        }
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        header.setDefaultRenderer(renderer);
        header.setEnabled(false);
        requestFocus();
    }
}