package classes;

import constants.Constants;
import helpers.Dice;

public class GameAPI {

    private GameBoard gameBoard;
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    private Player[] players;

    public Player[] getPlayer() {
        return players;
    }
    public GameAPI(){
        this.players = new Player[4];
        for (int i = 0; i < 4; i++) {
            this.players[i] = new Player(i, Constants.TEAMCOLORS[i]);
        }
        this.gameBoard = new GameBoard(players);
    }

    public int RollDice(int playerID){
        int rolled = Dice.rollDice();
        if (rolled == 6) {
            for (int i = 0;i<4;i++){
                if (players[playerID].getHomeFields()[i].getFigure() != null){
                    System.out.println("Feld frei machen");
                }
            }
        }
        return rolled;
    }

}
