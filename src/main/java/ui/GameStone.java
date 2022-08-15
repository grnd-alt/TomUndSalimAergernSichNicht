package ui;

import javax.swing.*;
import java.awt.*;

public class GameStone {
    public void initGameStones(JPanel panel, Color color){
        int d = 720;
        JPanel field = new JPanel();
        field.setBackground(color);
        field.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.BLACK));
        field.setPreferredSize(new Dimension(d/11-10,d/11-10));
        panel.add(field);
    }
}
