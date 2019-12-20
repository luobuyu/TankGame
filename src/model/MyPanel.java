package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel {
    private Vector<Tank> tanks;
    public MyPanel(){
        tanks = new Vector<Tank>();
        tanks.add(new PlayerTank(Const.PLAYER));

//        enemyTanks = new Vector<EnemyTank>();
//        playerTank = new PlayerTank(Const.PLAYER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画坦克
        for (Tank tank: tanks){
            tank.draw(g, this);
            tank.move();
        }
        repaint();
    }

    public Vector<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(Vector<Tank> tanks) {
        this.tanks = tanks;
    }
}
