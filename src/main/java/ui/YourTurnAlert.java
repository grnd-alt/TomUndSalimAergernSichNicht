package ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import constants.Constants;

public class YourTurnAlert {
    private JPanel alertpanel;
    private JPanel contentpanel;
    private JTextArea textArea;
    public YourTurnAlert(int x,int y,JPanel parent) {
        alertpanel = new JPanel();
        alertpanel.setBounds(x,y,200,100);
        alertpanel.setBackground(Constants.WHITE);
        contentpanel = new JPanel();
        contentpanel.setBackground(Constants.TEAMCOLORS[0]);
        alertpanel.setLayout(null);
        contentpanel.setBounds(0,0,100,100);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("ist Am Zug");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);

        JPanel textPanel = new JPanel();
        textPanel.setBounds(100,40,100,100);
        textPanel.setBackground(Constants.WHITE);
        textPanel.add(textArea);
        // contentpanel.add(textArea);
        alertpanel.add(textPanel);
        alertpanel.add(contentpanel);
        parent.add(alertpanel);
        alertpanel.setVisible(true);
    }

    public void alertTurn(int teamindex){
        alertpanel.setVisible(true);
        contentpanel.setBackground(Constants.TEAMCOLORS[teamindex]);
        alertpanel.repaint();
    }
}
