package view;

import model.Const;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    private String bg;
    public Welcome(String path) {
        this.bg = path;
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Toolkit.getDefaultToolkit().getImage(bg), 0, 0, Const.WIN_WIDTH+200, Const.WIN_HEIGHT, this);
    }
}
