package ServerSide;


import com.google.common.flogger.FluentLogger;
import gameComponent.CardHolder;
import gameComponent.Player;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.json.JSONObject;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    private final TableManager tableManager;

    TextWebSocketFrameHandler(TableManager tableManager) {
        this.tableManager = tableManager;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if ( evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            ctx.pipeline().remove(HttpRequestHandler.class);
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        msg.retain();
        // Get json request
        JSONObject request = requestParser(msg);

        try {
            EventDispatcher.dispatch(request, tableManager, ctx);

        } finally {
            msg.release();
        }
    }

    // Remove player out of List Players when the channel become inActive
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        for (CardHolder player : this.tableManager.getListPlayers()) {
            if (player instanceof Player) {
               if ( ((Player) player).getChannel().id() == ctx.channel().id()) {
                   this.tableManager.removePlayer((Player) player);
               }
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

    }

    private JSONObject requestParser(TextWebSocketFrame msg) {
        return new JSONObject(msg.text());
    }
}

/**
 * Our Json message is constructed as followed
 * {
 *     "event" : int
 *     "data" : {
 *         ...
 *     }
 * }*/

