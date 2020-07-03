package ServerSide;

import gameComponent.BlackJackPlayer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.ImmediateEventExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Room {
    ResourceBundle configuration = ResourceBundle.getBundle("configuration");
    private int maxPlayers = Integer.valueOf(configuration.getString("maximum_players_per_room"));

    private String name;
    private ChannelGroup channelGroup;
    private boolean isLock;
    private volatile List<BlackJackPlayer> listPlayers = new ArrayList<>();

    Room (String name) {
        this.name = name;
        this.channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public String getName() {
        return name;
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

    public void addPlayer(BlackJackPlayer player) {
        player.setPlayerIndex(this.listPlayers.size());
        this.channelGroup.add(player.getChannel());
        this.listPlayers.add(player);
    }

    public void broadcast (String message) {
        this.channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }

    public void sendToOneClient(ChannelHandlerContext ctx, String message) {
        ctx.writeAndFlush(new TextWebSocketFrame(message));
    }

    public int getNumberOfPlayers () {
        return listPlayers.size();
    }
}
