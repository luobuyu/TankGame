package model;

import java.awt.*;

public class Birth {
    private int x;
    private int y;
    private int cur;
    private final static String[] img = new String[]{"pictures/map/star0.gif", "pictures/map/star1.gif", "pictures/map/star2.gif", "pictures/map/star3.gif"};
    private boolean isAlive;
    private long lastPutImgTime;
    private final static int width = Const.TankWidth;
    private final static int total = 4;
    private MyPanel father;

    public Birth(int x, int y, MyPanel father) {
        this.x = x;
        this.y = y;
        this.cur = 0;
        this.isAlive = true;
        this.lastPutImgTime = System.currentTimeMillis();
        this.father = father;
    }

    public void draw(Graphics g) {
        long t = System.currentTimeMillis();
        g.drawImage(Toolkit.getDefaultToolkit().getImage(img[cur]), this.getX(), this.getY(), width, width, this.getFather());
        if(t - this.getLastPutImgTime() > Const.Img_Gap) {
            cur++;
            this.setLastPutImgTime(t);
        }
        this.setAlive(cur != 4);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public static String[] getImg() {
        return img;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public long getLastPutImgTime() {
        return lastPutImgTime;
    }

    public void setLastPutImgTime(long lastPutImgTime) {
        this.lastPutImgTime = lastPutImgTime;
    }

    public int getWidth() {
        return width;
    }


    public int getTotal() {
        return total;
    }

    public MyPanel getFather() {
        return father;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }
}
