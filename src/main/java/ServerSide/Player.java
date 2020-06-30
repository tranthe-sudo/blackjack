package ServerSide;

import io.netty.channel.Channel;

import java.util.LinkedList;

public class Player {

    private LinkedList<String> PlayerCards;
    private int playerID;
    private int roomID;
    private String playerName;
    private int Event;
    private Channel channel;

    private String playerJson;
    private int activePlayerID;





    public Channel getChannel() {
        return channel;
    }

    public int getID() {
        return playerID;
    }
}
