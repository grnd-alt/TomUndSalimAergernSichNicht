package classes;

import java.util.ArrayList;
import java.util.Arrays;

import constants.Constants;
import helpers.Dice;

public class GameAPI {

    private GameBoard gameBoard;
    private Player onturn;
    public Player getOnturn() {
        return onturn;
    }
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
        this.onturn = this.players[0];
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
    public boolean makeMove(Move move) {
        if(move.getFieldFrom().getFigure() != null){
            if(move.getFieldTo().getFigure() == null) {
                move.getFieldFrom().getFigure().setPosition(move.getFieldTo());
                move.getFieldTo().setFigure(move.getFieldFrom().getFigure());
                move.getFieldFrom().setFigure(null);
            } else if (move.getFieldTo().getFigure().getOwner() != move.getFieldFrom().getFigure().getOwner()){
                Figure tohit = move.getFieldTo().getFigure();
                move.getFieldTo().setFigure(move.getFieldFrom().getFigure());
                tohit.hitMe();
                move.getFieldFrom().getFigure().setPosition(move.getFieldTo());
                move.getFieldFrom().setFigure(null);
            }
        }
        return move.getPlayer().hasWon();
    }

    private boolean inEndZone(Figure figure,int rolled) {
        int endfieldsbegin = (0+ figure.getOwner().getTeamindex() * 4) % 16 + 56;
        int endfieldslast = (3+ figure.getOwner().getTeamindex() * 4) % 16 + 56;
        return false;
    }

