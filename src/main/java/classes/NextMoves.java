package classes;

import java.util.ArrayList;

public class NextMoves {
    public Player nextPlayer;
    private int rolled;
    public int getRolled() {
        return rolled;
    }
    public Player getNextPlayer() {
        return nextPlayer;
    }
    private ArrayList<Move> moves = new ArrayList<Move>();
    public ArrayList<Move> getMoves() {
        return moves;
    }
    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }

    public void addMove(Move move) {
        this.moves.add(move);
        this.nextPlayer = move.getPlayer();
    }

    public NextMoves(Move move) {
        this.moves.add(move);
        this.nextPlayer = move.getPlayer();
        System.out.println(move.getRolledNumber() + "HHHHH");
        this.rolled = move.getRolledNumber();
    }
    public NextMoves(Player nextPlayer,int rolled) {
        this.nextPlayer = nextPlayer;
        this.rolled = rolled;
    }
    
}
