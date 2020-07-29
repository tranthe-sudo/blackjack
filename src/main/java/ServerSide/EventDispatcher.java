package ServerSide;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.json.JSONArray;
import org.json.JSONObject;

public final class EventDispatcher {

    public static void dispatch(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {

        int event = request.getInt("event");

        switch(event) {
            case Event.REQUEST_JOIN_TABLE:
                Controller.responseToRequestJoinTable(request, tableManager, ctx);
                break;
            case Event.REQUEST_TO_PLAY:
                break;
            case Event.PLAY_HIT:
                break;
            case Event.PLAY_STAY:
                break;
            default:
                break;
        }

    }
}
