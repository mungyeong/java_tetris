package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.support.FileSteam;

import javax.swing.table.DefaultTableModel;
import java.util.Map;

public class TableModel extends DefaultTableModel {

    public TableModel() {
        Map<Integer, ScoreInfo> List = FileSteam.getInstance().getList();
        String[][] data = new String[List.size()][List.keySet().size()];
        for (int i : List.keySet()) {
            data[i] = List.get(i).getString(i);
        }
        for (String[] s : data) {
            for (String c : s) {
                System.out.println(c);
            }
            System.out.println();
        }
        String[] header = new String[]{"rank", "name", "score", "day", "play_time"};
        super.setDataVector(data, header);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }
}
