package model;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank{
    private boolean moving;
    private MyPanel father;

    public PlayerTank(int tankType, MyPanel father) {
        super(tankType);
        this.setX(17*Const.width);
        this.setY(37*Const.width);
        this.setDir(Const.UP);
        this.setMoving(false);
        this.setFather(father);
//        this.pictImg = new String[][]{{Const.PlayerTank_IMG_UP1, Const.PlayerTank_IMG_UP2},
//                {Const.PlayerTank_IMG_RIGHT1, Const.PlayerTank_IMG_RIGHT2},
//                {Const.PlayerTank_IMG_DOWN1, Const.PlayerTank_IMG_DOWN2},
//                {Const.PlayerTank_IMG_LEFT1, Const.PlayerTank_IMG_LEFT2}
//        };
    }

    @Override
    public void draw(Graphics g, JPanel father) {
        String path = null;
//        int index = 0;
//        long currTime = System.currentTimeMillis();
        switch (this.getDir()){
            case Const.UP:
                path = Const.PlayerTank_IMG_UP;
                break;
            case Const.DOWN:
                path = Const.PlayerTank_IMG_DOWN;
                break;
            case Const.RIGHT:
                path = Const.PlayerTank_IMG_RIGHT;
                break;
            case Const.LEFT:
                path = Const.PlayerTank_IMG_LEFT;
                break;
            default:
                break;
        }
        g.drawImage(Toolkit.getDefaultToolkit().getImage(path), this.getX(), this.getY(), Const.TankWidth, Const.TankWidth, this.getFather());
    }

    @Override
    public void move() {
        if(this.isMoving()&&canMove()){
            switch (this.getDir()){
                case Const.UP:
                    this.setY(this.getY()-this.getSpeed());
                    break;
                case Const.DOWN:
                    this.setY(this.getY()+this.getSpeed());
                    break;
                case Const.LEFT:
                    this.setX(this.getX()-this.getSpeed());
                    break;
                case Const.RIGHT:
                    this.setX(this.getX()+this.getSpeed());
                    break;
            }
        }
    }

    public boolean canMove(){
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
//        if(newX<0||newY<0||newX>Const.WIN_WIDTH - Const.TankWidth||newY>Const.WIN_HEIGHT-Const.TankWidth){
//            return false;
//        }
        return true;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public MyPanel getFather() {
        return father;
    }

    public void setFather(MyPanel father) {
        this.father = father;
    }
}
