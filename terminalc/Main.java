package terminalc;

import java.util.*;
import plyrData.player;

public class Main {
    public static void main(String[] args) {
        player player1 = new player("Alice");
        player player2 = new player("Bob");

        Scanner scanner = new Scanner(System.in);

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn);

            // Player 1 
            System.out.println(player1.getName() + "'s turn. Press Enter to roll the dice.");
            scanner.nextLine();
            player1.move();
            System.out.println("Player Name: " + player1.getName());
            System.out.println("Final Position: " + player1.getPosition());

            if (player1.getPosition() >= 100) {
                System.out.println(player1.getName() + " wins!");
                break;
            }

            // Player 2
            System.out.println(player2.getName() + "'s turn. Press Enter to roll the dice.");
            scanner.nextLine();
            player2.move();
            System.out.println("Player Name: " + player2.getName());
            System.out.println("Final Position: " + player2.getPosition());

            if (player2.getPosition() >= 100) {
                System.out.println(player2.getName() + " wins!");
                break;
            }
            turn++;
        }
    }
}

