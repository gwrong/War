/**
 * Defines a Player in the game of War
 *
 * @date 7/3/12
 * @author Graham Wright
 */
import java.util.ArrayList;

public class Player {

    private ArrayList<Card> hand;
    private int playerNumber;
    private int cardsPlayed;
    private int handStrength;
    private int aceCount;
    private int switchDeal;

    /**
     * The player constructor with the number of player
     */
    public Player(int number) {
        hand = new ArrayList<Card>();
        playerNumber = number;
    }
    
    /**
     * Gets the flag value for switching the returning order of cards
     * for this player
     */
    public int getSwitch() {
        return switchDeal;
    }
    
    /**
     * Flips the flag value for this player
     */
    public void changeSwitch() {
        if (switchDeal == 0) switchDeal = 1;
        else switchDeal = 0;
    }

    /**
     * Returns the count of the number of cards played
     *
     * @return The number of cards played by this player
     */
    public int getCardsPlayed() {
        return cardsPlayed;
    }
    
    /**
     * Returns the number of this player
     *
     * @return playerNumber
     */
    public int getPlayerNumber() {
        return playerNumber;
    }

    /**
     * Returns the numerical hand strength
     *
     * @return value The numerical strength of the player's hand
     */
    public int getHandStrength() {
        return handStrength;
    }
    
    /**
     * Returns the number of aces in the player's hand
     *
     * @return The number of aces in the player's hand
     */
    public int getAceCount() {
        return aceCount;
    }

