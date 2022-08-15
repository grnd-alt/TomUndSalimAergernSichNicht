package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.Graphics2D;

public class Field extends JFrame {

    private Color backgroundColor = new Color(253 , 241,141);

    public void initField(JPanel panel, Color color, int times,int x,int y){
        int d = 720;

        JPanel field = new JPanel();
        //field.setBackground(color);
        field.setPreferredSize(new Dimension(d/11*times,d/11));
        field.setBounds(x,y,d/11*times,d/11);
        field.setBorder(new RoundedBorder(d/11, color));
        panel.setBackground(backgroundColor);
        panel.add(field);
    }

    private static class RoundedBorder implements Border {

        private int radius;
        private Color color;

        RoundedBorder(int radius,Color color) {
            this.radius = radius;
            this.color = color;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(color);
            g2.fillRoundRect(x,y,width,height,radius,radius);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

}