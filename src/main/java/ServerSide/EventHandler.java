package ServerSide;

import org.json.JSONObject;

public final class EventHandler {
    public static void responseToInitialRequest(RoomManager roomManager, JSONObject data) {}
    public static void responseToJoinRoom(RoomManager roomManager, JSONObject data) {}
    public static void responseToReadyToPlay(RoomManager roomManager, JSONObject data) {}
    public static void responseToPlay(RoomManager roomManager, JSONObject data) {}
}
