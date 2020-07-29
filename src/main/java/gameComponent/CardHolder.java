package gameComponent;

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

    public String getPlayerJsonString() {
        return constructJsonObject().toString();
    }

    public abstract String getType();
    public abstract JSONObject constructJsonObject();
}
