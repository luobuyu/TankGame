package model;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    private Tank playerTank;

    public MyPanel(){
        playerTank = new Tank(15);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.playerTank.draw(g);
    }
}
