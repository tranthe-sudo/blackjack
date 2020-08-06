package ServerSide;

import gameComponent.CardHolder;
import gameComponent.Player;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.json.JSONArray;
import org.json.JSONObject;



public class Controller {

    public static void responseToRequestJoinTable(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {

        if ( tableManager.getNumberOfPlayers() == 4 || (tableManager.getLockStatus() == true) ) {
            JSONObject msg = new JSONObject().put("event", Event.REQUEST_DENIED);
            ctx.writeAndFlush(new TextWebSocketFrame(msg.toString()));
        } else {
            JSONObject msg = new JSONObject().put("event", Event.REQUEST_ACCEPTED);
            ctx.writeAndFlush(new TextWebSocketFrame(msg.toString()));

            // Add new player to list
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
            updateBroadcast(Event.JOIN_TABLE_BROADCAST_RESPONSE, tableManager);
        }
    }



    /**
     * the request Json message will look like this
     * {
     *     event: ...
     *     data: {
     *         id: ...
     *     }
     * }
     *
     * */
    public static void responseToRequestToPlay(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {

        // get playerID who sent the request
        if ( tableManager.getLockStatus() == false ) {
            String id = request.getJSONObject("data").getString("id");

            for (CardHolder player : tableManager.getListPlayers()) {
                if ( player instanceof Player ) {
                    if (  ((Player) player).getPlayerID().equals(id) ) {
                        ((Player) player).ready();
                    }

                }
            }

            // Check if all players are ready
            // if true start game
            // if not do nothing wait until everyone is ready
            if ( tableManager.isAllReady() ) {
                // Do not allow user to press the ready button
                tableManager.lock();


                // start game
                tableManager.initGame();
                tableManager.dealCard();

                updateBroadcast(Event.UPDATE_TABLE_STATE_BROADCAST_RESPONSE, tableManager);
            }
        }
    }

    public static void responseToHitRequest(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {
        tableManager.hitFromPlayer();
        updateBroadcast(Event.UPDATE_TABLE_STATE_BROADCAST_RESPONSE, tableManager);

    }

    public static void responseToStayRequest(JSONObject request, TableManager tableManager, ChannelHandlerContext ctx) {
        tableManager.stayFromPlayer();
        updateBroadcast(Event.UPDATE_TABLE_STATE_BROADCAST_RESPONSE, tableManager);
    }


    private static void updateBroadcast(int evtType, TableManager tableManager) {
        JSONObject broadcastResponse = new JSONObject();

        JSONArray playersArrayJSON = new JSONArray();
        for (CardHolder p : tableManager.getListPlayers()) {
            playersArrayJSON.put(p.getPlayerJson());
        }

        JSONObject data = new JSONObject();
        data.put("players", playersArrayJSON);
        broadcastResponse.put("event", evtType);
        broadcastResponse.put("data", data);

        tableManager.broadcast(broadcastResponse.toString());
    }
}


