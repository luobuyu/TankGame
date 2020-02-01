package model;

import java.awt.*;

public class PlayerTank extends Tank{
    private boolean isMoving;
    private boolean isFiring;
    public PlayerTank(int tankType, MyPanel father, int x, int y) {
        super(tankType, father, x, y);
        this.setDir(Const.UP);
        this.setSpeed(Const.TANK_NOR_SPEED);
        this.setMoving(false);
        this.setFiring(false);
        this.setBulletType(Const.BULLET_NOR);
        this.setImg(new String[]{Const.PlayerTank_IMG_UP, Const.PlayerTank_IMG_DOWN, Const.PlayerTank_IMG_RIGHT, Const.PlayerTank_IMG_LEFT});
    }

    @Override
    public boolean canFire() {
        long t = System.currentTimeMillis();
        if(t - this.getLastFireTime() > Const.PlayerFireGap && this.isFiring()) {
            this.setLastFireTime(t);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean canMove(){
        if(!this.isMoving()){
            return false;
        }
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDir()){
            case Const.UP:
                newY = this.getY()-this.getSpeed();
                break;
            case Const.DOWN:
                newY = this.getY()+this.getSpeed();
                break;
            case Const.LEFT:
                newX = this.getX()-this.getSpeed();
                break;
            case Const.RIGHT:
                newX = this.getX()+this.getSpeed();
                break;
            default:
                break;
        }

        // 如果超出边界
        if(CollDete.isBeyondBorder(newX, newY, Const.TankWidth)){
            return false;
        }

        // 如果和障碍物碰撞
        for(Barrier barrier: this.getFather().getBarriers()){
            if(barrier.getType()>Const.canMove){
                if(CollDete.isCollide(newX, newY, Const.TankWidth, barrier.getX(), barrier.getY(), Const.width)){
                    return false;
                }
            }
        }

        // 如果和坦克碰撞
        for(int i=1; i<this.getFather().getTanks().size(); i++){
            if(CollDete.isCollide(newX, newY, Const.TankWidth, this.getFather().getTanks().get(i).getX(), this.getFather().getTanks().get(i).getY(), Const.TankWidth)){
                return false;
            }
        }
        return true;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isFiring() {
        return isFiring;
    }

    public void setFiring(boolean firing) {
        isFiring = firing;
    }
}
