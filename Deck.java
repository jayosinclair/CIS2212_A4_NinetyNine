/*

The Deck class is responsible for managing a collection of playing cards. Here's what it needs to
do:
• Allow the creation of a deck containing one or more standard 52-card sets. The number
of decks should be passed as a parameter to the constructor.
• Store the cards using an ArrayList, which simplifies card management.
• Shuffle the cards after they’ve been added to the deck. This can be done by repeatedly
swapping pairs of cards at random—for example, performing 1,000 random swaps will
achieve a good shuffle.

*/



import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; //needed to shuffle

public class Deck {
    // Constants for a normal deck of playing cards.
    private final int SUITS = 4;
    private final int RANKS = 13;
    private final int DECK_SIZE = SUITS * RANKS;
    private final int SHUFFLE_COUNT = 1000;

    //TODO: Declare instance variables to hold the cards and the number 
    //      of packs of cards in the deck.

    ArrayList<Card> cardDeck;
    int numPacks;


    //TODO: Write a constructor that receives the number of packs of cards to put in the deck.
    //      The constructor should build the deck and then shuffle the cards.

    public Deck(int numPacks){

        this.numPacks = numPacks;
        this.buildDeck();
        this.shuffleDeck();

    }


    //TODO: Write the method that builds the deck of cards.
    //      Instantiate the ArrayList. Send the total number of cards to the constructor.
    //      Use nested loops.  The outer loop counts through the number of packs.
    //      The inner loop counts through the card numbers, from 0 to < DECK_SIZE.
    //      Add a new Card to the list using the inner loop control variable.

    private void buildDeck() {

        cardDeck = new ArrayList<Card>(numPacks * DECK_SIZE);

        for (int i = 0; i < numPacks; i++){

            for (int j = 0; j < DECK_SIZE; j++){

                cardDeck.add(new Card(j));

            }

        }

    }

    //TODO: Write the method that shuffles the deck
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
        
        
        /*
        
        Shuffle the cards after they’ve been added to the deck. This can be done by repeatedly
        swapping pairs of cards at random—for example, performing 1,000 random swaps will
        achieve a good shuffle.

        AI Tip: Explore the Collections.swap method to help with this.

        */

    }

    //TODO: Write the methods that implement the behaviors of a deck of cards.

   /*
   
   Provide a method to get the next card from the deck. Return null if the deck is empty.
    AI Tip: Do some research to see if it is more efficient to remove the card from the
    beginning or the end of the ArrayList. Choose the most efficient method.

    Include a method to return the current number of cards remaining in the deck.
   
   */

    public Card getNextCard(){ //Return type needs to be wrapper Integer in order to return null.
                                  //I learned this with a google search for null return type.

        if (cardDeck.isEmpty()){

            return null;

        }

        else{

            return cardDeck.removeLast();

        }

    }


    public int getCurrentCardCount(){

        return cardDeck.size();

    }


}
