package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.Pos;

import plyrData.player;

public class Main extends Application {
    private player player1;
    private player player2;
    private int turn;
    private TextArea player1InfoTextArea;
    private TextArea player2InfoTextArea;
    private Button rollButton;
    private Label diceRollLabel;
    private ProgressBar player1ProgressBar;
    private ProgressBar player2ProgressBar;
    private Stage primaryStage;
    private TextArea playerMovementTextArea;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        player1 = new player("P1");
        player2 = new player("P2");
        turn = 1;

        primaryStage.setTitle("Snakes and Ladders Game");
        BorderPane root = new BorderPane();

        // Add styles for inputPanel (background color)
        VBox inputPanel = new VBox(10);
        inputPanel.setAlignment(Pos.CENTER);
        inputPanel.setStyle("-fx-background-color: #a0a0a0;");

        Label nameLabel = new Label("Enter Player Names:");
        TextField player1NameField = new TextField();
        TextField player2NameField = new TextField();
        rollButton = new Button("Roll Dice");
        rollButton.setDisable(false);

        inputPanel.getChildren().addAll(nameLabel, player1NameField, player2NameField, rollButton);
        root.setTop(inputPanel);

        HBox dicePanel = new HBox(10);
        dicePanel.setAlignment(Pos.CENTER);

        // Add styles for dicePanel (background color)
        dicePanel.setStyle("-fx-background-color: #f0f0f0;");

        Label diceRollTextLabel = new Label("Dice Roll: ");
        diceRollLabel = new Label();

        dicePanel.getChildren().addAll(diceRollTextLabel, diceRollLabel);
        root.setBottom(dicePanel);

        VBox player1Panel = new VBox(10);

        // Add styles for player1Panel (background color)
        player1Panel.setStyle("-fx-background-color: #90EE90;");
        player1InfoTextArea = new TextArea();
        player1InfoTextArea.setEditable(false);
        player1ProgressBar = new ProgressBar(0);
        player1ProgressBar.setPrefWidth(150);
        player1Panel.getChildren().addAll(new Label("Player 1"), player1ProgressBar, new ScrollPane(player1InfoTextArea));

        VBox player2Panel = new VBox(10);

        // Add styles for player2Panel (background color)
        player2Panel.setStyle("-fx-background-color: #ADD8E6;");
        player2InfoTextArea = new TextArea();
        player2InfoTextArea.setEditable(false);
        player2ProgressBar = new ProgressBar(0);
        player2ProgressBar.setPrefWidth(150);
        player2Panel.getChildren().addAll(new Label("Player 2"), player2ProgressBar, new ScrollPane(player2InfoTextArea));

        HBox playerInfoPanel = new HBox(20);

        // Add styles for playerInfoPanel (background color)
        playerInfoPanel.setStyle("-fx-background-color: #f0f0f0;");
        playerInfoPanel.getChildren().addAll(player1Panel, player2Panel);
        root.setCenter(playerInfoPanel);

        playerMovementTextArea = new TextArea();
        playerMovementTextArea.setEditable(false);
        playerMovementTextArea.setPrefRowCount(10);
        playerMovementTextArea.setPrefColumnCount(10);

        VBox gameBoardPanel = new VBox(10);
        gameBoardPanel.setAlignment(Pos.TOP_CENTER);

        // Add styles for gameBoardPanel (background color)
        gameBoardPanel.setStyle("-fx-background-color: #E6E6E6;");
        gameBoardPanel.getChildren().addAll(new Label("Game Board"), playerMovementTextArea);

        root.setRight(gameBoardPanel);

        Scene scene = new Scene(root, 800, 400);

        primaryStage.setScene(scene);

        rollButton.setOnAction(event -> {
            if (turn % 2 == 1) {
                playTurn(player1, player1NameField.getText(), "Player 1", player1InfoTextArea, player2);
            } else {
                playTurn(player2, player2NameField.getText(), "Player 2", player2InfoTextArea, player1);
            }
            turn++;
        });

        primaryStage.show();
    }

    private void playTurn(player currentPlayer, String playerNameFieldText, String playerLabel, TextArea playerInfoTextArea, player otherPlayer) {
        playerInfoTextArea.clear();

        playerInfoTextArea.appendText("Turn " + turn + "\n");
        playerInfoTextArea.appendText(playerLabel + "'s turn. Rolling the dice...\n");

        int diceRoll = currentPlayer.move();
        playerInfoTextArea.appendText("Player Name: " + playerNameFieldText + "\n");
        playerInfoTextArea.appendText("Final Position: " + currentPlayer.getPosition() + "\n");

        diceRollLabel.setText(String.valueOf(diceRoll));

        player1ProgressBar.setProgress(player1.getPosition() / 100.0);
        player2ProgressBar.setProgress(player2.getPosition() / 100.0);

        playerMovementTextArea.clear();
        for (int i = 1; i <= 100; i++) {
            if (i == currentPlayer.getPosition()) {
                playerMovementTextArea.appendText(currentPlayer.getName());
            } else if (i == otherPlayer.getPosition()) {
                playerMovementTextArea.appendText(otherPlayer.getName());
            } else {
                playerMovementTextArea.appendText("_");
            }
            if (i % 10 == 0) {
                playerMovementTextArea.appendText("\n");
            } else {
                playerMovementTextArea.appendText(" ");
            }
        }

        if (currentPlayer.getPosition() >= 100) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText(playerNameFieldText + " wins!");
            alert.setContentText("Game Over");
            alert.initOwner(primaryStage);
            alert.showAndWait();

            rollButton.setDisable(true);

            Platform.exit();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
