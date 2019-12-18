package model;

import javax.swing.*;
import java.awt.*;

public class PlayerTank extends Tank{

    public PlayerTank(int tankType) {
        super(tankType);
        this.setX(400);
        this.setY(600);
        this.setDir(Const.UP);
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
}
