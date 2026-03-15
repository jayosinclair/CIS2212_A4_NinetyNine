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



//TODO: Complete the Card class according to the specifications.


/*

The Card class should store a card’s suit and rank. Its constructor receives an integer from 0 to
51 and computes the suit and rank accordingly. (It isn’t necessary to keep the original number.)
The Card class should be able to return:
1. Its numeric suit and rank
2. Its standard name, e.g., Ace of Clubs or Three of Hearts.
AI Tip: Research how to solve this part using the toString method.

A convenient way to create the standard names is to define String arrays for the rank and
suit names, then use the numeric values as indices to retrieve the appropriate names. Each
card does NOT need its own copies of these String arrays, so declare them in a way that all
Card objects share the arrays.

*/


/*

• Suits (0–3): diamonds (0), clubs (1), hearts (2), spades (3)
• Ranks (0–12): ace (0), two (1), three (2), …, Jack (10), Queen (11), King (12)

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

        /*Here was my initial code (this is commented out because I changed it after using Gemini as required by the assignment):

        String left = "";
        String middle = "of";
        String right = "";
        String composite = "";


        for (int i = 0; i < rankName.length; i++){

            if (i == this.getRank()){

                left = rankName[i];

            }

        }


        for (int i = 0; i < suitName.length; i++){

            if (i == this.getSuit()){

                right = suitName[i];

            }

        }

        composite = left + " " + middle + " " + right;

        return composite;

    }

    */


    String left = rankName[this.getRank()];
    String right = suitName[this.getSuit()];
    return left + " of " + right;

    }

}