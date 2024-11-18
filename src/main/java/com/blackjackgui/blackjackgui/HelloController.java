package com.blackjackgui.blackjackgui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloController {

    @FXML
    private Label welcomeText;

    @FXML
    private Button helloButton;

    @FXML
    private Button hitButton;

    @FXML
    private Button standButton;

    @FXML
    private Button resetButton;

    @FXML
    private VBox rootLayout;

    private BlackJack game;

    @FXML
    protected void onHelloButtonClick() {
        game = new BlackJack(); // Start the game
        welcomeText.setText("Player hand: " + game.getPlayerHandWithSum() + "\nDealer hand: " + game.getDealerHandWithSum(false));
        hitButton.setVisible(true);
        standButton.setVisible(true);
        helloButton.setVisible(false);
    }

    @FXML
    private void onHitButtonClick() {
        game.handleHit();
        welcomeText.setText("Player hand: " + game.getPlayerHandWithSum() + "\nDealer hand: " + game.getDealerHandWithSum(false));
        if (game.getPlayerSum() > 21) {
            endGame("Player busted! Dealer wins!");
        }
    }

    @FXML
    private void onStandButtonClick() {
        game.handleStand();
        int dealerSum = game.getDealerSum();
        int playerSum = game.getPlayerSum();
        String result = "Player hand: " + game.getPlayerHandWithSum() + "\nDealer's hand: " + game.getDealerHandWithSum(true);

        if (dealerSum > 21) {
            endGame(result + "\nDealer busted! Player wins!");
        } else if (playerSum > 21) {
            endGame(result + "\nPlayer busted! Dealer wins!");
        } else if (playerSum == 21) {
            endGame(result + "\nPlayer wins with Blackjack!");
        } else if (dealerSum == 21) {
            endGame(result + "\nDealer wins with Blackjack!");
        } else if (playerSum > dealerSum) {
            endGame(result + "\nPlayer wins!");
        } else if (dealerSum > playerSum) {
            endGame(result + "\nDealer wins!");
        } else {
            endGame(result + "\nIt's a tie!");
        }
    }

    private void endGame(String resultMessage) {
        welcomeText.setText(resultMessage);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        resetButton.setVisible(true);
    }

    @FXML
    private void onResetButtonClick() {
        // Reset the game to start a new round
        game = new BlackJack();
        welcomeText.setText("Press 'Hello' to start the game.");
        resetButton.setVisible(false);
        hitButton.setVisible(false);
        standButton.setVisible(false);
        helloButton.setVisible(true);
    }
}
