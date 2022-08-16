package classes;

import java.util.Arrays;

import javax.swing.JPanel;

import ui.InitWindow;

public class GameLoop {
    private static void render(GameAPI gameAPI,JPanel mainpanel){
		for (int i = 0; i < gameAPI.getPlayer().length;i++) {
			for (int j = 0;j < gameAPI.getPlayer()[i].getFigures().length;j++){
				gameAPI.getPlayer()[i].getFigures()[j].updateCords();
				// mainpanel.setComponentZOrder(gameAPI.getPlayer()[i].getFigures()[i].getFigureUI().getComp(), 0);
			}
		}
		mainpanel.repaint();
	}
    public static void Main() throws InterruptedException {
        // Thread.sleep(1000);
        GameAPI gameAPI = new GameAPI();
        InitWindow uiWindow = new InitWindow();
        JPanel mainpanel = uiWindow.windowInit(gameAPI.getGameBoard().getGameFields());
        for (int i = 0; i < gameAPI.getPlayer().length;i++) {
            for (int j = 0;j < gameAPI.getPlayer()[i].getFigures().length;j++){
                mainpanel.add(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp());
                mainpanel.setComponentZOrder(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp(), 0);
            }
        }
        for(int i = 0; i < 1000;i++){
            // if (i == 500) {
            //     Thread.sleep(3000);
            // }

            NextMoves nextMoves = gameAPI.rollDice(gameAPI.getOnturn().getTeamindex());
            // nextMoves.getMoves().get(0).getRolledNumber();
            // NextMoves nextMoves = gameAPI.rollDice(0);
            if(nextMoves.getMoves().size() > 0) {
                uiWindow.getDiceUI().setVisible(nextMoves.getMoves().get(0).getRolledNumber());
                // System.out.println(gameAPI.getOnturn().getTeamindex() + " did move lol");
                // System.out.println("made move from " + nextMoves.getMoves().get(0).getFieldFrom().getIndex() + "; to " + nextMoves.getMoves().get(0).getFieldTo().getIndex());
                if (gameAPI.makeMove(nextMoves.getMoves().get(0))) {
                    System.out.println(gameAPI.getOnturn().getTeamindex() + " HAS WON YEAH!!");
                    Thread.sleep(2000);
                    return;
                };
                Thread.sleep(200);
                // Thread.sleep(1000);
                render(gameAPI,mainpanel);
                mainpanel.repaint();
            } else {
                // System.out.println(gameAPI.getOnturn().getTeamindex() + " couldn't move lol");
            }
        }
        System.out.println("");
    }
}
