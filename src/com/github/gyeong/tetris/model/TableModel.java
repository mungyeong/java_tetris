package com.github.gyeong.tetris.model;

import com.github.gyeong.tetris.support.FileSteam;

import javax.swing.table.DefaultTableModel;
import java.util.Map;

public class TableModel extends DefaultTableModel {

    public TableModel() {
        this.setDataVector();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return false;
    }
    public void setDataVector() {
        Map<Integer, ScoreInfo> List = FileSteam.getInstance().getList();
        String[][] data = new String[List.size()][List.keySet().size()];
        for(int i: List.keySet()){
            data[Integer.valueOf(List.get(i).getRank())] = List.get(i).getString();
        }
        String[] header = new String[]{"rank", "play_time", "name", "score", "day"};
        super.setDataVector(data, header);
    }
}
