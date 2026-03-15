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

import java.util.Scanner;

/**
 * The NinetyNineGame class manages the overall gameplay.
 */

public class NinetyNineGame {
    private Scanner input; //Style-wise, does it really matter what order I list variables vs constants vs objects like input?

    private final int CARDS_IN_HAND = 5;
    private final int GAME_OVER = 99;

    private Player[] players;
    private int contestants;
    private int currentPlayer;
    private int gameTotal = 0;
    private Deck myDeck;

    public NinetyNineGame() {
        input = new Scanner(System.in);
    }


    /**
     * The go method calls other methods to display the rules, get player count and names of players, set up a
     * card deck, and play the game.
     */
    public void go() {
        displayGreeting();
        getNames();

        //Create a Deck of Cards using the number of packs based on the number of players.

        myDeck = new Deck(calcPackCount());
        
        playGame();
    }

    /**
     * The displayGreeting method displays the game's rules.
     */
    private void displayGreeting() {
        String msg = "\n\n\nGreetings!  This is the card game Ninety-Nine.  Here are the rules.\n\n" 
            + "Each player gets 5 cards.  In a turn, each player plays a card. The point value of the card is added to\n" 
            + "the game total, which starts at zero.  The player that puts the total over 99 loses the game.  After a card is\n" 
            + "played, the player draws a new card from the deck.\n\n" 
            + "Each card has a point value that is added to the total. For most of the cards, the point value is\n" 
            + "the face value of the card.  For example, the two is worth 2 points, a Jack is worth 11, a Queen is worth 12,\n" 
            + "a King is worth 13, and an Ace is worth 14.  Here are the cards with special values:\n" 
            + "\tA nine is worth 0 points\n" 
            + "\tA ten is worth -10\n" 
            + "\tA seven adds 7, unless that would put the total over 99.  In that case, it adds 1.\n\n" 
            + "Also, crossing the point total between 33 and 34, or 55 and 56, or 77 and 78, in either direction, adds\n" 
            + "5 points to the total.\n\n" 
            + "Have fun!\n";
        System.out.println(msg);
    }


    /**
     * The getNames method asks how many people are playing, then gets the names and creates Player objects.
     */
    private void getNames() {


        System.out.print("How many people are playing? " );
        contestants = input.nextInt();

        while (contestants < 2){

            System.out.println("You must have two or more players. Try again: " );
            contestants = input.nextInt();

        }

        System.out.println(""); //For readability.

        players = new Player[contestants];

        for (int i = 0; i < contestants; i++){

            System.out.print("Player " + (i + 1) + ", enter your name: ");
            
            players[i] = new Player(input.next());

        }

    }


    /**
     * 
     * The calcPackCount method calculates how many packs are needed. For every 4 players a deck is needed for the
     * game's master deck.
     * 
     * @return the number of packs to be used in the game
     */
    private int calcPackCount(){

        //For every 4 players, add a deck.

        int packCount = -1;

        if (contestants < 2){ //Need at least two players to play.

            return packCount; //I just return -1 for this point in the class... We haven't gotten to exceptions yet.
                              //Program shouldn't be able to get to this point, though.

        }

        //I used Gemini here per assignment instructions to reach the formula of (n + (k - 1)) / k  where k is the group size (4).
        //Previous logic I wrote myself is in previous commits in GitHub repo. See Word doc with assignment for what I learned here.

        packCount = (contestants + 3) / 4;

        return packCount;

    }


    /**
     * The playGame method is the game loop, and it calls various methods to deal cards to each player
     * and manage each turn/score updates.
     */
    private void playGame() {

        dealHands();

        currentPlayer = -1;
        do {
            currentPlayer = (currentPlayer + 1) % contestants; // Make sure you understand how this works.
            displayTurnInfo();
            playCard();

            // Draw a new card to maintain a hand of 5 cards. The game will always end before the deck is empty.
            players[currentPlayer].addCard(myDeck.drawCard());
        } while (gameTotal <= GAME_OVER);

        System.out.println("\nGAME OVER!  Sorry " + players[currentPlayer].getName() + ", but you lost.\n\n");
    }


