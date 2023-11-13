package terminalc;

import java.util.*;
import plyrData.player;
import brdData.Board;
import plyrData.Dice;

public class Main {
    public static void main(String[] args) {
    	Dice d = new Dice();
    	Board board = new Board();
    	player<Dice, Board> player1 = new player<>("Alice", d, board);
    	player<Dice, Board> player2 = new player<>("Bob", d, board);
        int dice = 0;

        Scanner scanner = new Scanner(System.in);

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn);

            // Player 1 
            System.out.println(player1.getName() + "'s turn. Press Enter to roll the dice.");
            scanner.nextLine();
            dice = player1.move();
            System.out.println("Player Name: " + player1.getName());
            System.out.println("Final Position: " + player1.getPosition());
            System.out.println("Dice roll: " + dice);

            if (player1.getPosition() >= 100) {
                System.out.println(player1.getName() + " wins!");
                break;
            }

            // Player 2
            System.out.println(player2.getName() + "'s turn. Press Enter to roll the dice.");
            scanner.nextLine();
            dice = player2.move();
            System.out.println("Player Name: " + player2.getName());
            System.out.println("Final Position: " + player2.getPosition());
            System.out.println("Dice roll: " + dice);

            if (player2.getPosition() >= 100) {
                System.out.println(player2.getName() + " wins!");
                break;
            }
            turn++;
            
        }
        scanner.close();
    }
}