    /**
     * Decides if hand is sorted low to high (from index 0 to index size - 1)
     * index 0 is the bottom of the player's hand
     * 
     * @return boolean true/false
     */
    public boolean isSortedLtoH() {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() < hand.get(i + 1).getValue()) return false;
        }
        return true;
    }
    
    /**
     * Decides if hand is sorted low to high (from index 0 to index size - 1)
     * 
     * @return boolean true/false
     */
     public boolean isSortedHtoL() {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() > hand.get(i + 1).getValue()) return false;
        }
        return true;
    }
    
     /**
      * Decides if hand is sorted low to high (from index 0 to index size - 1)
      * 
      * @return boolean true/false
      */
    public void sortCardsLtoH() {
        int start = 0;
        while (!isSortedLtoH()) {
            int highest = hand.get(start).getValue();
            int highestj = start;
        
            for (int i = start + 1; i < hand.size(); i++) {
                if (hand.get(i).getValue() > highest) {
                    highestj = i;
                    highest = hand.get(i).getValue();
                }
            }
            
            Card high = hand.get(highestj);
            Card notHigh = hand.get(start);
            hand.remove(start);
            hand.add(start, high);
            hand.remove(highestj);
            hand.add(highestj, notHigh);
            start++;
        }
    }
    
    /**
     * Decides if hand is sorted high to low (from index 0 to index size - 1)
     * 
     * @return boolean true/false
     */
    public void sortCardsHtoL() {
        
        int start = 0;
        while (!isSortedHtoL()) {
            int lowest = hand.get(start).getValue();
            int lowestj = start;
        
            for (int i = start + 1; i < hand.size(); i++) {
                if (hand.get(i).getValue() < lowest) {
                    lowestj = i;
                    lowest = hand.get(i).getValue();
                }
            }
            
            Card low = hand.get(lowestj);
            Card notLow = hand.get(start);
            hand.remove(start);
            hand.add(start, low);
            hand.remove(lowestj);
            hand.add(lowestj, notLow);
            start++;
        }
    }

    /**
     * Decides if hand is alternating highest value card, lowest value card,
     * highest value card, etc. from the bottom of the player's hand (from index 0)
     * 
     * @return boolean true/false
     */
    public void sortCardsAltHLBottom() {

        int start = 0;
        for (int j = 0; j < 13; j++) {
                int lowest = hand.get(start).getValue();
                int lowestj = start;

                for (int i = start + 1; i < hand.size(); i++) {
                    if (hand.get(i).getValue() < lowest) {
                        lowestj = i;
                        lowest = hand.get(i).getValue();
                    }
                }

                Card low = hand.get(lowestj);
                Card notLow = hand.get(start);
                hand.remove(start);
                hand.add(start, low);
                hand.remove(lowestj);
                hand.add(lowestj, notLow);
                start++;

                int highest = hand.get(start).getValue();
                int highestj = start;

                for (int i = start + 1; i < hand.size(); i++) {
                    if (hand.get(i).getValue() > highest) {
                        highestj = i;
                        highest = hand.get(i).getValue();
                    }
                }

                Card high = hand.get(highestj);
                Card notHigh = hand.get(start);
                hand.remove(start);
                hand.add(start, high);
                hand.remove(highestj);
                hand.add(highestj, notHigh);
                start++;
        }
    }
    
    /**
     * Decides if hand is alternating lowest value card, highest value card,
     * lowest value card, etc. from the bottom of the player's hand (from index 0)
     * 
     * @return boolean true/false
     */
    public void sortCardsAltLHBottom() {
  
        int start = 0;
        for (int j = 0; j < 13; j++) {
                int highest = hand.get(start).getValue();
                int highestj = start;

                for (int i = start + 1; i < hand.size(); i++) {
                    if (hand.get(i).getValue() > highest) {
                        highestj = i;
                        highest = hand.get(i).getValue();
                    }
                }

                Card high = hand.get(highestj);
                Card notHigh = hand.get(start);
                hand.remove(start);
                hand.add(start, high);
                hand.remove(highestj);
                hand.add(highestj, notHigh);
                start++;


                int lowest = hand.get(start).getValue();
                int lowestj = start;

                for (int i = start + 1; i < hand.size(); i++) {
                    if (hand.get(i).getValue() < lowest) {
                        lowestj = i;
                        lowest = hand.get(i).getValue();
                    }
                }

                Card low = hand.get(lowestj);
                Card notLow = hand.get(start);
                hand.remove(start);
                hand.add(start, low);
                hand.remove(lowestj);
                hand.add(lowestj, notLow);
                start++;
        }              
    }
    
    /**
     * Decides if hand is alternating highest value card, lowest value card,
     * highest value card, etc. from the TOP of the player's hand
     * (index hand.size() - 1) and down
     * 
     * @return boolean true/false
     */
    public void sortCardsAltHLTop() {
  
        int start = hand.size() - 1;
        for (int j = 0; j < 13; j++) {
                
    
                int highest = hand.get(start).getValue();
                int highestj = start;

                for (int i = start - 1; i > -1; i--) {
                    if (hand.get(i).getValue() > highest) {
                        highestj = i;
                        highest = hand.get(i).getValue();
                    }
                }

                Card high = hand.get(highestj);
                Card notHigh = hand.get(start);
                hand.remove(start);
                hand.add(start, high);
                hand.remove(highestj);
                hand.add(highestj, notHigh);
                start--;
                
                int lowest = hand.get(start).getValue();
                int lowestj = start;

                for (int i = start - 1; i > -1; i--) {
                    if (hand.get(i).getValue() < lowest) {
                        lowestj = i;
                        lowest = hand.get(i).getValue();
                    }
                }

                Card low = hand.get(lowestj);
                Card notLow = hand.get(start);
                hand.remove(start);
                hand.add(start, low);
                hand.remove(lowestj);
                hand.add(lowestj, notLow);
                start--;
        }              
    }

    /**
     * Decides if hand is alternating lowest value card, highest value card,
     * lowest value card, etc. from the TOP of the player's hand
     * (index hand.size() - 1) and down
     * 
     * @return boolean true/false
     */
    public void sortCardsAltLHTop() {

        int start = hand.size() - 1;
        for (int j = 0; j < 13; j++) {

                int lowest = hand.get(start).getValue();
                int lowestj = start;

                for (int i = start - 1; i > -1; i--) {
                    if (hand.get(i).getValue() < lowest) {
                        lowestj = i;
                        lowest = hand.get(i).getValue();
                    }
                }

                Card low = hand.get(lowestj);
                Card notLow = hand.get(start);
                hand.remove(start);
                hand.add(start, low);
                hand.remove(lowestj);
                hand.add(lowestj, notLow);
                start--;


                int highest = hand.get(start).getValue();
                int highestj = start;

                for (int i = start - 1; i > -1; i--) {
                    if (hand.get(i).getValue() > highest) {
                        highestj = i;
                        highest = hand.get(i).getValue();
                    }
                }

                Card high = hand.get(highestj);
                Card notHigh = hand.get(start);
                hand.remove(start);
                hand.add(start, high);
                hand.remove(highestj);
                hand.add(highestj, notHigh);
                start--;
        }
    }
    
    /**
     * Computes the hand strength and ace count of the player's hand
     */
    public void updateHandStrength() {
        int count = 0;
        for (int i = 0; i < hand.size(); i++) {
            count = count + hand.get(i).getValue();
            if (hand.get(i).getValue() == 14) aceCount++;
        }
        
        handStrength = count;
    }
    
    /**
     * Gets the length of the hand
     * 
     * @return hand size
     */
    public int length() {
        return hand.size();
    }

    /**
     * Burns cards from the player's hand into the discard pile
     * 
     * @param discPile The discard pile for the War game
     */
    public void burnCards(ArrayList<Card> discPile) {
        
        if (hand.size() == 0) return;
        
        else if (hand.size() == 1) {
            Card c1 = hand.get(hand.size() - 1);         
            hand.remove(hand.size() - 1);
            cardsPlayed++;
            discPile.add(c1);
        }        
              
        else {
            Card c1 = hand.get(hand.size() - 1);
            Card c2 = hand.get(hand.size() - 2);
            
            hand.remove(hand.size() - 1);
            cardsPlayed++;
            hand.remove(hand.size() - 1);
            cardsPlayed++;
            
            discPile.add(c1); discPile.add(c2);
        }
        
    }

    /**
     * Takes the top card from the deck
     * 
     * @param d The deck for the game
     * @return Card The card from the deck
     */
    public Card drawCard(Deck d) {
        Card[] temp = d.getCards();
        Card c = temp[51];
        d.removeLastCard();
        return c;
    }

    /**
     * Adds the card c1 to the player's hand
     * 
     * @param c1 the card to be added
     */
    public void addCard(Card c1) {
        hand.add(0, c1);
    }

    /**
     * Plays the card at the top of the player's hand
     * 
     * @return The card being played
     */
    public Card playCard() {
        
        Card lastCard = hand.get(hand.size() - 1);
        hand.remove(hand.size() - 1);
        cardsPlayed++;
        return lastCard;
    }

    /**
     * Plays the card at the top of the player's hand into place
     * 
     * @param place The place to put the played card
     */
    public void playCard(ArrayList<Card> place) {
        Card lastCard = hand.get(hand.size() - 1);
        hand.remove(hand.size() - 1);
        place.add(lastCard);
        cardsPlayed++;
    }          
    
    @Override
    public String toString() {
        String result = "[";
        if (hand.isEmpty()) {
            result = result + "]";
        }
        
        for (int i = 0; i < hand.size(); i++) {
            result = result + hand.get(i).toString();
            if (i + 1 >= hand.size()) result = result + "]";
            else result = result + ", ";
        }
        
        result = result + "\nSize: " + length(); 
        return result;
    }  
}
