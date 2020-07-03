package ServerSide;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RoomManager {
    private ConcurrentMap<Integer, Room> listRooms = new ConcurrentHashMap<>(10);

    RoomManager() {
        init();
    }

    private void init() {
        listRooms.put(1, new Room("room_1"));
        listRooms.put(2, new Room("room_2"));
        listRooms.put(3, new Room("room_3"));
        listRooms.put(4, new Room("room_4"));
        listRooms.put(5, new Room("room_5"));
        listRooms.put(6, new Room("room_6"));
        listRooms.put(7, new Room("room_7"));
        listRooms.put(8, new Room("room_8"));
        listRooms.put(9, new Room("room_9"));
        listRooms.put(10, new Room("room_10"));
    }

    public ConcurrentMap<Integer, Room> getListRooms() {
        return listRooms;
    }

    public Room getRoomByIndex(int index) {
        return listRooms.get(index);
    }
}
