package model;

import view.Welcome;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameFrame extends JFrame {
    private JPanel panel;
    private int curLevel;
    public GameFrame(){
        setTitle("Tank game");
        this.setSize(Const.WIN_WIDTH+15+200, Const.WIN_HEIGHT+38);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        curLevel = 1;
        setResizable(false);
    }

    public void welcome() {
        String bg = "pictures/bgimg/welcome.jpg";
        JPanel wel = new Welcome(bg);
        JButton beginGame = new JButton("开始游戏");
        beginGame.setBounds(100, 500, 100, 30);
        beginGame.setContentAreaFilled(false);
        beginGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                playGame();
            }
        });
        wel.add(beginGame);
        wel.add(this.getExitGameButton());
        this.setPanel(wel);
    }

    public void playGame() {
        this.setPanel(new MyPanel(this, this.curLevel));
        this.getContentPane().requestFocus();
    }

    public void gameOver() {
        String bg = "pictures/bgimg/gameover.jpg";
        JPanel over = new Welcome(bg);
        over.add(this.getBackMainButton());
        over.add(this.getExitGameButton());
        this.setPanel(over);
    }

    public void win() {
        String bg = "pictures/bgimg/win.jpg";
        JPanel win = new Welcome(bg);
        win.add(this.getBackMainButton());
        win.add(this.getExitGameButton());

        this.setPanel(win);

    }

    public void passGame() {
        String bg = "pictures/bgimg/pass.jpg";
        JPanel pass = new Welcome(bg);
        JButton nextLevel = new JButton("下一关");
        nextLevel.setBounds(100, 452, 100, 30);
        nextLevel.setContentAreaFilled(false);
        nextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setCurLevel(getCurLevel() + 1);
                playGame();
            }
        });

        pass.add(nextLevel);
        pass.add(this.getExitGameButton());
        pass.add(this.getBackMainButton());
        this.setPanel(pass);
    }


    public JButton getExitGameButton() {
        JButton exitGame = new JButton("退出游戏");
        exitGame.setBounds(100, 550, 100, 30);
        exitGame.setContentAreaFilled(false);
        exitGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        return exitGame;
    }

    public JButton getBackMainButton() {
        JButton backMain = new JButton("返回主菜单");
        backMain.setBounds(100, 500, 100, 30);
        backMain.setContentAreaFilled(false);
        backMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                welcome();
            }
        });
        return backMain;
    }


    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
        panel.setLayout(null);
        this.setContentPane(panel);
        this.setVisible(true);
    }



    /**
     * get and set
     * @return 成员
     */

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }


    public static void main(String[] args) {
        GameFrame win = new GameFrame();
        win.welcome();
    }
}
