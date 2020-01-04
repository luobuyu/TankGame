package model;

import javax.swing.*;
import java.awt.*;

public class Tank implements Attacked{
    private int x;
    private int y;
    private int dir;
    private int hp;
    private int bulletType;
    private int fireGap;    // 开火间隔
    private int tankType;
    private boolean isAlive;
    private int speed;
    private long lastFireTime;  // 上次开火的时间

    // 传入的是 敌人 、 玩家
    public Tank(){
//        this.speed = Const.NOR_SPEED;
//        this.bulletType = Const.BULLET_NOR;
        this.hp = Const.MAX_HP;
        this.isAlive = true;
        this.lastFireTime = System.currentTimeMillis();
    }


    public void move(){
        switch (this.dir){
            case Const.UP:
                this.setY(this.getY()-speed);
                break;
            case Const.DOWN:
                this.setY(this.getY()+speed);
                break;
            case Const.LEFT:
                this.setX(this.getX()-speed);
                break;
            case Const.RIGHT:
                this.setX(this.getX()+speed);
                break;
        }
    }

    public void fire(){
        long t = System.currentTimeMillis();    // 此时时间
        if(t-this.getLastFireTime() >= fireGap){

            this.setLastFireTime(t);
        }
    }

    public void draw(Graphics g){

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if(hp < 0) {
            this.hp = 0;
            this.setAlive(false);
        }else {
            this.hp = hp;
        }
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    public int getFireGap() {
        return fireGap;
    }

    public void setFireGap(int fireGap) {
        this.fireGap = fireGap;
    }

    public int getTankType() {
        return tankType;
    }

    public void setTankType(int tankType) {
        this.tankType = tankType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public long getLastFireTime() {
        return lastFireTime;
    }

    public void setLastFireTime(long lastFireTime) {
        this.lastFireTime = lastFireTime;
    }

    @Override
    public void beAttacked(int damage) {
        this.setHp(this.getHp()-damage);
    }
}
