package classes;

import java.awt.Color;

public class Figure {
    private int index;
    public int getIndex() {
        return index;
    }

    private GameField position;
    public void setPosition(GameField position) {
        this.position = position;
    }

    public GameField getPosition() {
        return position;
    }

    private Player owner;
    

    public Player getOwner() {
        return owner;
    }

    public Figure(int index, GameField position,Player owner) {
        this.index = index;
        this.position = position;
        this.owner =owner;
    }

    public void hitMe() {
        for (int i = 0;i<4;i++) {
            if (this.owner.getHomeFields()[i].getFigure() == null) {
                this.position = this.owner.getHomeFields()[i];
                this.owner.getHomeFields()[i].setFigure(this);
            }
        }
    }
}
