package model;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank{
    private boolean moving;

    public PlayerTank(int tankType) {
        super(tankType);
        this.setX(400);
        this.setY(600);
        this.setDir(Const.UP);
        this.setMoving(false);
    }

    @Override
    public void draw(Graphics g, JPanel father) {
        String path = null;
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
        g.drawImage(Toolkit.getDefaultToolkit().getImage(path), this.getX(), this.getY(), Const.TankWidth, Const.TankWidth, father);
    }

    @Override
    public void move() {
        if(this.isMoving()){
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

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
