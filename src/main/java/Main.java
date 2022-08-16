import java.util.ArrayList;

import javax.swing.JPanel;

import classes.GameAPI;
import classes.GameLoop;
import classes.Move;
import classes.NextMoves;
import classes.Player;
import constants.Constants;
import ui.FigureUI;
import ui.InitWindow;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		GameLoop gameLoop = new GameLoop();
		for (int i = 0; i < 15; i++){
			gameLoop.Main();
		}
		// InitWindow uiWindow = new InitWindow();
		// uiWindow.windowInit();


		

		// for ( int i =0; i<50; i++ ) {
		// 	ArrayList<Move> moves = gameAPI.rollDice(0);
		// 	if (moves.size()>0) {
		// 		gameAPI.makeMove(moves.get(0));
		// 		render(gameAPI,mainpanel);
		// 		mainpanel.repaint();
		// 		Thread.sleep(1000);
		// 	}
		// }

		// gameAPI.getGameBoard().getGameFields();
		// Player[] players = new Player[4];
		// for (int i = 0; i < 4;i++) {
		// 	players[i] = new Player(i,Constants.TEAMCOLORS[i]);
		// }
		// mainpanel.repaint();

	}

}
