package gameComponent;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import org.json.JSONObject;


public class Player extends CardHolder {
    private String name;
    boolean readyToPlay;
    ChannelId playerID;
    private boolean current;

    private int state;
    private Channel channel;

    public Player(Channel channel, String name) {
        this.channel = channel;
        this.playerID = channel.id();
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

    public void setCurrent(boolean value) { current = value; }

    public boolean getCurrent () { return current; }

    public ChannelId getPlayerID() { return playerID; }

    public Channel getChannel() { return channel; }

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public void reset() {
        super.reset();
        // set ready to play to false. This require user to
        // press the ready button to start a new round
        this.readyToPlay = false;
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

        return  player;
    }

}
