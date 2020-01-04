package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel {
    private Vector<Tank> tanks;
    private Vector<Bullet> bullets;
    private Vector<Barrier> barriers;
    private int curLevel;
    private long curTime;
    private Thread rePaintThread = null;
    public MyPanel(){
        this.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT);
        tanks = new Vector<Tank>();
        bullets = new Vector<Bullet>();
        tanks.add(new PlayerTank(Const.PLAYER, this));
        curLevel = 1;
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

        // 画坦克

        for(int i=0;i<tanks.size();i++){
            tanks.get(i).move();
            if(!tanks.get(i).isAlive()){
                if(i==0){
                    System.out.println("game over");
                }
                tanks.remove(i);
            }else {
                tanks.get(i).draw(g);
            }
        }

        // 画子弹
//        for (Bullet bullet: bullets){
//            bullet.move();
//            if(!bullet.isAlive()){
//                this.getBullets().remove(bullet);
//            }else {
//                bullet.draw(g);
//            }
//        }
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).move();
            if(!bullets.get(i).isAlive()){
                bullets.remove(i);
            }else {
                bullets.get(i).draw(g);
            }
        }

        // 画障碍物
        for (int i=0;i<barriers.size();i++){
            if(!barriers.get(i).isAlive()){
                barriers.remove(i);
            }else {
                barriers.get(i).draw(g, this);
            }
        }

    }

//    void
    /**
     * get and set
     * @return get
     */


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

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

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
