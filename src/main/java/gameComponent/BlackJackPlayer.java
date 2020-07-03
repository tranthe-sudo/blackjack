package gameComponent;

import io.netty.channel.Channel;

import java.util.LinkedList;

public class BlackJackPlayer {

    private LinkedList<Card> PlayerCards;
    private int playerIndex;
    private String roomName;
    private String playerName;
    private boolean readyToPlay;
    private Channel channel;

    private String playerJson;

    BlackJackPlayer(String playerName, Channel channel) {
        this.playerName = playerName;
        this.channel = channel;
        this.init();
    }

    private void init() {
        PlayerCards = new LinkedList<>();
        this.readyToPlay = false;
    }


    public int getPlayerIndex() {
        return playerIndex;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerJson() {
        return playerJson;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setPlayerJson(String playerJson) {
        this.playerJson = playerJson;
    }

    public void addToHand(Card card) {
        PlayerCards.add(card);
    }
}
