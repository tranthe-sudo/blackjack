package ServerSide;

public class Event {

    // Request from clients to server
    public final static int REQUEST_JOIN_TABLE                        = 1;
    public final static int REQUEST_TO_PLAY                           = 2;
    public final static int PLAY_HIT                                  = 3;
    public final static int PLAY_STAY                                 = 4;
    public final static int JOIN_TABLE_RESPONSE                       = 5;    // Done
    public final static int JOIN_TABLE_BROADCAST_RESPONSE             = 6;    // Done
    public final static int REQUEST_ACCEPTED                          = 7;    // Done
    public final static int REQUEST_DENIED                            = 8;    // Done
    public final static int UPDATE_TABLE_STATE_BROADCAST_RESPONSE     = 9;

}
