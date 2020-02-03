package model;

import view.Welcome;

import javax.swing.*;

public class MainWin extends JFrame {
    private JFrame gameWin;
    private JPanel panel;

    public MainWin() {
        this.setBounds(100, 50, 1200, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public JFrame getGameWin() {
        return gameWin;
    }

    public void setGameWin(JFrame gameWin) {
        this.gameWin = gameWin;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
        this.setContentPane(panel);
    }

    public static void main(String[] args) {
        MainWin mw = new MainWin();
        mw.setPanel(new Welcome("pictures/bgimg/welcome.jpg"));
        mw.setVisible(true);
    }
}
