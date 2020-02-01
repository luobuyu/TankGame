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
    private Vector<Explosion> explosions;
    private int curLevel;
    private long curTime;
    private Thread rePaintThread = null;
    public MyPanel(){
        this.setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT);
        tanks = new Vector<Tank>();
        bullets = new Vector<Bullet>();
        explosions = new Vector<Explosion>();
        curLevel = 1;
        this.barriers = Barrier.readMap(curLevel, this);
        tanks.add(new PlayerTank(Const.PLAYER, this, 17 * Const.width, 37 * Const.width));
        tanks.add(new EnemyTank(Const.ENEMY, this, 0 * Const.width, 0 * Const.width));
        tanks.add(new EnemyTank(Const.ENEMY, this, 3 * Const.width, 0 * Const.width));
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
        this.setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 画背景
        g.drawImage(Toolkit.getDefaultToolkit().getImage("pictures/map/bg.jpg"), 0, 0, Const.WIN_WIDTH, Const.WIN_HEIGHT, this);
        // 需要使用for int i 循环，不能使用 for each 循环
        // 画坦克
        for(int i=0;i<tanks.size();i++){
            tanks.get(i).move();
            tanks.get(i).fire();
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
        for(int i=0;i<bullets.size();i++){
            bullets.get(i).move();
            if(!bullets.get(i).isAlive()){
                bullets.remove(i);
            }else {
                bullets.get(i).draw(g);
            }
        }

        // 画爆炸
        for (int i = 0; i < explosions.size(); i++) {
            if(!explosions.get(i).isAlive()) {
                explosions.remove(i);
            } else {
                explosions.get(i).draw(g);
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

    public void addEnemyTank(){
        if(EnemyTank.numOfBirthPlace(this)==-1){
            this.getTanks().add(new EnemyTank(Const.ENEMY, this, 0, 0));
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

    public Vector<Explosion> getExplosions() {
        return explosions;
    }

    public void setExplosions(Vector<Explosion> explosions) {
        this.explosions = explosions;
    }

    public Thread getRePaintThread() {
        return rePaintThread;
    }

    public void setRePaintThread(Thread rePaintThread) {
        this.rePaintThread = rePaintThread;
    }
}
