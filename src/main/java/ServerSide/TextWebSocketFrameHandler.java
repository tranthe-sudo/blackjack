package ServerSide;


import com.google.common.flogger.FluentLogger;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import org.json.JSONObject;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private final RoomManager roomManager;

    TextWebSocketFrameHandler(RoomManager roomManager) {
        this.roomManager = roomManager;
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

        logger.atInfo().log(ctx.channel() + " send " + request.toString(4));

        try {
            if ( request.getInt("event") == Event.INITIAL_REQUEST ) {
                EventHandler.responseToInitialRequest(roomManager, request.getJSONObject("data"));
            } else if ( request.getInt("event") == Event.JOIN_ROOM ) {
                EventHandler.responseToJoinRoom(roomManager, request.getJSONObject("data"));
            } else if ( request.getInt("event") == Event.READY_TO_PLAY ) {
                EventHandler.responseToReadyToPlay(roomManager, request.getJSONObject("data"));
            } else if ( request.getInt("event") == Event.PLAY ) {
                EventHandler.responseToPlay(roomManager, request.getJSONObject("data"));
            }

        } finally {
            msg.release();
        }
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

