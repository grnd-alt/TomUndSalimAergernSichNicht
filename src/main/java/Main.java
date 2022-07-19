import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import classes.GameAPI;
import classes.Move;
import classes.Player;
import constants.Constants;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("TESTFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel label = new JLabel("JFrame Example");
		JButton button = new JButton();
		button.setText("HALLO TOM");
		panel.add(label);
		panel.add(button);
		frame.add(panel);
		frame.setSize(200,300);
		frame.setVisible(true);


		GameAPI gameAPI = new GameAPI();
		for ( int i =0; i<10; i++ ) {
			Move[] moves = gameAPI.RollDice(0);
			if (moves[0].getRolledNumber()==6){
				gameAPI.MakeMove(moves[0]);
			}
		}

		Player[] players = new Player[4];
		for (int i = 0; i < 4;i++) {
			players[i] = new Player(i,Constants.TEAMCOLORS[i]);
		}
	}

}
