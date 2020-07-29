package gameComponent;

import java.util.LinkedList;


public class Hand {
    private int point;
    private LinkedList<Card> hand;

    Hand() {
        point = 0;
        hand = new LinkedList<>();
    }

    public int getPoint() {
        calculatePoint();
        return point;
    }

    public void addToHand(Card card) {
        if ( card.isAce() ) {
            hand.addLast(card);
        }
        hand.addFirst(card);
    }

    private void calculatePoint() {
        for (Card c : hand) {
            point += c.getValue();
            if ( c.isAce() ) {
                if ( point > 21 ) {
                    point -= 10;
                }
            }
        }
    }

    public void flush() {
        hand.clear();
    }
}
