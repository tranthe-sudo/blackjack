package ServerSide;

import gameComponent.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;


import java.util.LinkedList;
import java.util.ResourceBundle;

public class TableManager {
    // Get configuration
    ResourceBundle configuration = ResourceBundle.getBundle("configuration");
    private int maxPlayers = Integer.valueOf(configuration.getString("maximum_players_per_room"));

    // Reference to Blackjack game controller
    Game game = new Game(this);


    private ChannelGroup channelGroup;
    private boolean isLock;
    private boolean firstRound;
    volatile private LinkedList<CardHolder> listPlayers = new LinkedList<>();



    TableManager() {
        this.channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        Dealer dealer = new Dealer();
        this.listPlayers.addLast(dealer);
        this.firstRound = true;
    }

    public boolean isFirstRound() {
        return this.firstRound;
    }

    public void setFirstRound(boolean value) {
        this.firstRound = false;
    }

    public void lock() {
        this.isLock = true;
    }

    public void unlock() {
        this.isLock = false;
    }

    public boolean getLockStatus() {
        return this.isLock;
    }

    public LinkedList<CardHolder> getListPlayers () { return listPlayers; }

    // Need update
    public void addPlayer(Player player) {
        this.channelGroup.add(player.getChannel());
        this.listPlayers.addFirst(player);
    }

    public void removePlayer(Player player) {
        listPlayers.remove(player);
    }



    // - Add game logic here --------------
    public void startNewRound() {
        startNewRound();
    }

    public void initGame() {
        game.init();
    }

    public void hitFromPlayer() {
        game.playHit();
    }

    public void stayFromPlayer() {
        game.playStay();
    }

    public void dealCard() {
        game.dealCard();
    }




    // - End game logic ---------------


    public void broadcast (String message) {
        this.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }


    public int getNumberOfPlayers () {
        return listPlayers.size();
    }

    public boolean isAllReady() {
        for (CardHolder player: listPlayers) {
            if ( player instanceof Player ) {
                if ( !((Player) player).isReady() ) {
                    return false;
                }
            }
        }

        return true;
    }
}
