package ui;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DiceUI {
    private JLabel[] piclab = new JLabel[6];

    public DiceUI(int x ,int y,JPanel parent) {
        for (int i = 0; i < 6; i++){
            String filename = "src/main/resources/diceSides/"+(i+1)+".png";
            System.out.println(filename);
            File file = new File(filename);
            try {
                BufferedImage bimage = ImageIO.read(file);
                JLabel label = new JLabel(new ImageIcon(bimage));
                label.setBounds(x,y,65,65);
                label.setVisible(false);
                this.piclab[i] = label;
                parent.add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JLabel getComp(int number) {
        return this.piclab[number-1];
    }
    public void setCords(int number,int x,int y) {
        this.piclab[number-1].setBounds(x,y,65,65);
    }
    public void setVisible(int number){
        for (int i = 0; i < 6;i++) {
            this.piclab[i].setVisible(false);
        }
        this.piclab[number-1].setVisible(true);
    }
}
