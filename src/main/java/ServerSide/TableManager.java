package ServerSide;

import gameComponent.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;
import java.util.LinkedList;


public class TableManager {
    BlackJackGame game = new BlackJackGame(this);


    private ChannelGroup channelGroup;
    private boolean isLock;
    volatile private LinkedList<CardHolder> listPlayers = new LinkedList<>();



    TableManager() {
        this.channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
        Dealer dealer = new Dealer();
        this.listPlayers.addLast(dealer);
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
