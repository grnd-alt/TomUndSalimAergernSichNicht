package classes;

import java.util.ArrayList;
import java.util.Arrays;

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
            this.players[i] = new Player(i,Constants.TEAMCOLORS[i]);
        }
        this.gameBoard = new GameBoard(this.players);
        for (int i = 0; i < 4; i++) {
            this.players[i].setFigures(gameBoard);
        }
    }

    /**
     * 
     * executes the move the player decided to make
     * call after player decided or if RollDice only returns one Move
     * 
     * 
     * @param move The move the Player decided to make
     * @return 
     */
    public void makeMove(Move move) {
        move.getFieldFrom().getFigure().setPosition(move.getFieldTo());
        move.getFieldTo().setFigure(move.getFieldFrom().getFigure());
        move.getFieldFrom().setFigure(null);
    }

    /**
     * RoolDice generates a random number, and calculates all possible moves
     * the player rolling the dice could make
     * 
     * Frontend has to let the user choose one move and then call MakeMove with that move
     * 
     * @param playerId the player's id who's turn it is
     * @return ArrayList of Moves the player can decide from
     */
    public ArrayList<Move> rollDice(int playerId){
        int rolled = Dice.rollDice();
        Player player = players[playerId];
        System.out.println(rolled);
        if (rolled == 6) {
            for (int i = 0;i<4;i++){
                if (player.getHomeFields()[i].getFigure() != null){
                    if (player.getStartField().getFigure() == null) {
                        return new ArrayList<Move>(Arrays.asList(new Move(player,player.getHomeFields()[i],player.getStartField(),rolled)));
                    }
                    else {
                        GameField endfield = this.gameBoard.getGameFields()[player.getStartField().getIndex() + rolled];
                        return new ArrayList<Move>((Arrays.asList(new Move(player,player.getStartField(),endfield,rolled))));
                    }
                }
            }
        }
        ArrayList<Move> possiblemoves = new ArrayList<Move>();
        for (int i = 0; i<4; i++){
            if (player.getFigures()[i].getPosition().getIndex()>16){
                if (this.gameBoard.getGameFields()[player.getFigures()[i].getPosition().getIndex() + rolled].getFigure() == null) {
                    possiblemoves.add(new Move(player,player.getFigures()[i].getPosition(),this.gameBoard.getGameFields()[player.getFigures()[i].getPosition().getIndex()+rolled],rolled));
                }
            }
        }
        return possiblemoves;
    }

}
