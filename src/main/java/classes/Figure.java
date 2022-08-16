package classes;

import ui.FigureUI;

public class Figure {
    private int index;
    private FigureUI figureUI;
    public FigureUI getFigureUI() {
        return figureUI;
    }

    public int getIndex() {
        return index;
    }

    private GameField position;
    
    public void setPosition(GameField position) {
        this.position = position;
        this.figureUI.setCords(position.getX(), position.getY());
    }

    public void updateCords() {
        this.figureUI.setCords(this.position.getX(), this.position.getY());
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
        this.figureUI = new FigureUI(owner.getTeamindex(), position.getX(), position.getY());
    }

    public void hitMe() {
        for (int i = 0;i<4;i++) {
            if (this.owner.getHomeFields()[i].getFigure() == null) {
                this.setPosition(this.owner.getHomeFields()[i]);
                this.owner.getHomeFields()[i].setFigure(this);
                return;
            }
        }
    }
}
