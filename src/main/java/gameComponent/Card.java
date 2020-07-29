package gameComponent;

public class Card {

    private int value;
    private SUIT suit;
    private RANK rank;

    Card (SUIT suit, RANK rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        if ( rank == RANK.Ace) {
            return true;
        }
        return false;
    }

    public RANK getRank() {
        return rank;
    }

    @Override
    public String toString() {
        // e.g seven_of_heart
        return rank.toString() + "_of_" + suit.toString();
    }
}
