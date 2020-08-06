package gameComponent;

public class Card {

    private int value;
    private Suit suit;
    private Rank rank;

    public Card (Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = rank.getValue();
    }

    public int getValue() {
        return value;
    }

    public boolean isAce() {
        if ( rank == Rank.Ace) {
            return true;
        }
        return false;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank.toString() + "_of_" + suit.toString();
    }
}
