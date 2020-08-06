package gameComponent;

import org.json.JSONObject;

public class Dealer extends CardHolder {
    private boolean reveal;

    public boolean isReveal() {
        return reveal;
    }

    public void setReveal(boolean reveal) {
        this.reveal = reveal;
    }

    public Dealer() {
        this.reveal = false;
    }

    @Override
    public String getType() {
        return "dealer";
    }

    @Override
    public JSONObject constructJsonObject() {
        JSONObject dealer = new JSONObject();
        dealer.put("type", getType());
        dealer.put("cards", cardsToJSONArray());
        dealer.put("reveal", this.isReveal());

        return dealer;
    }

    public JSONObject getPlayerJson() {
        return constructJsonObject();
    }
}
