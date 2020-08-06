package gameComponent;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class CardHolder {
    private Hand hand = new Hand();

    public void reset () {
        hand.flush();
    }

    public void getDealt(Card card) {
            hand.addToHand(card);
    }

    public int getTotalPoint() {
        return hand.getPoint();
    }

    public JSONObject getPlayerJson() {
        return constructJsonObject();
    }

    public JSONArray cardsToJSONArray() {
        String[] arrOfStringCards = new String[hand.getListCards().size()];
        int index = 0;

        for ( Card card : hand.getListCards() ) {
            arrOfStringCards[index] = card.toString();
            index++;
        }

        return new JSONArray(arrOfStringCards);
    }

    public abstract String getType();
    public abstract JSONObject constructJsonObject();
}
