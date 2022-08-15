package ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FigureUI {
    private JLabel piclab;
    public FigureUI(String colorString,JPanel panel) {
        try {
            
            String filename = "src/"+colorString+".png";
            // filename = "/home/belakkaf/eclipse-workspace/1/src/"+colorString+".png";
            System.out.println(filename);
            File file = new File(filename);
            System.out.println(file.getAbsolutePath());
            BufferedImage pic = ImageIO.read(file);
            this.piclab = new JLabel(new ImageIcon(pic));
            this.piclab.setBounds(10,10,65,65);
            panel.add(this.piclab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JLabel getComp() {
        return this.piclab;
    }

    public void setCords(int x,int y) {
        this.piclab.setBounds(x,y,65,65);
    }
}
