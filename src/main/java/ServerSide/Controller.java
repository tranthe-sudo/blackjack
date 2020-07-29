package ServerSide;

import gameComponent.CardHolder;
import gameComponent.Player;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

public class Controller {

    public static void responseToRequestJoinTable(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {
        String playerName = request.getJSONObject("data").getString("name");
        Player player = new Player(ctx.channel(), playerName);
        tableManager.addPlayer(player);


        // Only sent to new player
        JSONObject individualResponse = new JSONObject();
        JSONObject data1 = player.getPlayerJson();
        individualResponse.put("event", Event.JOIN_TABLE_RESPONSE);
        individualResponse.put("data", data1);
        ctx.writeAndFlush(new TextWebSocketFrame(individualResponse.toString()));

        // broadcast to all other players in the Table
        JSONObject broadcastResponse = new JSONObject();
        JSONArray players = new JSONArray();
        for (CardHolder p : tableManager.getListPlayers()) {
            players.put(p.getPlayerJson());
        }
        JSONObject data2 = new JSONObject();
        data2.put("players", players);
        broadcastResponse.put("event", Event.JOIN_TABLE_BROADCAST_RESPONSE);
        broadcastResponse.put("data", data2);
        tableManager.broadcast(broadcastResponse.toString());
    }


    // Helper method

}
