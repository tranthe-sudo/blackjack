package gameComponent;

import java.util.LinkedList;


public class Hand {
    private LinkedList<Card> hand;

    public Hand() {
        hand = new LinkedList<>();
    }

    public int getPoint() {
        return calculatePoint();
    }

    public void addToHand(Card card) {
        if ( card.isAce() ) {
            hand.addLast(card);
        } else {
            hand.addFirst(card);
        }
    }

    private int calculatePoint() {
        int point = 0;

        for (Card c : hand) {
            point += c.getValue();
            if ( c.isAce() ) {
                if ( point > 21 ) {
                    point -= 10;
                }
            }
        }

        return point;
    }

    public LinkedList<Card> getListCards() {
        return this.hand;
    }

    public void flush() {
        hand.clear();
    }
}
