package gameComponent;

import io.netty.channel.Channel;
import org.json.JSONObject;


public class Player extends CardHolder {
    private String name;
    boolean readyToPlay;
    String playerID;
    private boolean current;

    private int state;
    private Channel channel;

    public Player(Channel channel, String name) {
        this.channel = channel;
        this.playerID = channel.id().asLongText();
        this.name = name;

        this.state = State.NULL;
        this.readyToPlay = false;
        this.current = false;
    }

    @Override
    public String getType() {
        return "player";
    }

    public boolean isReady() { return readyToPlay; }
    public void ready() { this.readyToPlay = true; };
    public void setReadyToPlay(boolean value) {
        this.readyToPlay = value;
    }

    public void setCurrent(boolean value) { current = value; }

    public boolean getCurrent () { return current; }

    public String getPlayerID() { return this.playerID; }

    public Channel getChannel() { return channel; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public void reset() {
        super.reset();
        this.current = false;

    }

    @Override
    public JSONObject constructJsonObject () {
        JSONObject player = new JSONObject();
        player.put("type", getType());
        player.put("id", this.playerID);
        player.put("name", this.name);
        player.put("isCurrent", this.getCurrent());
        player.put("state", this.getState());
        player.put("isReady", this.readyToPlay);
        player.put("cards", this.cardsToJSONArray());

        return  player;
    }

}
