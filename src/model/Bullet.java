package model;

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

    public Bullet(int x, int y, int damage, int dir, int bulletType, int speed, int belongTo, MyPanel father) {
        this.x = x;
        this.y = y;
        this.damage = damage;
        this.dir = dir;
        this.bulletType = bulletType;
        this.speed = speed;
        this.belongTo = belongTo;
        this.alive = false;
        this.father = father;
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
        if(CollDete.isBeyondBorder(newX, newY, Const.TankWidth)){
            this.setAlive(true);
            return false;
        }

        // 如果和障碍物碰撞
        for(Barrier barrier: this.getFather().getBarriers()){
            if(barrier.getType()>Const.canMove){
                if(CollDete.isCollide(newX, newY, Const.TankWidth, barrier.getX(), barrier.getY(), Const.width)){
                    this.setAlive(true);
                    return false;
                }
            }
        }
        return false;
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