/*

The NinetyNineGame class manages the overall gameplay. To complete the game, follow
the “TODO” instructions within the NinetyNineGame.java file located in the starter
project folder.

*/




import java.util.Scanner;

public class NinetyNineGame {
    private Scanner input;

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

    public void go() {
        displayGreeting();
        getNames();

        //TODO: Create a Deck of Cards using the number of packs based on the number of players.

        myDeck = new Deck(calcPackCount());
        
        playGame();
    }

    private void displayGreeting() {
        String msg = "Greetings!  This is the card game Ninety-Nine.  Here are the rules.\n\n" 
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

    //TODO: Ask how many people are playing, then get the names and create the Player objects.
    private void getNames() {

        System.out.println("How many people are playing? " );
        contestants = input.nextInt();

        players = new Player[contestants];

        for (int i = 0; i < contestants; i++){

            System.out.println("Player " + (i+1) + ", enter your name: ");
            
            players[i] = new Player(input.next());

        }

    }


    private int calcPackCount(){

        //For every 4 players, add a deck

        int packCount = -1;

        if (contestants < 2){ //Need at least two players to play.

            return packCount;

        }

        else if (contestants <= 4){

            packCount = 1;

        }


        else {

            packCount = (contestants / 4) + 1;            

        }

        System.out.println("Pack count: " + packCount);

        return packCount;

    }

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

        System.out.println("\nGAME OVER!  Sorry " + players[currentPlayer].getName() + ", but you lost.");
    }

    //TODO: Deal the cards to start the game.
    private void dealHands() {

        for (int i = 0; i < contestants; ++i) { 

            for (int j = 0; j < CARDS_IN_HAND; j++){

                players[i].addCard(myDeck.drawCard());

            }

        }

    }

    //TODO: Display the number of cards in the deck, the current game total, and the player's hand.
    private void displayTurnInfo() {

        System.out.println("There are " + myDeck.getCurrentCardCount() + " cards in the deck. The current game total is: " + gameTotal);
        System.out.println(players[currentPlayer].getName() + ", it's your turn. Here is your hand:\n");
        
        for (int i = 0; i < players[currentPlayer].getCurrentCardCount(); i++){

            System.out.print("\t" + (i + 1) + ") " + players[currentPlayer].getCard(i).toString());

        }

    }

   //TODO: Ask the user which card to play.  Then modify the game total based on the card played.
   private void playCard() {

        int currentSelection = -1;

        System.out.println("\nEnter the number of the card you wish to play: ");
        currentSelection = input.nextInt();

        while (currentSelection < 1 || currentSelection > players[currentPlayer].getCurrentCardCount()){

            System.out.println("That number is out of range. Try again: ");
            currentSelection = input.nextInt();

        }

        currentSelection--; //Need to stay on 0-based number for the computer, so subtract 1 from what the human entered.

        System.out.println("You played: " + (currentSelection + 1) + ", " + players[currentPlayer].getCard(currentSelection).toString());

        players[currentPlayer].playCard(currentSelection);

        /*System.out.println("\nRemaining Cards: \n");

        for (int i = 0; i < players[currentPlayer].getCurrentCardCount(); i++){

            System.out.println(players[currentPlayer].getCard(i).toString());

        }

        */

        players[currentPlayer].addCard(myDeck.drawCard());

        updateGameTotal(currentSelection);


   }


    private void updateGameTotal(int lastPlayedValue){

        if (lastPlayedValue <= 5){

            this.gameTotal += lastPlayedValue + 1;

        }

        else if (lastPlayedValue == 6){

            if (this.gameTotal == 99){

                this.gameTotal += 1;
                
            }

            else {

                this.gameTotal += 7;

            }

        }

        else if (lastPlayedValue == 7){

            this.gameTotal += (lastPlayedValue + 1);

        }

        else if (lastPlayedValue == 8){

            this.gameTotal += 0;

        }

        else if (lastPlayedValue == 9){

            this.gameTotal -= 10;

        }

        else if (lastPlayedValue >= 10){

            this.gameTotal  += lastPlayedValue;

        }

    }

}
