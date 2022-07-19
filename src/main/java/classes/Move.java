package classes;

public class Move {
    private Player player;
    public Player getPlayer() {
        return player;
    }
    private GameField fieldFrom;
    public GameField getFieldFrom() {
        return fieldFrom;
    }
    private GameField fieldTo;
    public GameField getFieldTo() {
        return fieldTo;
    }
    private int rolledNumber;
    public int getRolledNumber() {
        return rolledNumber;
    }
    public Move(Player player,GameField fieldFrom,GameField fieldTo,int rolledNumber) {
        this.player = player;
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
        this.rolledNumber = rolledNumber;
    }
    public Move(int rolledNumber){
        this.rolledNumber = rolledNumber;
    }
}
