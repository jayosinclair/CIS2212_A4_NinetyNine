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

public class Player{

    private String name;
    private ArrayList<Card> hand = new ArrayList<Card>();



    public void setName(String playerName){

        this.name = playerName;

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



