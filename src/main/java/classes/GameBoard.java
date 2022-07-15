package classes;

import constants.Constants;

public class GameBoard {

    private GameField[] gameFields;

    public GameField[] getGameFields() {
        return gameFields;
    }

    public GameBoard(Player[] players) {
        this.gameFields = new GameField[72];
        for ( int i = 0; i < 72;i++) {
            if (i < 16){
                this.gameFields[i] = new GameField("start",Constants.TEAMCOLORS[(int)i/4]);
                this.gameFields[i].setFigure(players[(int)i/4].getFigures()[(int)i/4]);
                players[(int)i/4].addHomeField(gameFields[i]);
            }
            else if (i > 71-16) {
                this.gameFields[i] = new GameField("end",Constants.TEAMCOLORS[(int)(71-i)/4]);
                this.gameFields[i].setFigure(players[(int)(71-i)/4].getFigures()[(int)(71-i)/4]);
            }
            else {
                this.gameFields[i] = new GameField("normal", Constants.WHITE);
            }
        }
        System.out.println("HHH");
    }
}
