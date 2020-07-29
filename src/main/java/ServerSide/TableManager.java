package ServerSide;

import gameComponent.*;
import io.netty.channel.ChannelHandlerContext;
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
    Game game = new Game();

    private ChannelGroup channelGroup;
    private boolean isLock;
    private LinkedList<CardHolder> listPlayers = new LinkedList<>();



    TableManager() {
        this.channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
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
    public void gameInit() {
        game.init(this);
    }

    public void playerPlayHit() {
        game.playHit();
    }

    public void playerPlayStay() {
        game.playStay();
    }




    // - End game logic ---------------


    public void broadcast (String message) {
        this.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

//    public void sendToOneClient(ChannelHandlerContext ctx, String message) {
////        ctx.writeAndFlush(new TextWebSocketFrame(message));
////    }

    public int getNumberOfPlayers () {
        return listPlayers.size();
    }
}
