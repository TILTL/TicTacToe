package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private static final int BOARD_SIZE = 3;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";

    private Button[][] board;
    private String currentPlayer;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        board = new Button[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        statusLabel = new Label("Player " + currentPlayer + "'s turn");
        gridPane.add(statusLabel, 0, 0, BOARD_SIZE, 1);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Button button = new Button();
                button.setMinSize(80, 80);
                button.setOnAction(e -> {
                    handleButtonClick(button);
                    if (checkForWin()) {
                        showWinAlert();
                        resetBoard();
                    } else if (isBoardFull()) {
                        showDrawAlert();
                        resetBoard();
                    } else {
                        currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
                        statusLabel.setText("Player " + currentPlayer + "'s turn");
                    }
                });
                board[row][col] = button;
                gridPane.add(button, col, row + 1);
            }
        }

        Scene scene = new Scene(gridPane, 300, 340);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);
            button.setDisable(true);
        }
    }

    private boolean checkForWin() {
        String[][] boardValues = new String[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardValues[row][col] = board[row][col].getText();
            }
        }

        // Check rows
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (boardValues[row][0].equals(currentPlayer) &&
                    boardValues[row][1].equals(currentPlayer) &&
                    boardValues[row][2].equals(currentPlayer)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (boardValues[0][col].equals(currentPlayer) &&
                    boardValues[1][col].equals(currentPlayer) &&
                    boardValues[2][col].equals(currentPlayer)) {
                return true;
            }
        }

        // Check diagonals
        if (boardValues[0][0].equals(currentPlayer) &&
                boardValues[1][1].equals(currentPlayer) &&
                boardValues[2][2].equals(currentPlayer)) {
            return true;
        }

        if (boardValues[0][2].equals(currentPlayer) &&
                boardValues[1][1].equals(currentPlayer) &&
                boardValues[2][0].equals(currentPlayer)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + currentPlayer + " wins!");
        alert.showAndWait();
    }

    private void showDrawAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("It's a draw!");
        alert.showAndWait();
    }

    private void resetBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col].setText("");
                board[row][col].setDisable(false);
            }
        }
        currentPlayer = PLAYER_X;
        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
