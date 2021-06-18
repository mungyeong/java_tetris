package com.github.gyeong.tetris.controller.support;

import java.awt.*;

public final class Colors {
    private static final Color I = new Color(80,188,223);     // I 도형(0)
    private static final Color L = Color.BLUE;                         // L 도형(1)
    private static final Color J = new Color(248,155,0);      // J 도형(2)
    private static final Color O = Color.YELLOW;                       // O 도형(3)
    private static final Color S = Color.green;                        // S 도형(4)
    private static final Color T = new Color(102,0,153);      // T 도형(5)
    private static final Color Z = Color.RED;                          // Z 도형(6)
    private static final Color attack = Color.DARK_GRAY;                          // Z 도형(6)

    public static Color getColor(int num) {
        switch (num){
            case 1:
                return I;
            case 2:
                return L;
            case 3:
                return J;
            case 4:
                return O;
            case 5:
                return S;
            case 6:
                return T;
            case 7:
                return Z;
            case 8:
                return attack;
            default:
                return Color.WHITE;
        }
    }

    public static Color getRd(){
        return getColor((int)(Math.random()*7)+1);
    }
}
