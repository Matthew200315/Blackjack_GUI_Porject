package com.blackjackgui.blackjackgui;

import java.util.ArrayList;
import java.util.Random;

public class BlackJack {

    private class Card {
        String value;
        String type;

        Card(String value, String type) {
            this.value = value;
            this.type = type;
        }

        public String toString() {
            return value + "-" + type;
        }

        public int getValue() {
            if ("AJQK".contains(value)) {
                if (value.equals("A")) {
                    return 11;
                }
                return 10;
            }
            return Integer.parseInt(value); // 2-10
        }

        public boolean isAce() {
            return value.equals("A");
        }
    }

    private ArrayList<Card> deck;
    private Random random = new Random();

    // Dealer's hand
    private Card hiddenCard;
    private ArrayList<Card> dealerHand;
    private int dealerSum;
    private int dealerAceCount;

    // Player's hand
    private ArrayList<Card> playerHand;
    private int playerSum;
    private int playerAceCount;

    public BlackJack() {
        startGame();
    }

    public void startGame() {
        buildDeck();
        shuffleDeck();

        // Dealer's hand
        dealerHand = new ArrayList<>();
        dealerSum = 0;
        dealerAceCount = 0;

        // Deal the first hidden card
        hiddenCard = deck.remove(deck.size() - 1); // First card is hidden
        dealerSum += hiddenCard.getValue();
        dealerAceCount += hiddenCard.isAce() ? 1 : 0;

        // Deal the second card to the dealer
        Card card = deck.remove(deck.size() - 1);
        dealerSum += card.getValue();
        dealerAceCount += card.isAce() ? 1 : 0;
        dealerHand.add(card);

        // Player's hand
        playerHand = new ArrayList<>();
        playerSum = 0;
        playerAceCount = 0;

        // Deal two cards to the player
        for (int i = 0; i < 2; i++) {
            card = deck.remove(deck.size() - 1);
            playerSum += card.getValue();
            playerAceCount += card.isAce() ? 1 : 0;
            playerHand.add(card);
        }

        // Check for Blackjack at the start of the game
        checkBlackjack();
    }

    public void handleHit() {
        Card card = deck.remove(deck.size() - 1);
        playerHand.add(card);
        playerSum += card.getValue();
        playerAceCount += card.isAce() ? 1 : 0;

        // Check for Blackjack after a hit
        if (playerSum == 21 && playerHand.size() == 2) {
            System.out.println("Player hit blackjack and wins!");
            terminateGame("Player");
        }
    }

    public void handleStand() {
        // Dealer's turn logic (simplified for now)
        while (dealerSum < 17) {
            Card card = deck.remove(deck.size() - 1);
            dealerHand.add(card);
            dealerSum += card.getValue();
            dealerAceCount += card.isAce() ? 1 : 0;
        }

        // Check for dealer blackjack after they stand
        if (dealerSum == 21 && dealerHand.size() == 2) {
            System.out.println("Dealer hit blackjack and wins!");
            terminateGame("Dealer");
        } else {
            // Compare sums if neither has blackjack
            determineWinner();
        }
    }

    public String getPlayerHandWithSum() {
        StringBuilder hand = new StringBuilder();
        for (Card card : playerHand) {
            hand.append(card.toString()).append(" ");
        }
        return hand.toString() + " (Total: " + playerSum + ")";
    }

    public String getDealerHandWithSum(boolean revealHiddenCard) {
        StringBuilder hand = new StringBuilder();
        if (revealHiddenCard) {
            // Show both cards in dealer's hand (reveal hidden card)
            for (Card card : dealerHand) {
                hand.append(card.toString()).append(" ");
            }
        } else {
            // Show hidden card and one revealed card
            hand.append(hiddenCard.toString()).append(" (Hidden) ");
            for (int i = 1; i < dealerHand.size(); i++) {
                hand.append(dealerHand.get(i).toString()).append(" ");
            }
        }
        return hand.toString() + " (Total: " + dealerSum + ")";
    }

    public int getPlayerSum() {
        return playerSum;
    }

    public int getDealerSum() {
        return dealerSum;
    }

    private void buildDeck() {
        deck = new ArrayList<>();
        String[] values = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] types = {"C", "D", "H", "S"};

        for (String type : types) {
            for (String value : values) {
                Card card = new Card(value, type);
                deck.add(card);  // Make sure you're adding cards to the deck here
            }
        }
    }

    private void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currCard);
        }
    }

    private void checkBlackjack() {
        if (playerSum == 21 && playerHand.size() == 2) {
            System.out.println("Player hit blackjack and wins!");
            terminateGame("Player");
        } else if (dealerSum == 21 && dealerHand.size() == 2) {
            System.out.println("Dealer hit blackjack and wins!");
            terminateGame("Dealer");
        }
    }

    private void determineWinner() {
        if (playerSum > 21) {
            System.out.println("Player busted! Dealer wins.");
        } else if (dealerSum > 21) {
            System.out.println("Dealer busted! Player wins.");
        } else if (playerSum > dealerSum) {
            System.out.println("Player wins!");
        } else if (dealerSum > playerSum) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void terminateGame(String winner) {
        System.out.println(winner + " wins the game!");
        System.exit(0); // End the game
    }
}
