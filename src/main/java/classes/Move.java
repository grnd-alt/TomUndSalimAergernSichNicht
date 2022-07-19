package classes;

public class Move {
    private int playerID;
    public int getPlayerID() {
        return playerID;
    }
    private int figureId;
    public int getFigureId() {
        return figureId;
    }
    private int fieldIndexFrom;
    public int getFieldIndexFrom() {
        return fieldIndexFrom;
    }
    private int fieldIndexTo;
    public int getFieldIndexTo() {
        return fieldIndexTo;
    }
    private int rolledNumber;
    public int getRolledNumber() {
        return rolledNumber;
    }
    public Move(int playerID, int figureId,int fieldIndexFrom,int fieldIndexTo,int rolledNumber) {
        this.playerID = playerID;
        this.figureId = figureId;
        this.fieldIndexFrom = fieldIndexFrom;
        this.fieldIndexTo = fieldIndexTo;
        this.rolledNumber = rolledNumber;
    }
}