    private boolean movePossible(Figure figure,int rolled) {
        if (figure.getPosition().getIndex()+rolled < 72 && figure.getPosition().getIndex() >= 16){
            if ((3+ figure.getOwner().getTeamindex() * 4) % 16 + 56 >= figure.getPosition().getIndex()+rolled){
                if (!extendsStartField(figure, rolled) &&
                (gameBoard.getGameFields()[figure.getPosition().getIndex() + rolled].getFigure() == null || gameBoard.getGameFields()[figure.getPosition().getIndex() + rolled].getFigure().getOwner() != figure.getOwner()))
                {
                    return true;
                }
                if (extendsStartField(figure, rolled)){
                    if (calcMoveToEndField(figure, rolled) != null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Move calcMoveToEndField(Figure figure, int rolled) {
        if (figure.getOwner().getStartField().getIndex() == 16) {
            return new Move(figure.getOwner(), figure.getPosition(), gameBoard.getGameFields()[figure.getPosition().getIndex() + rolled], rolled);
        }
        int endfieldsbegin = (0+ figure.getOwner().getTeamindex() * 4) % 16 + 56;
        int endfieldslast = (3+ figure.getOwner().getTeamindex() * 4) % 16 + 56;
        int resultfield = endfieldsbegin - figure.getOwner().getStartField().getIndex() + figure.getPosition().getIndex() + rolled;
        if (resultfield < 72 && endfieldsbegin <= resultfield && resultfield <= endfieldslast) {
            return new Move(figure.getOwner(),figure.getPosition(),gameBoard.getGameFields()[resultfield],rolled);
        }
        return null;
    }

    private boolean extendsStartField(Figure figure, int rolled) {
        if (figure.getOwner().getStartField().getIndex() == 16) {
            if (figure.getPosition().getIndex() + rolled > 55){
                System.out.println("OUT1");
                return true;
            }
        }
        else if (
            figure.getOwner().getStartField().getIndex() - 1 < figure.getPosition().getIndex() + rolled &&
            figure.getOwner().getStartField().getIndex() > figure.getPosition().getIndex()
        ) {
            System.out.println("OUT2");
            return true;
        }
        return false;
    }

    public NextMoves rollDice(int playerId) {
        int rolled = Dice.rollDice();
        Player player = players[playerId];
        NextMoves nextMoves = new NextMoves(players[(playerId+1)%4]);


        for (int figureInd = 0; figureInd < 4; figureInd++) {
            Figure figure = player.getFigures()[figureInd];
            if (figure.getPosition().getIndex() >= 16) {
                // CHECK if Figure has gone one round
                if(figure.getPosition().getIndex() < player.getStartField().getIndex() && figure.getPosition().getIndex()+rolled >= player.getStartField().getIndex()
                    || figure.getPosition().getType() == "end"
                ){
                    if (rolled -(player.getStartField().getIndex()-figure.getPosition().getIndex()) < 4) {
                        System.out.println("one way around");
                        nextMoves.addMove(new Move(player, figure.getPosition(), player.getEndzone()[rolled -(player.getStartField().getIndex()-figure.getPosition().getIndex())], rolled));
                        System.out.println(player.getEndzone()[rolled -(player.getStartField().getIndex()-figure.getPosition().getIndex())]);
                    }
                    continue;
                }
                if(figure.getPosition().getIndex() > player.getStartField().getIndex() && (figure.getPosition().getIndex() +rolled) % 50 < player.getStartField().getIndex()&& player.getStartField().getIndex() == 16) {
                    if (figure.getPosition().getIndex() + rolled <= 59 ) {
                        nextMoves.addMove(new Move(player, figure.getPosition(), gameBoard.getGameFields()[figure.getPosition().getIndex()+rolled], rolled));
                    }
                    continue;
                }

                // DEFAULT Figures going around in circles
                nextMoves.addMove(new Move(
                    player,
                    gameBoard.getGameFields()[figure.getPosition().getIndex()],
                    gameBoard.getGameFields()[figure.getPosition().getIndex()+rolled > 55 ? (figure.getPosition().getIndex()+rolled)%55 + 15:figure.getPosition().getIndex()+rolled], 
                    rolled
                ));
            } else {
                if (rolled == 6) {
                    nextMoves.addMove(new Move(player, figure.getPosition(), player.getStartField(), rolled));
                }
            }
        }

        this.onturn = players[(player.getTeamindex()+1) %this.players.length];
        return nextMoves;
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
    public NextMoves rollDice2(int playerId){
        int rolled = Dice.rollDice();
        Player player = players[playerId];
        NextMoves nextMoves = new NextMoves(players[(playerId+1)%4]);
        if (rolled == 6) {
            for (int i = 0;i<4;i++){
                if (player.getHomeFields()[i].getFigure() != null){
                    if (player.getStartField().getFigure() == null ||
                        player.getStartField().getFigure().getOwner() != player
                    ) {
                        return new NextMoves(new Move(player,player.getHomeFields()[i],player.getStartField(),rolled));
                    }
                    // else if(this.gameBoard.getGameFields()[player.getStartField().getIndex()+rolled].getFigure() == null ||
                    // this.gameBoard.getGameFields()[player.getStartField().getIndex()+rolled].getFigure().getOwner() != player){
                    //     GameField endfield = this.gameBoard.getGameFields()[player.getStartField().getIndex() + rolled];
                    //     return new NextMoves(new Move(player,player.getStartField(),endfield,rolled));
                    // }
                }
            }
        }
        for (int i = 0; i<4; i++){
            if (player.getFigures()[i].getPosition().getIndex() + rolled > 55) {
                System.out.println("HHHH");
            }
            if (movePossible(player.getFigures()[i], rolled)){
                if (extendsStartField(player.getFigures()[i], rolled)) {
                    for (int j = 0; j < 4;j++) {
                        if (calcMoveToEndField(player.getFigures()[i], rolled) != null && player.getEndzone()[j].getIndex() == calcMoveToEndField(player.getFigures()[i],rolled).getFieldFrom().getIndex()){
                            System.out.println("HALLO");
                        }
                    }
                    nextMoves.addMove(calcMoveToEndField(player.getFigures()[i], rolled));
                }
                else{
                    nextMoves.addMove(new Move(player,player.getFigures()[i].getPosition(),this.gameBoard.getGameFields()[(player.getFigures()[i].getPosition().getIndex()+rolled) > 55 ? (player.getFigures()[i].getPosition().getIndex()+rolled)%40:(player.getFigures()[i].getPosition().getIndex()+rolled)],rolled));
                }
            }
            // else if (   player.getFigures()[i].getPosition().getIndex() >=16 &&
            //             player.getFigures()[i].getPosition().getIndex() <= 55){
        }
        this.onturn = players[(Arrays.asList(players).indexOf(this.onturn)+1)%this.players.length];
        return nextMoves;
    }

}
