package ServerSide;

import gameComponent.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;


public class Game {

    private int currentIndex;

    private LinkedList<CardHolder> listPlayers;
    private Dealer dealer;
    private Deck deck;

    private Timer timer;
    private TableManager tableManager;

    public Game(TableManager tableManager) {
        this.timer = new Timer();
        this.tableManager = tableManager;
    }

    public void init() {
        listPlayers = tableManager.getListPlayers();
        currentIndex = 0;
        ((Player) listPlayers.get(currentIndex)).setCurrent(true);
        deck = new Deck();

        for (CardHolder c : listPlayers) {
            if ( c.getType() == "dealer") {
                dealer = (Dealer) c;
            }
        }

    }

    public void startNewRound() {
        deck.resetDeck();
        for ( CardHolder player : listPlayers) {
            player.reset();
        }

        this.currentIndex = 0;
        ((Player) listPlayers.get(currentIndex)).setCurrent(true);

        for (CardHolder player : listPlayers) {
            if ( player.getType() == "player") {
                ((Player) player).setState(State.NULL);
            }
        }

        dealCard();
    }

    public void dealCard() {
        for (byte b = 0; b < 2; b++) {
            for (CardHolder player : listPlayers) {
                player.getDealt(deck.draw());
            }
        }
    }

    public void playHit() {

        ((Player) listPlayers.get(currentIndex)).getDealt(deck.draw());
        System.out.println(currentIndex);
        System.out.println(((Player) listPlayers.get(currentIndex)).getPlayerID());
        System.out.println(((Player) listPlayers.get(currentIndex)).getTotalPoint());
        System.out.println("iscurrent > 21" + (((Player) listPlayers.get(currentIndex)).getTotalPoint() > 21));

        if ( ((Player) listPlayers.get(currentIndex)).getTotalPoint() > 21 ) {
            ((Player) listPlayers.get(currentIndex)).setState(State.LOSE);
            promoteNext();
        }
    }

    public void playStay() {
        ((Player) listPlayers.get(currentIndex)).setState(State.NULL);
        promoteNext();
    }

    private void promoteNext() {
        ((Player) listPlayers.get(currentIndex)).setCurrent(false);

        System.out.println("increment currentIndex");
        currentIndex += 1;

        System.out.println("currentIndex" + currentIndex);
        if ( currentIndex == listPlayers.size() - 1 ) {
            while ( dealer.getTotalPoint() < 17 ) {
                dealer.getDealt(deck.draw());
            }

            // check compare dealer with each player

            // if dealer bust
            if ( dealer.getTotalPoint() > 21) {
                for ( CardHolder player : listPlayers ) {
                    if ( player instanceof Player ) {
                        if ( ((Player) player).getState() == State.NULL ) {
                            ((Player) player).setState(State.WIN);
                        }
                    }
                }
            } else {
                for ( CardHolder player : listPlayers ) {
                    if ( player instanceof Player ) {
                        if ( ((Player) player).getState() == State.NULL ) {
                            if ( player.getTotalPoint() <= dealer.getTotalPoint() ) {
                                ((Player) player).setState(State.LOSE);
                            } else {
                                ((Player) player).setState(State.WIN);
                            }
                        }
                    }
                }
            }

            // Set timer to start new round
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    startNewRound();

                    JSONObject broadcastResponse = new JSONObject();

                    JSONArray playersArrayJSON = new JSONArray();
                    for (CardHolder p : tableManager.getListPlayers()) {
                        playersArrayJSON.put(p.getPlayerJson());
                    }

                    JSONObject data = new JSONObject();
                    data.put("players", playersArrayJSON);
                    broadcastResponse.put("event", Event.UPDATE_TABLE_STATE_BROADCAST_RESPONSE);
                    broadcastResponse.put("data", data);

                    tableManager.broadcast(broadcastResponse.toString());
                }
            }, 8 * 1000);

        } else {
            ((Player) listPlayers.get(currentIndex)).setCurrent(true);
        }
    }
}
