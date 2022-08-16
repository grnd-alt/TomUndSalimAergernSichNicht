package ui;

import javax.swing.*;

import classes.GameAPI;
import classes.GameField;
import classes.GameLoop;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import ui.Field;
import constants.Constants;
import java.awt.geom.Ellipse2D;
import java.util.*;

public class InitWindow{

    private Color playerGreen = new Color(0,255,0);
    private Color playerRed = new Color(255,0,0);
    private Color playerYellow = new Color(255,255,0);
    private Color playerBlack = new Color(0,0,0);
    private DiceUI diceUI;
    private YourTurnAlert yourTurnAlert;


    public YourTurnAlert getYourTurnAlert() {
        return yourTurnAlert;
    }



    public DiceUI getDiceUI() {
        return diceUI;
    }



    public JPanel windowInit(GameField[] gamefields,GameAPI gameAPI,boolean test){
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
        final JPanel curton = new JPanel();
        curton.setBounds(0,0,1000,1000);
        JButton startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public void actionPerformed(ActionEvent e) {
                curton.setVisible(false);
                return;
            }
        });
        JLabel welcomeText = new JLabel();
        welcomeText.setText("WELCOME TO THE GAME");
        JLabel infoText = new JLabel();
        infoText.setText("choose against how many robots you want to play");
        welcomeText.setBounds(300,250,300,100);
        infoText.setBounds(300,270,500,100);
        startButton.setBounds(325,650,100,100);
        final GameAPI gameAPI2 = gameAPI;

        JButton[] optButtons = new JButton[5];
        for (int i = 0; i <= 4;i++){
                optButtons[i] = new JButton((i)+"");
                optButtons[i].setBounds(300+(i*100+i*10),350,100,100);
                curton.add(optButtons[i]);
                optButtons[i].addActionListener(new ActionListener() {
                    /* (non-Javadoc)
                     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
                     */
                    public void actionPerformed(ActionEvent e) {
                        // gameAPI2.getPlayer()[
                        final int number = Integer.parseInt(((JButton)e.getSource()).getText());
                        for (int i = 0; i < number;i++){
                            gameAPI2.getPlayer()[i].setType("bot");
                        }
                        return;
                    }
                });
        }
        curton.setLayout(null);
        curton.add(welcomeText);
        curton.add(infoText);
        curton.add(startButton);
        panel.add(curton);
        panel.setComponentZOrder(curton, 0);
        if (test) {
            for(int i = 1;i<=6;i++){
                new RollButton(panel,gameAPI,this,i,720,100*(i+1));
            }
        }
        
        yourTurnAlert = new YourTurnAlert(720, 0, panel);

        diceUI = new DiceUI(335, 335,panel);
        diceUI.setVisible(4);

        new RollButton(panel,gameAPI,this,-1,720,100);

        byte[][] field = new byte[][]{
                {4,5,-1,-1,34,35,36,-1,-1,8,9,-2},
                {6,7,-1,-1,33,64,37,-1,-1,10,11,-2},
                {-1,-1,-1,-1,32,65,38,-1,-1,-1,-1,-2},
                {-1,-1,-1,-1,31,66,39,-1,-1,-1,-1,-2},
                {26,27,28,29,30,67,40,41,42,43,44,-2},
                {25,60,61,62,63,-1,71,70,69,68,45,-2},
                {24,23,22,21,20,59,50,49,48,47,46,-2},
                {-1,-1,-1,-1,19,58,51,-1,-1,-1,-1,-2},
                {-1,-1,-1,-1,18,57,52,-1,-1,-1,-1,-2},
                {0,1,-1,-1,17,56,53,-1,-1,12,13,-2},
                {2,3,-1,-1,16,55,54,-1,-1,14,15,-2},

        };
        // FigureUI figure = new FigureUI("RED");
        // panel.add(figure.getComp());
        for(int i = 0;i < field.length; i++){
            int y = i*(720/11)+10;
            for(int j = 0; j < field[i].length; j++){
                int x = j*(720/11)+10;
                if(field[i][j] >= 4 && field[i][j] <= 7 || field[i][j] == 26 || field[i][j] >= 60 && field[i][j] <= 63){
                    gamefields[field[i][j]].setX(x);
                    gamefields[field[i][j]].setY(y);
                    new Field().initField(panel, playerGreen, 1,x,y);
                }
                else if(field[i][j] >= 8 && field[i][j] <= 11 || field[i][j] == 36 || field[i][j] >= 64 && field[i][j] <= 67){
                    gamefields[field[i][j]].setX(x);
                    gamefields[field[i][j]].setY(y);
                    new Field().initField(panel, playerYellow, 1,x,y);
                }
                else if(field[i][j] >= 0 && field[i][j] <= 3 || field[i][j] == 16 || field[i][j] >= 56 && field[i][j] <= 59){
                    gamefields[field[i][j]].setX(x);
                    gamefields[field[i][j]].setY(y);
                    new Field().initField(panel, playerBlack, 1,x,y);
                }
                else if(field[i][j] >= 12 && field[i][j] <= 15  || field[i][j] == 46 || field[i][j] >= 68 && field[i][j] <= 71){
                    gamefields[field[i][j]].setX(x);
                    gamefields[field[i][j]].setY(y);
                    new Field().initField(panel, playerRed, 1,x,y);
                }
                else if (field[i][j] >= 17 && field[i][j] <= 25 || field[i][j] >= 27 && field[i][j] <= 35 || field[i][j] >= 37 && field[i][j] <= 45 || field[i][j] >= 47 && field[i][j] <= 55){
                    gamefields[field[i][j]].setX(x);
                    gamefields[field[i][j]].setY(y);
                    new Field().initField(panel, Color.WHITE, 1,x,y);
                } else if (field[i][j] == -2) {
                    new Field().initField(panel, Constants.BACKGROUND, 3,x,y);
                } else {
                    new Field().initField(panel, Constants.BACKGROUND, 1,x,y);
                }
            }
        }
        frame.setSize(1001,825);
        return panel;
    }
}
