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

        for (int i = 0; i < contestants; i++){

            System.out.println("Player " + (i+1) + ", enter your name: ");
            
            players[i].setName(input.next()); //FIXME - ensure players objects are instantiated correctly.

        }

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

        for (i = 0; i < contestants; ++i) { //Enhanced for loop - Ch.8, page 47
            myDeck.add();
        }

    }

    //TODO: Display the number of cards in the deck, the current game total, and the player's hand.
    private void displayTurnInfo() {

    }

   //TODO: Ask the user which card to play.  Then modify the game total based on the card played.
   private void playCard() {

    }
}
