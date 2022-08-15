import java.util.ArrayList;

import classes.GameAPI;
import classes.Move;
import classes.Player;
import constants.Constants;
import ui.InitWindow;

public class Main {

	public static void main(String[] args) {
		InitWindow uiWindow = new InitWindow();
		uiWindow.windowInit();


		GameAPI gameAPI = new GameAPI();
		for ( int i =0; i<10; i++ ) {
			ArrayList<Move> moves = gameAPI.rollDice(0);
			if (moves.size()>0) {
				gameAPI.makeMove(moves.get(0));
				System.out.println("made move from;to:  " + moves.get(0).getFieldFrom().getIndex() + "; " + moves.get(0).getFieldTo().getIndex());
			}
		}

		Player[] players = new Player[4];
		for (int i = 0; i < 4;i++) {
			players[i] = new Player(i,Constants.TEAMCOLORS[i]);
		}
	}

}
