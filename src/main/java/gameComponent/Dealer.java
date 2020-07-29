package gameComponent;

import org.json.JSONObject;

public class Dealer extends CardHolder {
    @Override
    public String getType() {
        return "dealer";
    }

    @Override
    public JSONObject constructJsonObject() {
        JSONObject player = new JSONObject();
        player.put("type", getType());

        return  player;
    }

    public JSONObject getPlayerJson() {
        return constructJsonObject();
    }
}
