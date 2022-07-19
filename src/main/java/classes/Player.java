package classes;

import java.awt.Color;;

public class Player {
    private Figure[] figures;
    private int teamindex;
    private GameField startField;

    public void setStartField(GameField startField) {
        this.startField = startField;
    }

    public GameField getStartField() {
        return startField;
    }

    public int getTeamindex() {
        return teamindex;
    }

    private GameField[] homeFields = new GameField[4];

    public GameField[] getHomeFields() {
        return homeFields;
    }

    public GameField[] addHomeField(GameField gameField) {
        for (int i = 0; i< 4;i++) {
            if (this.homeFields[i] == null) {
                this.homeFields[i] = gameField;
                return this.homeFields;
            }
        }
        return homeFields;
    }

    private Color teamcolor;

    public Color getTeamcolor() {
        return teamcolor;
    }

    public Player(int teamindex,Color teamcolor) {
        this.teamcolor = teamcolor;
        this.figures = new Figure[4];
        this.teamindex = teamindex;
        for ( int i = 0; i< 4; i++) {
            this.figures[i] = new Figure(i,0,new Color(255,0,0));
        }
    }

    public Figure[] getFigures() {
        return this.figures;
    }
}
