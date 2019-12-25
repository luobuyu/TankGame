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
    private Thread rePaintThread = null;
    public MyPanel(){
        this.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT);
        tanks = new Vector<Tank>();
        tanks.add(new PlayerTank(Const.PLAYER));
        curLevel = 2;
        this.barriers = Barrier.readMap(curLevel);
//        enemyTanks = new Vector<EnemyTank>();
//        playerTank = new PlayerTank(Const.PLAYER);
        rePaintThread = new Thread(){
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }

            }
        };

        rePaintThread.start();
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
