package classes;

import java.util.Arrays;

import javax.swing.JPanel;

import ui.InitWindow;

public class GameLoop {

    private GameAPI gameAPI;
    private JPanel mainpanel;
    private InitWindow uiWindow;


    private static void render(GameAPI gameAPI,JPanel mainpanel){
		for (int i = 0; i < gameAPI.getPlayer().length;i++) {
			for (int j = 0;j < gameAPI.getPlayer()[i].getFigures().length;j++){
				gameAPI.getPlayer()[i].getFigures()[j].updateCords();
			}
		}
		mainpanel.repaint();
    }

    public static void RollDiceUI(GameAPI gameAPI,InitWindow uiWindow,JPanel mainpanel,int toroll) throws InterruptedException{
        NextMoves nextMoves = gameAPI.rollDice(gameAPI.getOnturn().getTeamindex(),toroll);
        uiWindow.getYourTurnAlert().alertTurn((gameAPI.getOnturn().getTeamindex()+1)%gameAPI.getPlayer().length);
        render(gameAPI, mainpanel);
        uiWindow.getDiceUI().setVisible(nextMoves.getRolled());
        if(nextMoves.getMoves().size() > 0) {
            render(gameAPI, mainpanel);
            for (int j = 0; j < 4;j++){
                for (int m = 0;m< 4;m++){
                    gameAPI.getPlayer()[j].getFigures()[m].getFigureUI().UnSetBackground();
                }
            }
            render(gameAPI, mainpanel);
            for (int i = 0; i < nextMoves.getMoves().size();i++) {
                nextMoves.getMoves().get(i).getFieldFrom().getFigure().getFigureUI().setBackground(nextMoves.getMoves().get(i));
                mainpanel.repaint();
            }
            if (nextMoves.getMoves().get(0).getPlayer().getType() != "human"){
                if (GameAPI.makeMove(nextMoves.getMoves().get(0))) {
                    System.out.println(gameAPI.getOnturn().getTeamindex() + " HAS WON YEAH!!");
                    return;
                };
            }
            render(gameAPI, mainpanel);
            // while (gameAPI.getOnturn() == currentPlayer) {
            //     continue;
            // }
            render(gameAPI, mainpanel);
        } else {
            // System.out.println(gameAPI.getOnturn().getTeamindex() + " couldn't move lol");
        }
        // if (gameAPI.getPlayer()[(gameAPI.getOnturn().getTeamindex()-1)%4].hasWon()){
        //     System.out.println("SOMEONE HAS WON WHOOP WHOOP");
        // }
    }

    public void RunLoop(Boolean test) throws InterruptedException{
        uiWindow = new InitWindow();
        gameAPI = new GameAPI();
        mainpanel = uiWindow.windowInit(gameAPI.getGameBoard().getGameFields(),gameAPI,test);
        for (int i = 0; i < gameAPI.getPlayer().length;i++) {
            for (int j = 0;j < gameAPI.getPlayer()[i].getFigures().length;j++){
                mainpanel.add(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp());
                mainpanel.setComponentZOrder(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp(), 1);
            }
        }
        render(gameAPI, mainpanel);


        for(int i = 0; i < 1000;i++){
            if (gameAPI.getOnturn().getType() != "human"){
                RollDiceUI(gameAPI, uiWindow, mainpanel,-1);
            }
        }
        System.out.println("");
    }

    // public static void Main() throws InterruptedException {
    //     // Thread.sleep(1000);
    //     GameAPI gameAPI = new GameAPI();
    //     InitWindow uiWindow = new InitWindow();
    //     JPanel mainpanel = uiWindow.windowInit(gameAPI.getGameBoard().getGameFields());
    //     for (int i = 0; i < gameAPI.getPlayer().length;i++) {
    //         for (int j = 0;j < gameAPI.getPlayer()[i].getFigures().length;j++){
    //             mainpanel.add(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp());
    //             mainpanel.setComponentZOrder(gameAPI.getPlayer()[i].getFigures()[j].getFigureUI().getComp(), 0);
    //         }
    //     }


    //     for(int i = 0; i < 1000;i++){
    //         NextMoves nextMoves = gameAPI.rollDice(gameAPI.getOnturn().getTeamindex());
    //         uiWindow.getYourTurnAlert().alertTurn(gameAPI.getOnturn().getTeamindex());
    //         Thread.sleep(200);

    //         if(nextMoves.getMoves().size() > 0) {
    //             uiWindow.getDiceUI().setVisible(nextMoves.getMoves().get(0).getRolledNumber());
    //             Thread.sleep(3000);
    //             if (gameAPI.makeMove(nextMoves.getMoves().get(0))) {
    //                 System.out.println(gameAPI.getOnturn().getTeamindex() + " HAS WON YEAH!!");
    //                 Thread.sleep(2000);
    //                 return;
    //             };
    //             Thread.sleep(200);
    //             // Thread.sleep(1000);
    //             render(gameAPI,mainpanel);
    //             mainpanel.repaint();
    //         } else {
    //             // System.out.println(gameAPI.getOnturn().getTeamindex() + " couldn't move lol");
    //         }
    //     }
    //     System.out.println("");
    // }
}
