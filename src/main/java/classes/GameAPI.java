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

    /**
     * 
     * @param move The move the Player decided to make
     * @return 
     */
    public void MakeMove(Move move) {
        move.getFieldFrom().getFigure().setPosition(move.getFieldTo().getIndex());
        move.getFieldTo().setFigure(move.getFieldFrom().getFigure());
        move.getFieldFrom().setFigure(null);
    }

    /**
     * RoolDice generates a random number, and calculates all possible moves
     * the player rolling the dice could make
     * @param playerId the player's id who's turn it is
     * @return list of Moves the player can decide from
     */
    public Move[] RollDice(int playerId){
        int rolled = Dice.rollDice();
        Player player = players[playerId];
        if (rolled == 6) {
            for (int i = 0;i<4;i++){
                if (player.getHomeFields()[i].getFigure() != null){
                    if (player.getStartField().getFigure() == null) {
                        return new Move[]{new Move(player,player.getHomeFields()[i],player.getStartField(),rolled)};
                    }
                    else {
                        GameField endfield = this.gameBoard.getGameFields()[player.getStartField().getIndex() + rolled];
                        return new Move[]{new Move(player,player.getStartField(),endfield,rolled)};
                    }
                }
            }
        }
        return new Move[]{new Move(0)};
    }

}
