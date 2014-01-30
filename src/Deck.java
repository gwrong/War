import java.util.ArrayList;

/**
 * Defines a Deck of cards
 *
 * @date 7/3/12
 * @author Graham Wright
 */
public class Deck {

    private Card[] cards;
    private int lastCard;

    /**
     * Constructs a deck of cards in random order
     */
    public Deck() {
        lastCard = 0;
        cards = new Card[52];
        Card[] randoms = new Card[52];
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card c = new Card(j);
                int temp = (int) (Math.random() * 52);
                while (randoms[temp] != null) {
                    temp = (int) (Math.random() * 52);
                }
                randoms[temp] = c;
            }
        }
        for (int i = 0; i < 52; i++) {
            int temp = (int) (Math.random() * 52);
            while (randoms[temp] == null) {
                temp = (int) (Math.random() * 52);
            }
            Card c1 = randoms[temp];
            randoms[temp] = null;
            cards[i] = c1;
        }
    }

    /**
     * Returns the size of the deck
     *
     * @return The number of cards in the deck
     */
    public int length() {
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) count++;
        }
        return count;
    }
    
    /**
     * Returns the numerical value of the card
     *
     * @return value The numerical value of the card
     */
    public void dealCards(ArrayList<Player> players) {
        while (length() != 0) {
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i) != null) {
                    int temp = (int) (Math.random() * 52);

                    while (cards[temp] == null) {
                        temp = (int) (Math.random() * 52);
                    }
                    Card c = cards[temp];
                    cards[temp] = null;
                    players.get(i).addCard(c);            
                }           
            }       
        }
    }
    
    /**
     * Removes the last card in the deck
     */
    public void removeLastCard() {
        cards[51] = null;
    }

    @Override
    public String toString() {
        String result = "";
        if (cards.length > 0) result = result + "[";
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                result = result + cards[i].toString();
                if (i + 1 >= cards.length) result = result + "]";
                else result = result + ", ";
            }
            
        }
        return result;
    }

    /**
     * Returns the deck in an array
     *
     * @return cards the backing array
     */
    public Card[] getCards() {
        return cards;
    }

}
