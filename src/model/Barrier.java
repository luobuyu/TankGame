package model;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;

public class Barrier {
    private int type;
    private int x;
    private int y;
    private int width;
    private boolean alive;
    private MyPanel father;

    public Barrier(int type, int x, int y, int width, MyPanel father) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.width = width;
        this.alive = true;
        this.father = father;
    }


    public static Vector<Barrier> readMap(int i, MyPanel father) {
        // 23*20
        String path = "map/map" + i + ".txt";
        Vector<Barrier> barriers = new Vector<Barrier>();
        barriers.add(new Barrier(Const.home, 23 * Const.width, 37 * Const.width, Const.TankWidth, father));
        BufferedReader reader = null;
        int row = 0;
        String line;
        try {
            reader = new BufferedReader(new FileReader(new File(path)));
            while ((line = reader.readLine()) != null) {
                for (int j = 0; j < line.length(); j++) {

                    if (line.charAt(j) > '0' && line.charAt(j) < '9') {
                        switch (line.charAt(j)) {
                            case '2':
                                barriers.add(new Barrier(Const.grass, j * Const.width, row * Const.width, Const.width, father));
                                break;
                            case '3':
                                barriers.add(new Barrier(Const.brick, j * Const.width, row * Const.width, Const.width, father));
                                break;
                            case '4':
                                barriers.add(new Barrier(Const.steel, j * Const.width, row * Const.width, Const.width, father));
                                break;
                            default:
                                break;
                        }
                    }
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return barriers;
    }

    public void draw(Graphics g, MyPanel father){
        String path = null;
        switch (this.type){
            case Const.home:
                path = "pictures/map/home.png";
                break;
            case Const.grass:
                path = "pictures/map/Grass.png";
                break;
            case Const.brick:
                path = "pictures/map/Brick.png";
                break;
            case Const.steel:
                path = "pictures/map/steel.png";
                break;
            default:
                break;
        }
        g.drawImage(Toolkit.getDefaultToolkit().getImage(path), this.getX(), this.getY(), this.getWidth(), this.getWidth(), father);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isAlive() {
        return alive;
    }

    public MyPanel getFather() {
        return father;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

}
