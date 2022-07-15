package classes;

import java.awt.Color;

public class Figure {
    private int index;
    public int getIndex() {
        return index;
    }

    private int position;
    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public Figure(int index, int position,Color color) {
        this.index = index;
        this.position = position;
    }

    public void moveMe(int rolled){
        this.position += rolled;
    }

    public void hitMe() {
        this.position = 0;
    }
}
