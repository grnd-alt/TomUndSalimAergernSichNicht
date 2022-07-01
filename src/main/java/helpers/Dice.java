package helpers;

import java.util.Random;

public class Dice{
    static Random dice;
    public Dice() {
        dice = new Random();
    }

    public int rollDice() {
        return dice.nextInt(6)+1;
    }
}