    /**
     * The dealHands method deals cards to start the game.
     */
    private void dealHands() {

        for (int i = 0; i < contestants; ++i) { 

            for (int j = 0; j < CARDS_IN_HAND; j++){

                players[i].addCard(myDeck.drawCard());

            }

        }

    }

    
    /**
     * The displayTurnInfo method gives a preamble with tallies for the turn. It displaysthe number of cards in the deck, 
     * the current game total, and the player's hand.
     */
    private void displayTurnInfo() {

        System.out.println("\n\nThere are " + myDeck.getCurrentCardCount() + " cards in the deck. The current game total is: " + this.gameTotal);
        System.out.println("\n" + players[currentPlayer].getName() + ", it's your turn. Here is your hand:\n");
        
        for (int i = 0; i < players[currentPlayer].getCurrentCardCount(); i++){

            System.out.print("\t" + (i + 1) + ") " + players[currentPlayer].getCard(i).toString());

        }

    }

   /**
    * The playCard method asks the user which card to play, and then modifies the game total based on the card played.
    */
   private void playCard() {

        int currentSelection = -1;
        int playedCard = -1;

        System.out.print("\n\nEnter the number of the card you wish to play: ");
        currentSelection = input.nextInt();

        while (currentSelection < 1 || currentSelection > players[currentPlayer].getCurrentCardCount()){

            System.out.print("\nThat number is out of range. Try again: ");
            currentSelection = input.nextInt();

        }

        currentSelection--; //Need to stay on 0-based number for the computer, so subtract 1 from what the human entered.

        playedCard = players[currentPlayer].getCard(currentSelection).getRank();

        players[currentPlayer].playCard(currentSelection);

        updateGameTotal(this.gameTotal, playedCard);

   }


   /**
    * The updateGameTotal method adds the card values to the total game point counter.
    * It needs to know what the previous game total was (before playing a card), and it
    * needs to know the last played card (zero-indexed). This method calls helper method
    * checkThresholds to add additional points if a threshold is crossed by either adding
    * or subtracting (the 10 card subtracts...).
    * 
    * @param previousGameTotal the game total before playing a card
    * @param lastPlayed the card most recently played (to add to the game total in this method)
    */
    private void updateGameTotal(int previousGameTotal, int lastPlayed){

        lastPlayed += 1; //Keep in mind inbound lastPlayedValue is 0 indexed, so adjusting here to make code easier to read...
        
        if (lastPlayed < 1){

            System.out.println("Error. Card does not exist."); //Not handling exceptions until we get there in class, but this shouldn't happen.

        }
        
        else if (lastPlayed == 1){
            this.gameTotal += 14; //Ace is worth 14, not 1.
        }
        
        
        else if (lastPlayed > 1 && lastPlayed <= 6){ 

            this.gameTotal = this.gameTotal + lastPlayed;

        }

        else if (lastPlayed == 7){ //Add 7, unless that would exceed 99...in which case it adds just 1.
 
            if (this.gameTotal >= 98){

                this.gameTotal += 1;
                
            }

            else {

                this.gameTotal += 7;

            }

        }

        else if (lastPlayed == 8){

            this.gameTotal = this.gameTotal + lastPlayed;

        }

        else if (lastPlayed == 9){

            this.gameTotal += 0; //Does nothing, but put this here in case rule ever changes...

        }

        else if (lastPlayed == 10){

            this.gameTotal -= 10;

        }

        else if (lastPlayed >= 10){

            this.gameTotal  = this.gameTotal + lastPlayed;

        }

        if (checkThresholds(previousGameTotal, this.gameTotal)){
            System.out.println("\nTHRESHOLD CROSSED! Adding 5 more points...");
            this.gameTotal += 5;
        }
        

    }


    /**
     * The checkThresholds method helps determine whether a threshold was crossed by either
     * adding or subtracting card value. If the threshold is crossed in either direction,
     * a boolean value of true is returned so another method can do something with the info.
     * 
     * 
     * @param previousValue the game value before the play
     * @param currentValue the game value after playing the card in a turn (but not including 5 pt increment)
     * @return true value if a threshold is tripped so that info can be used elsewhere
     */
    private boolean checkThresholds(int previousValue, int currentValue){

        boolean addPoints = false;
        final int THRESHOLD1 = 33;
        final int THRESHOLD2 = 55;
        final int THRESHOLD3 = 77;


        int threshold[] = {THRESHOLD1, THRESHOLD2, THRESHOLD3}; //Perfect array
  
        // if start <= 33 and end >= 34, then flag is tripped... etc
        // if start >= 34 and end <= 33, then flag is tripped... etc

        for (int i = 0; i < threshold.length; i++){

            if ((!addPoints) && (previousValue <= threshold[i]) && (currentValue >= (threshold[i] + 1))){

                addPoints = true;

            }

            if ((!addPoints) && (previousValue >= (threshold[i] + 1)) && (currentValue <= threshold[i])){

                addPoints = true;

            }

         }

         return addPoints;

    }

}
