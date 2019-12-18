import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private Tank playerTank;
    private JPanel panel;
    public GameFrame(){
        setTitle("Tank game");
        setSize(1000, 800);
        this.playerTank = new Tank(20);
        panel = new JPanel();
        this.getContentPane().add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }


    //    @Override
//    public void paint(Graphics g) {
//        //super.paint(g);
//        this.playerTank.draw(g);
//    }


    public static void main(String[] args) {
        GameFrame win = new GameFrame();
        win.setVisible(true);
    }
}
