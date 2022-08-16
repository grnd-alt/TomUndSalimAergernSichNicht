package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import constants.Constants;

public class FigureUI {
    private JLabel piclab;
    public FigureUI(int teamindex,int x,int y) {
        try {
            
            String filename = "src/main/resources/figures/"+teamindex+".png";
            // filename = "/home/belakkaf/eclipse-workspace/1/src/"+teamindexstring+".png";
            File file = new File(filename);
            BufferedImage pic = ImageIO.read(file);
            this.piclab = new JLabel(new ImageIcon(pic));
            this.piclab.setBounds(x,y,65,65);
            // panel.add(this.piclab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JLabel getComp() {
        return this.piclab;
    }

    public void setBackground() {
        this.piclab.setBackground(Constants.BACKGROUND);
    }

    public void setCords(int x,int y) {
        this.piclab.setBounds(x,y,65,65);
    }
}
