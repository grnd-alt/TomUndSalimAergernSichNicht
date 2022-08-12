package ui;

import javax.swing.*;
import java.awt.*;
import ui.Field;
import java.awt.geom.Ellipse2D;
import java.util.*;


public class InitWindow extends JPanel{

    private Color backgroundColor = new Color(253 , 241,141);
    private Color playerGreen = new Color(0,255,0);
    private Color playerRed = new Color(255,0,0);
    private Color playerYellow = new Color(255,255,0);
    private Color playerBlack = new Color(0,0,0);



    public void windowInit(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createMatteBorder(10,10,10,10, playerRed));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Mensch aerger dich nicht");
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setSize(1000,825);
        panel.setLayout(null);


        byte[][] field = new byte[][]{
                {4,5,-1,-1,34,35,36,-1,-1,8,9,-2},
                {6,7,-1,-1,33,64,37,-1,-1,10,11,-2},
                {-1,-1,-1,-1,32,65,38,-1,-1,-1,-1,-2},
                {-1,-1,-1,-1,31,66,39,-1,-1,-1,-1,-2},
                {26,27,28,29,30,67,40,41,42,43,44,-2},
                {25,60,61,62,63,-1,68,69,70,71,45,-2},
                {24,23,22,21,20,59,50,49,48,47,46,-2},
                {-1,-1,-1,-1,19,58,51,-1,-1,-1,-1,-2},
                {-1,-1,-1,-1,18,57,52,-1,-1,-1,-1,-2},
                {0,1,-1,-1,17,56,53,-1,-1,12,13,-2},
                {2,3,-1,-1,16,55,54,-1,-1,14,15,-2},

        };

        for(int i = 0;i < field.length; i++){
            int y = i*(720/11)+10;
            for(int j = 0; j < field[i].length; j++){
                int x = j*(720/11)+10;
                if(field[i][j] >= 4 && field[i][j] <= 7 || field[i][j] == 26 || field[i][j] >= 60 && field[i][j] <= 63){
                    new Field().initField(panel, playerGreen, 1,x,y);
                }
                else if(field[i][j] >= 8 && field[i][j] <= 11 || field[i][j] == 36 || field[i][j] >= 64 && field[i][j] <= 67){
                    new Field().initField(panel, playerYellow, 1,x,y);
                }
                else if(field[i][j] >= 0 && field[i][j] <= 3 || field[i][j] == 16 || field[i][j] >= 56 && field[i][j] <= 59){
                    new Field().initField(panel, playerBlack, 1,x,y);
                }
                else if(field[i][j] >= 12 && field[i][j] <= 15  || field[i][j] == 46 || field[i][j] >= 68 && field[i][j] <= 71){
                    new Field().initField(panel, playerRed, 1,x,y);
                }
                else if (field[i][j] >= 17 && field[i][j] <= 25 || field[i][j] >= 27 && field[i][j] <= 35 || field[i][j] >= 37 && field[i][j] <= 45 || field[i][j] >= 47 && field[i][j] <= 55){
                    new Field().initField(panel, Color.WHITE, 1,x,y);
                } else if (field[i][j] == -2) {
                    new Field().initField(panel, Color.CYAN, 3,x,y);
                } else {
                    new Field().initField(panel, backgroundColor, 1,x,y);
                }
            }
        }
    }
}
