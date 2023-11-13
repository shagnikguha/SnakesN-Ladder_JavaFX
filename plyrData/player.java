package plyrData;

import brdData.*;

public class player {
    private String name;
    private int position;
    Dice obj = new Dice();
    Board co1 = new Board();
    
    public player(String name) {
        this.name = name;
        this.position = 0;
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
