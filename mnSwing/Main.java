package mnSwing;

import javax.swing.*;

import plyrData.player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private player player1;
    private player player2;
    private int turn;
    private JTextArea player1InfoTextArea;
    private JTextArea player2InfoTextArea;
    private JButton rollButton;
    private JLabel diceRollLabel;
    private JProgressBar player1ProgressBar;
    private JProgressBar player2ProgressBar;
    private JTextArea player1MovementTextArea;
    private JTextArea player2MovementTextArea;
    private JTextField player1NameField; // Declare as instance variables
    private JTextField player2NameField;
    
    public Main() {
        player1 = new player("P1");
        player2 = new player("P2");
        turn = 1;

        setLayout(new BorderLayout());

        // Input panel to get player names
        JPanel inputPanel = new JPanel();
        JLabel nameLabel = new JLabel("Enter Player Names:");
        player1NameField = new JTextField(10);
        player2NameField = new JTextField(10);
        rollButton = new JButton("Roll Dice");
        rollButton.setEnabled(true);

        inputPanel.add(nameLabel);
        inputPanel.add(player1NameField);
        inputPanel.add(player2NameField);
        inputPanel.add(rollButton);

        // Player info text areas for game updates
        player1InfoTextArea = new JTextArea(10, 40);
        player1InfoTextArea.setEditable(false);

        player2InfoTextArea = new JTextArea(10, 40);
        player2InfoTextArea.setEditable(false);

        // Create a split pane for both player text areas
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(new JScrollPane(player1InfoTextArea));
        splitPane.setBottomComponent(new JScrollPane(player2InfoTextArea));
        splitPane.setResizeWeight(0.5);

        // Label to display the dice roll
        diceRollLabel = new JLabel();

        // Progress bars for player progress
        player1ProgressBar = new JProgressBar(0, 100);
        player1ProgressBar.setStringPainted(true);
        player1ProgressBar.setString("Player 1");

        player2ProgressBar = new JProgressBar(0, 100);
        player2ProgressBar.setStringPainted(true);
        player2ProgressBar.setString("Player 2");

        // Text areas for player movement
        player1MovementTextArea = new JTextArea(10, 10);
        player1MovementTextArea.setEditable(false);

        player2MovementTextArea = new JTextArea(10, 10);
        player2MovementTextArea.setEditable(false);

        JPanel dicePanel = new JPanel();
        dicePanel.add(new JLabel("Dice Roll: "));
        dicePanel.add(diceRollLabel);

        JPanel progressPanel = new JPanel();
        progressPanel.setLayout(new GridLayout(2, 2));
        progressPanel.add(player1ProgressBar);
        progressPanel.add(player1MovementTextArea); // Add player1's movement text area
        progressPanel.add(player2ProgressBar);
        progressPanel.add(player2MovementTextArea); // Add player2's movement text area
        
        inputPanel.setBackground(Color.LIGHT_GRAY);
        progressPanel.setBackground(Color.DARK_GRAY);
        player1InfoTextArea.setForeground(Color.BLUE);
        player2InfoTextArea.setForeground(Color.GREEN);
        player1MovementTextArea.setForeground(Color.RED);
        player2MovementTextArea.setForeground(Color.MAGENTA);
        
        add(inputPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER); // Add the split pane
        add(dicePanel, BorderLayout.SOUTH);
        add(progressPanel, BorderLayout.WEST);

        // ActionListener for the rollButton
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn % 2 == 1) {
                    playTurn(player1, player1NameField.getText(), "Player 1", player1InfoTextArea, player1MovementTextArea);
                } else {
                    playTurn(player2, player2NameField.getText(), "Player 2", player2InfoTextArea, player2MovementTextArea);
                }
                turn++;
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    private void playTurn(player currentPlayer, String playerNameFieldText, String playerLabel, JTextArea playerInfoTextArea, JTextArea playerMovementTextArea) {
        // Clear the player info and movement areas
        playerInfoTextArea.setText("Turn " + turn + "\n");
        playerInfoTextArea.append(playerLabel + "'s turn. Rolling the dice...\n");
        playerMovementTextArea.setText("");

        int diceRoll = currentPlayer.move(); // Get the dice roll value

        playerInfoTextArea.append("Player Name: " + playerNameFieldText + "\n");
        playerInfoTextArea.append("Final Position: " + currentPlayer.getPosition() + "\n");

        // Update the progress bars
        if (currentPlayer == player1) {
            player1ProgressBar.setValue(currentPlayer.getPosition());
        } else {
            player2ProgressBar.setValue(currentPlayer.getPosition());
        }

        // Update the player's movement text area
        for (int i = 1; i <= 100; i++) {
            playerMovementTextArea.append(i == currentPlayer.getPosition() ? currentPlayer.getName() : "_");
            if (i % 10 == 0) {
                playerMovementTextArea.append("\n");
            } else {
                playerMovementTextArea.append(" ");
            }
        }

        // Display the dice roll value
        diceRollLabel.setText(String.valueOf(diceRoll));

        if (currentPlayer.getPosition() >= 100) {
            // Player wins, show a pop-up message with replay and close options
            int option = JOptionPane.showOptionDialog(this, playerLabel + " wins!", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Replay", "Close"}, "Replay");
            
            if (option == JOptionPane.YES_OPTION) {
                // Replay the game
                resetGame(); // You'll need to implement this method to reset the game
            } else {
                // Close the application
                System.exit(0);
            }
        }
    }

    // Add a method to reset the game
    private void resetGame() {
    	// Clear player names
        player1NameField.setText("");
        player2NameField.setText("");
    	
        // Reset player positions and any other game state variables
        player1 = new player("P1");
        player2 = new player("P2");

        // Clear JTextAreas, progress bars, and movement text areas
        player1InfoTextArea.setText("");
        player2InfoTextArea.setText("");
        player1MovementTextArea.setText("");
        player2MovementTextArea.setText("");
        player1ProgressBar.setValue(0);
        player2ProgressBar.setValue(0);

        turn = 0;
        // Enable the rollButton
        rollButton.setEnabled(true);
    }
    public static void main(String[] args) {
          SwingUtilities.invokeLater(new Runnable() {
        	  @Override
	          public void run() {
	              new Main();
	          }
	      });
    }
}

