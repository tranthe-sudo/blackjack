package gameComponent;

import org.json.JSONObject;

public class Dealer extends CardHolder {
    @Override
    public String getType() {
        return "dealer";
    }

    @Override
    public JSONObject constructJsonObject() {
        JSONObject dealer = new JSONObject();
        dealer.put("type", getType());
        dealer.put("cards", cardsToJSONArray());

        return dealer;
    }

    public JSONObject getPlayerJson() {
        return constructJsonObject();
    }
}
