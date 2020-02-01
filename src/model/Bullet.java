package model;

import java.awt.*;
import java.util.Random;

public class Bullet {

    private int x;      // 像素点
    private int y;
    private int damage; // 伤害
    private int dir;
    private int bulletType;     // 子弹种类
    private int speed;  // 射速
    private int belongTo;   // 谁发出的， 敌方还是玩家
    private boolean alive; // 是否存在， 如果是 false则播放爆炸图片
    private MyPanel father;

    public Bullet(int x, int y, int dir, int bulletType, int belongTo, MyPanel father) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.bulletType = bulletType;
        this.belongTo = belongTo;
        this.alive = true;
        this.setFather(father);

        switch (bulletType){
            case Const.BULLET_NOR:
                this.speed = Const.BulletNorSpeed;
                this.damage = Const.NOR_DAMAGE + new Random().nextInt(Const.DAMAGE_RAND);
                break;
            case Const.BULLET_ICE:
                this.speed = Const.BulletIncSpeed;
                this.damage = Const.INC_DAMAGE + new Random().nextInt(Const.DAMAGE_RAND);
                break;
            default:
                break;
        }
    }

    public void move(){
        if(canMove()){
            switch (this.getDir()){
                case Const.UP:
                    this.setY(this.getY()-this.getSpeed());
                    break;
                case Const.DOWN:
                    this.setY(this.getY()+this.getSpeed());
                    break;
                case Const.RIGHT:
                    this.setX(this.getX()+this.getSpeed());
                    break;
                case Const.LEFT:
                    this.setX(this.getX()-this.getSpeed());
                    break;
                default:
                    break;
            }
        }
    }

    public boolean canMove(){
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDir()){
            case Const.UP:
                newY -= this.getSpeed();
                break;
            case Const.DOWN:
                newY += this.getSpeed();
                break;
            case Const.RIGHT:
                newX += this.getSpeed();
                break;
            case Const.LEFT:
                newX -= this.getSpeed();
                break;
            default:
                break;
        }
        // 如果超出边界
        if(CollDete.isBeyondBorder(newX, newY, Const.width)){
            this.setAlive(false);
            this.getFather().getExplosions().add(new Explosion(this.getX(), this.getY(), 1, this.getFather()));
            return false;
        }

        // 如果和障碍物碰撞
        for(Barrier barrier: this.getFather().getBarriers()){
            if(barrier.getType() > Const.canMove){
                if(CollDete.isCollide(newX, newY, Const.width, barrier.getX(), barrier.getY(), Const.width)){
                    if (barrier.getType()==Const.brick||barrier.getType()==Const.home){
                        barrier.setAlive(false);
                    }
                    this.getFather().getExplosions().add(new Explosion(this.getX(), this.getY(), 1, this.getFather()));
                    this.setAlive(false);
                    return false;
                }
            }
        }
        // 如果和子弹碰撞
        for(int i = 0; i < this.getFather().getBullets().size(); i++) {
            Bullet bullet = this.getFather().getBullets().get(i);
            if(bullet.getBelongTo() != this.getBelongTo() && this.getFather().getBullets().get(i)!=this){
                if(CollDete.isCollide(newX, newY, Const.width, bullet.getX(), bullet.getY(), Const.width)) {
                    bullet.setAlive(false);
                    this.setAlive(false);
                    return false;
                }
            }
        }
        // 如果和坦克碰撞
        for (Tank tank: this.getFather().getTanks()){
            if(tank.getTankType()!=this.getBelongTo()){
                if(CollDete.isCollide(newX, newY, Const.width, tank.getX(), tank.getY(), Const.TankWidth)){
                    tank.beAttacked(this.getDamage());
                    if(!tank.isAlive()) {
                        this.getFather().getExplosions().add(new Explosion(tank.getX(), tank.getY(), 0, this.getFather()));
                    }
                    this.setAlive(false);
                    this.getFather().getExplosions().add(new Explosion(this.getX(), this.getY(), 1, this.getFather()));
                    return false;
                }
            }
        }
        return true;
    }

    public void draw(Graphics g){
        String path = null;
        if(this.getBulletType()==Const.BULLET_NOR){
            path = "pictures/bullet/Bullet.png";
        }else {
            path = "pictures/bullet/IceBullet.png";
        }
        g.drawImage(Toolkit.getDefaultToolkit().getImage(path), this.getX(), this.getY(), Const.width, Const.width, this.getFather());
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getBulletType() {
        return bulletType;
    }

    public void setBulletType(int bulletType) {
        this.bulletType = bulletType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(int belongTo) {
        this.belongTo = belongTo;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public MyPanel getFather() {
        return father;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }

}
