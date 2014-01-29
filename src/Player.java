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

    public Player(int number) {
        hand = new ArrayList<Card>();
        playerNumber = number;
    }
    
    public int getSwitch() {
        return switchDeal;
    }
    
    public void changeSwitch() {
        if (switchDeal == 0) switchDeal = 1;
        else switchDeal = 0;
    }

    public int getCardsPlayed() {
        return cardsPlayed;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    public int getHandStrength() {
        return handStrength;
    }
    
    public int getAceCount() {
        return aceCount;
    }
    
    public boolean isSortedLtoH() {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() < hand.get(i + 1).getValue()) return false;
        }
        return true;
    }
    
     public boolean isSortedHtoL() {
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() > hand.get(i + 1).getValue()) return false;
        }
        return true;
    }
    
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
    
    public void updateHandStrength() {
        int count = 0;
        for (int i = 0; i < hand.size(); i++) {
            count = count + hand.get(i).getValue();
            if (hand.get(i).getValue() == 14) aceCount++;
        }
        
        handStrength = count;
    }
    
    public int length() {
        return hand.size();
    }

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
    
    public Card drawCard(Deck d) {
        Card[] temp = d.getCards();
        Card c = temp[51];
        d.removeLastCard();
        return c;
    }

    public void addCard(Card c1) {
        hand.add(0, c1);
    }

    public Card playCard() {
        
        Card lastCard = hand.get(hand.size() - 1);
        hand.remove(hand.size() - 1);
        cardsPlayed++;
        return lastCard;
    }
    
    public void playCard(ArrayList<Card> place) {
        Card lastCard = hand.get(hand.size() - 1);
        hand.remove(hand.size() - 1);
        place.add(lastCard);
        cardsPlayed++;
    }          
    
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
