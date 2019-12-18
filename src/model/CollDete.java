package model;

public class CollDete {
    public static boolean isCollide(int x1, int y1, int width1, int x2, int y2, int width2){
        return (x1 > x2 || x1 + width1 > x2)
                && (x2 > x1 || x2 + width2 > x1)
                && (y1 > y2 || y1 + width1 > y2)
                && (y2 > y1 || y2 + width2 > y1);
    }
//    public static boolean isBeyondBorder(Position p, int width){
//        Position p2 = new Position(p.x+width, p.y+width);
//        return p.x < 0 || p.y < 0 || p2.x > Const.mapWidth || p2.y > Const.mapHeight;
//    }
}
