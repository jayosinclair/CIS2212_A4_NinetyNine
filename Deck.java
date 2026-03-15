//Author: Jay Olson
//Section: CIS 2212-801
//Assignment: Project 4 Ninety Nine
//Due Date: March 20, 2026
//Submitted: March 15, 2026

/*This program is a version of the card game, Ninety Nine. Purpose: design and create classes, 
and use arrays and ArrayLists to solve a problem.*/

//Github repos for this project are at: https://github.com/jayosinclair/CIS2212_A4_NinetyNine.git
//https://github.com/jayosinclair/ninetyNineTestBench.git

//**********************************************************************************************************************

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; //needed to shuffle

/**
 * The Deck class is responsible for managing a collection of playing cards. Here's what it does:
 * -Allows the creation of a deck containing one or more standard 52-card sets. The number
 * of decks should be passed as a parameter to the constructor.
 * -Stores the cards using an ArrayList, which simplifies card management.
 * -Shuffles the cards after they’ve been added to the deck. This can be done by repeatedly
 * swapping pairs of cards at random—for example, performing 1,000 random swaps will
 * achieve a good shuffle.
 */

public class Deck {
    // Constants for a normal deck of playing cards.
    private final int SUITS = 4;
    private final int RANKS = 13;
    private final int DECK_SIZE = SUITS * RANKS;
    private final int SHUFFLE_COUNT = 1000;

    //Instance variables to hold the cards and the number of packs of cards in the deck.

    ArrayList<Card> cardDeck;
    int numPacks;


    /**
     * The Deck constructor receives the number of packs of cards to put in the deck.
     * The constructor builds the deck and then shuffles the cards.
     * 
     * @param numPacks
     */
    public Deck(int numPacks){

        this.numPacks = numPacks;
        this.buildDeck();
        this.shuffleDeck();

    }


    /**
     * Method buildDeck builds the deck of cards. Notes about this method:
     * Instantiate the ArrayList. Send the total number of cards to the constructor.
     * Use nested loops.  The outer loop counts through the number of packs.
     * The inner loop counts through the card numbers, from 0 to < DECK_SIZE.
     * Add a new Card to the list using the inner loop control variable.
     */
    private void buildDeck() {

        cardDeck = new ArrayList<Card>(numPacks * DECK_SIZE);

        for (int i = 0; i < numPacks; i++){

            for (int j = 0; j < DECK_SIZE; j++){

                cardDeck.add(new Card(j));

            }

        }

    }

    
    /**
     * The shuffleDeck method shuffles the cards after they’ve been added to the deck. This is done by repeatedly
        swapping pairs of cards at random—for example, performing 1,000 random swaps will
        achieve a good shuffle. The shuffleDeck method uses the Collections.swap method to shuffle cards.
     */
    private void shuffleDeck() {

        int index1 = -1;
        int index2 = -1;
        int fullDeckSize = DECK_SIZE * this.numPacks;
        Random rand = new Random();

        for (int i = 0; i < SHUFFLE_COUNT; i++){

            index1 = rand.nextInt(fullDeckSize);
            index2 = rand.nextInt(fullDeckSize);

            Collections.swap(cardDeck, index1, index2);

        }

    }

    
    /**
     * The drawCard method removes the last card from the deck and returns it for use elsewhere.
     * 
     * @return
     */
    public Card drawCard(){

        if (cardDeck.isEmpty()){

            return null;

        }

        else{

            return cardDeck.removeLast();

        }

    }


    /**
     * The getCurrentCardCount method gets the number of cards remaining in a deck.
     * 
     * @return
     */
    public int getCurrentCardCount(){

        return cardDeck.size();

    }


}
