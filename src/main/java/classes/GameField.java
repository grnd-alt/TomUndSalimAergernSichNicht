package classes;

import java.awt.Color;

public class GameField {
    private String type;
    private Figure figure;
    private Color color;

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
    public GameField(String type,Color color){
        this.type = type;
        this.color = color;
    }
}
