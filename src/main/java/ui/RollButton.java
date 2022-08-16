package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import classes.GameLoop;

import javax.swing.JButton;
import javax.swing.JPanel;

import classes.GameAPI;

public class RollButton {
    public RollButton(JPanel parent,GameAPI gameAPI,InitWindow uiWindow,int toroll,int x,int y) {
        JButton button = new JButton();
        button.setText(toroll == -1?"WÜRFELN":toroll+"");
        button.putClientProperty("parent", parent);
        button.putClientProperty("uiWindow", uiWindow);
        button.putClientProperty("gameAPI", gameAPI);
        button.putClientProperty("toroll", toroll);
        button.setBounds(x,y,200,100);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    GameLoop.RollDiceUI((GameAPI)((JButton) e.getSource()).getClientProperty("gameAPI"),(InitWindow)((JButton) e.getSource()).getClientProperty("uiWindow"),(JPanel)((JButton) e.getSource()).getClientProperty("parent"),(Integer)((JButton) e.getSource()).getClientProperty("toroll"));
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        });

        parent.add(button);
    }
}
