/**
 * Defines a Card
 *
 * @date 7/3/12
 * @author Graham Wright
 */
public class Card {

    private final static String[] names = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
    private int value;

    /**
     * Defines a Card
     *
     * @param val The numerical value of the card "2" is 2, "Jack" is 11, "Ace" is 15
     */
    public Card(int val) {
        value = val;
    }

    /**
     * Returns the numerical value of the card
     *
     * @return value The numerical value of the card
     */
    public int getValue() {
        return value;
    }

    /**
     * The String representation of the Card
     *
     * @return the String representation of this Card
     */
    public String toString() {
        String result = "";
        int temp = getValue();
        result = result + names[temp - 2];
        return result;
    }
}
