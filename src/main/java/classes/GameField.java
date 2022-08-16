package classes;

import java.awt.Color;

public class GameField {
    private String type;
    private Figure figure;
    private Color color;
    private int index;
    private int x;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    private int y;
    

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getIndex() {
        return index;
    }
    public Color getColor() {
        return color;
    }
    public Figure getFigure() {
        return figure;
    }
    public void setFigure(Figure figure) {
        this.figure = figure;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public GameField(String type,Color color,int index){
        this.type = type;
        this.color = color;
        this.index = index;
    }
}
