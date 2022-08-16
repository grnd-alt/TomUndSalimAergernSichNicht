package classes;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.Constants;
import helpers.Dice;

public class GameAPI {

    private GameBoard gameBoard;
    private int trynum = 0;
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
    public static boolean makeMove(Move move) {
        for (int i = 0; i < 4;i++){
            move.getFieldFrom().getFigure().getOwner().getFigures()[i].getFigureUI().UnSetBackground();
        }
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


    private boolean NeedsSix(Player player) {
        for (int i = 0; i < 4;i++) {
            if (player.getFigures()[i].getPosition().getIndex()>=16 && player.getFigures()[i].getPosition().getIndex() <=55){
                return false;
            }
        }

        boolean seenfigureinendzone = false;
        for(int i = 0;i<4;i++){
            if (player.getEndzone()[i].getFigure() != null){
                if (seenfigureinendzone) {
                    return false;
                }
                seenfigureinendzone = true;
            }
        }
        return true;
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
    public NextMoves rollDice(int playerId,int toroll) {
        int rolled = toroll > -1?toroll:Dice.rollDice();
        Player player = players[playerId];
        NextMoves nextMoves = new NextMoves(players[(playerId+1)%4],rolled);

        
        for (int figureInd = 0; figureInd < 4; figureInd++) {
            Figure figure = player.getFigures()[figureInd];

            // PREVENT index out of bound
            if(
                figure.getPosition().getIndex()+rolled <72
            ) {
                // PREVENT Move hitting own Figure
                // if(
                //     gameBoard.getGameFields()[figure.getPosition().getIndex() + rolled].getFigure() != null &&
                //     gameBoard.getGameFields()[figure.getPosition().getIndex() + rolled].getFigure().getOwner() == player
                //     // (gameBoard.getGameFields()[figure.getPosition().getIndex()+rolled > 55 ? (figure.getPosition().getIndex()+rolled)%55 + 15:figure.getPosition().getIndex()+rolled].getFigure() == null &&
                //     // gameBoard.getGameFields()[figure.getPosition().getIndex()+rolled > 55 ? (figure.getPosition().getIndex()+rolled)%55 + 15:figure.getPosition().getIndex()+rolled].getFigure().getOwner() != player)
                // ) continue;

                // FORCE move when having people in the house
                if(
                    rolled == 6 &&
                    figure.getPosition().getIndex()< 16 &&
                    (player.getStartField().getFigure() == null ||
                    player.getStartField().getFigure().getOwner() != player)
                ) {
                    nextMoves = new NextMoves(new Move(player,figure.getPosition(),player.getStartField(),rolled));
                    break;
                }
                if (figure.getPosition().getIndex() >= 16) {
                    // CHECK if Figure is in endzone already
                    if (figure.getPosition().getType() == "end") {
                        if (figure.getPosition().getIndex()+rolled > player.getEndzone()[0].getIndex() && figure.getPosition().getIndex()+rolled <= player.getEndzone()[3].getIndex()){
                            nextMoves.addMove(new Move(player,figure.getPosition(),gameBoard.getGameFields()[figure.getPosition().getIndex()+rolled],rolled));
                            System.out.println("been here done that");
                        }
                        continue;
                    }


                    // CHECK if Figure has gone one round
                    if(figure.getPosition().getIndex() < player.getStartField().getIndex() && figure.getPosition().getIndex()+rolled >= player.getStartField().getIndex()
                    ){
                        if (rolled -(player.getStartField().getIndex()-figure.getPosition().getIndex()) < 4) {
                            nextMoves.addMove(new Move(player, figure.getPosition(), player.getEndzone()[rolled -(player.getStartField().getIndex()-figure.getPosition().getIndex())], rolled));
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
                    if (
                        rolled == 6 && (player.getStartField().getFigure() == null 
                        || player.getStartField().getFigure().getOwner() != player)
                    ) {
                        nextMoves = new NextMoves(new Move(player, figure.getPosition(), player.getStartField(), rolled));
                    }
                }
            }
        }

        for(int i = nextMoves.getMoves().size()-1; i >=0;i--){
            if(
                nextMoves.getMoves().get(i).getFieldTo().getFigure() != null &&
                nextMoves.getMoves().get(i).getFieldTo().getFigure().getOwner() == player
            ) nextMoves.getMoves().remove(i);
        }
        if(rolled != 6 && (trynum >=2 || !NeedsSix(player))) {
            this.onturn = players[(player.getTeamindex()+1) %this.players.length];
            trynum = 0;
        } else trynum++;
        if (player.getType() != "human" && nextMoves.getMoves().size() > 0){
            if(GameAPI.makeMove(nextMoves.getMoves().get(0))) {
                JFrame endscreen = new JFrame();
                endscreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                endscreen.setVisible(true);
                endscreen.setTitle("YOU WON");
                endscreen.setResizable(true);
                endscreen.setSize(1000,825);
                JPanel endpanel = new JPanel();
                endpanel.setLayout(null);
                JLabel endText = new JLabel();
                endText.setText("YOU WON THE GAME!!!");
                endpanel.setBackground(Constants.TEAMCOLORS[(nextMoves.getMoves().get(0).getPlayer().getTeamindex() + 1 )% 4]);
                endpanel.add(endText);
                endscreen.add(endpanel);
            };
            return new NextMoves(getOnturn(),rolled);
        }
        return nextMoves;
    }
}
