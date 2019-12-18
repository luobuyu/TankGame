package model;

import javax.swing.*;

public class GameFrame extends JFrame {
    private JPanel panel = null;
    public GameFrame(){
        setTitle("Tank game");
        setSize(1000, 800);
        panel = new MyPanel();
        this.getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        GameFrame win = new GameFrame();
        win.setVisible(true);
    }
}
