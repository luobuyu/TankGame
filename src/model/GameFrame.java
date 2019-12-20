package model;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private MyPanel gamePanel = null;

    public GameFrame(){
        setTitle("Tank game");
        setSize(Const.WIN_WIDTH, Const.WIN_HEIGHT);
        gamePanel = new MyPanel();
        this.getContentPane().add(gamePanel);
        this.addKeyListener(new KeyListener() {
            boolean up = false, down = false, left = false, right = false;
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
                }
                setCurDir();
            }

            public void setCurDir(){
                PlayerTank player = (PlayerTank) gamePanel.getTanks().get(0);
                if(!up && !down && !right && !left){
                    player.setMoving(false);
                    return;
                }else {
                    if(up) player.setDir(Const.UP);
                    if(down) player.setDir(Const.DOWN);
                    if(right) player.setDir(Const.RIGHT);
                    if(left) player.setDir(Const.LEFT);
                    player.setMoving(true);
                }

            }

        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        GameFrame win = new GameFrame();
        win.setVisible(true);
    }
}
