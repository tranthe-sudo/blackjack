package gameComponent;

import io.netty.channel.group.DefaultChannelGroup;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Deck {
    private static Integer[] index = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                         20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
                         38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    // Get a pointer to array of Cards
    private Card[] cardDeck = GameAssetInitializer.getStandardCardDeck();
    private LinkedList<Integer> randomIndex;


    public Deck () {
        init();
    }

    public void init() {
        Collections.shuffle(Arrays.asList(index));
        randomIndex = new LinkedList<>(Arrays.asList(index));
    }

    public Card draw() {
        return cardDeck[randomIndex.poll()];
    }

    public void resetDeck() {
        init();
    }
}
