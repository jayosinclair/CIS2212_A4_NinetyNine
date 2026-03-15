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

/**
 * The Player class manages the player's hand of cards.
 */

public class Player {

    private String name;
    private ArrayList<Card> hand;

    /**
     * The Player class constructor assigns a player name and creates a hand for that player.
     * @param playerName
     */
    public Player(String playerName){

        this.name = playerName;
        this.hand = new ArrayList<Card>();

    }


    /**
     * The getName method accesses the name instance variable.
     * @return The player's name
     */
    public String getName(){

        return this.name;

    }


    /**
     * The getCard method gets a card from the hand using a specified index, assuming hand is not empty.
     * @param index
     * @return
     */
    public Card getCard(int index){

        if (hand.isEmpty()){

            return null;

        }

        else{

            return hand.get(index);

        }

    }

    /**
     * The playCard method removes it from the hand and returns the value for use elsewhere.
     * @param index
     * @return
     */
    public Card playCard(int index){

        if (hand.isEmpty()){

            return null;

        }

        else{

            return hand.remove(index);

        }

    }

    /**
     * The addCard method adds a new card to the hand.
     * @param someCard A card from the deck to be added to the hand
     */
    public void addCard(Card someCard){

        hand.add(someCard);

    }

    /**
     * The getCurrentCardCount method accesses the number of cards in the deck. Should be 5 unless accessed before adding a new card.
     * @return
     */
    public int getCurrentCardCount(){

        return hand.size();

    }


}



