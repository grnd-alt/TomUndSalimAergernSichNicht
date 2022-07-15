package helpers;

import java.util.Random;

public class Dice{
    private static Random dice = new Random();
    public Dice() {
        dice = new Random();
    }

    public static int rollDice() {
        return dice.nextInt(6)+1;
    }
}
