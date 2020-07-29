package ServerSide;

import gameComponent.*;
import io.netty.channel.ChannelId;

import java.util.LinkedList;


public class Game {
    private ChannelId currentPlayerID;
    private int currentIndex;
    private Player currentPlayer;

    private LinkedList<CardHolder> listPlayers;
    private Dealer dealer;
    private Deck deck;

    public Game() { }

    public void init(TableManager tableManager) {
        listPlayers = tableManager.getListPlayers();
        currentIndex = 0;
        currentPlayer = ((Player) listPlayers.get(currentIndex));
        currentPlayerID = currentPlayer.getPlayerID();
        currentPlayer.setCurrent(true);
        dealer = new Dealer();
        deck = new Deck();
        listPlayers.addLast(dealer);
    }

    public void startNewRound() {
        currentIndex = 0;
        currentPlayerID = ((Player) listPlayers.get(currentIndex)).getPlayerID();
        deck.resetDeck();
        for ( CardHolder player : listPlayers) {
            player.reset();
        }
    }

    public void dealCard() {
        for (byte b = 0; b < 2; b++) {
            for (CardHolder player : listPlayers) {
                player.getDealt(deck.draw());
            }
        }
    }

    public void playHit() {
        currentPlayer = ((Player) listPlayers.get(currentIndex));
        if ( currentPlayer.getTotalPoint() > 21 ) {
            currentPlayer.setState(State.LOSE);
            promoteNext();
        } else {
            currentPlayer.getDealt(deck.draw());
        }
    }

    public void playStay() {
        currentPlayer.setState(State.NULL);
        promoteNext();
    }

    private void promoteNext() {
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

        } else {
            currentPlayer.setCurrent(false);
            currentIndex += 1;
            currentPlayer = ((Player) listPlayers.get(currentIndex));
            currentPlayerID = currentPlayer.getPlayerID();
        }
    }

    public ChannelId getCurrentPlayerID() {
        return currentPlayerID;
    }
}
