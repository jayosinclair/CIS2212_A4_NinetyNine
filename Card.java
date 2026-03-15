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

Suits (0–3): diamonds (0), clubs (1), hearts (2), spades (3)
Ranks (0–12): ace (0), two (1), three (2), …, Jack (10), Queen (11), King (12)

*/

/**
 * The Card class manages getting info from a numeric representation of a number between 0 - 51 in a standard card pack/deck.
 */
public class Card {
    
    //Static member variables (perfect arrays in this case)
    private static final String[] suitName = {"Diamonds", "Clubs", "Hearts", "Spades"};
    private static final String[] rankName = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

    private final int NUM_CARDS_PER_SUIT = 13; //I thought about getting rid of this and just using rankName.length, but I don't know if that's a good idea. 
    // I think it's easier to understand from a readability standpoint to just have a constant, but there are now two sources of truth to maintain...

    private int suit;
    private int rank;

    public Card (int cardID){

        suit = cardID / NUM_CARDS_PER_SUIT; //Integer division will get the suit.     
        rank = cardID % NUM_CARDS_PER_SUIT; //The repeating pattern of ranks for each suit is like hours on a clock. 

    }

    public int getSuit(){

        return suit;

    }

    public int getRank(){

        return rank;

    }

    @Override
    public String toString(){

    //Code below is result of using Gemini in accordance with assignment instructions. This is more efficient than
    //my original code (in Word doc submittal/ previous commits in GitHub repo).

    String left = rankName[this.getRank()];
    String right = suitName[this.getSuit()];
    return left + " of " + right;

    }

}