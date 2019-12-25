package model;

import javax.swing.*;

public class CollDete {
    public static boolean isCollide(int x1, int y1, int width1, int x2, int y2, int width2){
        // 100 100 60       160 160 60

        return (y2 < y1 || y2 < y1 + width1)&&(y1<y2 || y1<y2+width2)&&(x2<x1 || x2<x1+width1)&&(x1<x2 || x1<x2+width2);

    }

    /**
     *
     * @param x x
     * @param y y
     * @param width 宽度
     * @return 如果超出边界true， 否则 false
     */
    public static boolean isBeyondBorder(int x, int y, int width){
        return x < 0 || y < 0 || x > Const.WIN_WIDTH-Const.TankWidth || y > Const.WIN_HEIGHT-Const.TankWidth;
    }

}
