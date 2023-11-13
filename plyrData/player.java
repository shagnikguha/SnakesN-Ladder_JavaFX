package plyrData;

import brdData.*;

public class player<T extends Dice, U extends Board> {
    private String name;
    private int position;
    private T obj;  // Dice object
    private U co1;  // Board object

    public player(String name, T obj, U co1) {
        this.name = name;
        this.position = 0;
        this.obj = obj;
        this.co1 = co1;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int move() {
        int dice = obj.throwDice();

        int currentLoc = position;
        int newLoc = dice + position;

        try {
            int result = co1.check(newLoc);

            if (result != -1) {
                position = result;
            } else {
                if (newLoc > 100) {
                    throw new Exception("Invalid move: newLoc exceeds 100");
                }
                position = newLoc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            position = currentLoc;
        }

        return dice;
    }
}
