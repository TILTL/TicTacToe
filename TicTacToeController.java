package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeController {
    private static final int BOARD_SIZE = 3;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";

    private Button[][] board;
    private String currentPlayer;

    @FXML
    private Label statusLabel;

    @FXML
    private void initialize() {
        board = new Button[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = PLAYER_X;

        statusLabel.setText("Player " + currentPlayer + "'s turn");
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);
            button.setDisable(true);

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
}
