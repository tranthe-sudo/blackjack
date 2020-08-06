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
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private final TableManager tableManager;

    TextWebSocketFrameHandler(TableManager tableManager) {
        this.tableManager = tableManager;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if ( evt instanceof WebSocketServerProtocolHandler.HandshakeComplete) {
            ctx.pipeline().remove(HttpRequestHandler.class);
            //group.writeAndFlush(new TextWebSocketFrame(
            //        "Client " + ctx.channel() + " joined"
            //));
            //group.add(ctx.channel());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //group.writeAndFlush(msg.retain());

        msg.retain();
        // Get json request
        JSONObject request = requestParser(msg);

        //logger.atInfo().log(ctx.channel().id() + " has sent " + request.toString(4));

        try {
//            if ( request.getInt("event") == Event.INITIAL_REQUEST ) {
//                EventDispatcher.responseToInitialRequest(tableManager, ctx); // only this need reference to ctx
//            } else if ( request.getInt("event") == Event.JOIN_ROOM ) {
//                EventDispatcher.responseToJoinRoom(tableManager, request.getJSONObject("data"));
//            } else if ( request.getInt("event") == Event.READY_TO_PLAY ) {
//                EventDispatcher.responseToReadyToPlay(tableManager, request.getJSONObject("data"));
//            } else if ( request.getInt("event") == Event.PLAY ) {
//                EventDispatcher.responseToPlay(tableManager, request.getJSONObject("data"));
//            }
            EventDispatcher.dispatch(request, tableManager, ctx);

        } finally {
            msg.release();
        }
    }

    // Remove player out of List Players when the channel become inActive
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //super.channelInactive(ctx);
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

