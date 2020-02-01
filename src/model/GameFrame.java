package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private MyPanel gamePanel = null;
    public GameFrame(){
        setTitle("Tank game");
        this.setSize(Const.WIN_WIDTH+15+200, Const.WIN_HEIGHT+38);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        gamePanel = new MyPanel();
        this.getContentPane().add(gamePanel);
        this.setVisible(true);
        this.addKeyListener(new KeyListener() {
            boolean up = false, down = false, left = false, right = false;
            boolean fire = false;
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()){
                    case KeyEvent.VK_UP:
                        up = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        fire = true;
                        break;
                }
                setCurDir();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()){
                    case KeyEvent.VK_UP:
                        up = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        down = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        right = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        left = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        fire = false;
                        break;
                }
                setCurDir();
            }

            public void setCurDir(){
                PlayerTank player = (PlayerTank) gamePanel.getTanks().get(0);
                if(!fire) {
                    player.setFiring(false);
                }else {
                    player.setFiring(true);
                }
                if(!up && !down && !right && !left){
                    player.setMoving(false);
                }else {
                    // 如果开火间隔达不到直接return
                    if(up) player.setDir(Const.UP);
                    if(down) player.setDir(Const.DOWN);
                    if(right) player.setDir(Const.RIGHT);
                    if(left) player.setDir(Const.LEFT);
                    player.setMoving(true);
                }
            }

        });

    }

    /**
     * get and set
     * @return 成员
     */
    public MyPanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(MyPanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public static void main(String[] args) {
        GameFrame win = new GameFrame();
    }
}
