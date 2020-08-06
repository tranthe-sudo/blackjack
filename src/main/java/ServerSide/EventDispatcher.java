package ServerSide;

import io.netty.channel.ChannelHandlerContext;
import org.json.JSONObject;

public final class EventDispatcher {

    public static void dispatch(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {

        int event = request.getInt("event");

        switch(event) {
            case Event.REQUEST_JOIN_TABLE:
                Controller.responseToRequestJoinTable(request, tableManager, ctx);
                break;
            case Event.REQUEST_TO_PLAY:
                Controller.responseToRequestToPlay(request, tableManager, ctx);
                break;
            case Event.PLAY_HIT:
                Controller.responseToHitRequest(request, tableManager, ctx);
                break;
            case Event.PLAY_STAY:
                Controller.responseToStayRequest(request, tableManager, ctx);
                break;
            default:
                break;
        }

    }
}
