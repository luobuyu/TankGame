package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel {
    private Vector<Tank> tanks;
    private long curTime;
    private Vector<Barrier> barriers;
    private int curLevel;
    public MyPanel(){
        tanks = new Vector<Tank>();
        tanks.add(new PlayerTank(Const.PLAYER));
        curLevel = 1;
        this.barriers = Barrier.readMap(curLevel);
//        enemyTanks = new Vector<EnemyTank>();
//        playerTank = new PlayerTank(Const.PLAYER);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画障碍物
        for (Barrier barrier: barriers){
            barrier.draw(g, this);
        }

        // 画坦克
        for (Tank tank: tanks){
            tank.draw(g, this);
            curTime = System.currentTimeMillis();
            if(curTime-tank.getLastMoveTime()>tank.getMoveGap()){
                tank.move();
                tank.setLastMoveTime(curTime);
            }
        }
        repaint();
    }

//    void


    public Vector<Barrier> getBarriers() {
        return barriers;
    }

    public void setBarriers(Vector<Barrier> barriers) {
        this.barriers = barriers;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    /**
     * get and set
     * @return get
     */


    public Vector<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(Vector<Tank> tanks) {
        this.tanks = tanks;
    }

    public long getCurTime() {
        return curTime;
    }

    public void setCurTime(long curTime) {
        this.curTime = curTime;
    }
}
