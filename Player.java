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

/*

The Player class has a name and a collection of cards, often referred to as the player's “hand”.
Use an ArrayList to store the player’s cards.
A player object needs the following behaviors:
• Retrieve the player’s name.
• Get a card from the hand using a specified index.
• Play a card from the hand by removing it at a given index.
• Add a card to the hand (provided as a parameter). Name the parameter someCard.
• Return the current number of cards in the hand.

*/



//TODO: Complete the Player class according to the specifications.
import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<Card> hand;


    public Player(String playerName){

        this.name = playerName;
        this.hand = new ArrayList<Card>();

    }


    public String getName(){

        return this.name;

    }


    public Card getCard(int index){

        if (hand.isEmpty()){

            return null;

        }

        else{

            return hand.get(index);

        }

    }


    public Card playCard(int index){

        if (hand.isEmpty()){

            return null;

        }

        else{

            return hand.remove(index);

        }

    }


    public void addCard(Card someCard){

        hand.add(someCard);

    }


    public int getCurrentCardCount(){

        return hand.size();

    }


}



