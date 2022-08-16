package ui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.GameAPI;
import classes.Move;
import constants.Constants;

public class FigureUI {
    private JButton picbutton;
    public FigureUI(int teamindex,int x,int y) {
        try {
            String filename = "src/main/resources/figures/"+teamindex+".png";
            File file = new File(filename);
            BufferedImage pic = ImageIO.read(file);
            this.picbutton = new JButton(new ImageIcon(pic));
            this.picbutton.putClientProperty("active",false);
            this.picbutton.putClientProperty("finished", true);
            this.picbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Move move = (Move)((JButton)e.getSource()).getClientProperty("Move");
                    if (move != null) {
                        System.out.println("CLICKED");
                        if (GameAPI.makeMove(move)) {
                            JFrame endscreen = new JFrame();
                            endscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            endscreen.setVisible(true);
                            endscreen.setTitle("YOU WON");
                            endscreen.setResizable(true);
                            endscreen.setSize(1000,825);
                            JPanel endpanel = new JPanel();
                            endpanel.setLayout(null);
                            JLabel endText = new JLabel();
                            endText.setText("YOU WON THE GAME!!!");
                            endpanel.add(endText);
                            endpanel.setBackground(Constants.TEAMCOLORS[(move.getPlayer().getTeamindex() + 1 )% 4]);
                            endscreen.add(endpanel);
                        }
                    }
                    return;
                }
            });
            this.picbutton.setBounds(x,y,65,65);
            this.picbutton.setBorderPainted(false);
            this.picbutton.setOpaque(false);
            this.picbutton.setContentAreaFilled(false);
            this.picbutton.setFocusPainted(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JButton getComp() {
        return this.picbutton;
    }

    public void setBackground(Move move) {
        this.picbutton.setOpaque(true);

        this.picbutton.setBackground(Color.CYAN);
        this.picbutton.putClientProperty("Move",move);
    }

    public void UnSetBackground(){
        this.picbutton.setOpaque(false);
        this.picbutton.setContentAreaFilled(false);
        this.picbutton.setFocusPainted(false);
        this.picbutton.putClientProperty("Move",null);
        this.picbutton.repaint();
    }

    public void setCords(int x,int y) {
        this.picbutton.setBounds(x,y,65,65);
    }
}
