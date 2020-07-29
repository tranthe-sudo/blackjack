package gameComponent;


// Only allocate the Card Deck once instead of creating an array of Cards multiple times
public class GameAssetInitializer {
    static final Card[] standardCardDeck = {
            new Card(SUIT.Club, RANK.Two),
            new Card(SUIT.Club, RANK.Three),
            new Card(SUIT.Club, RANK.Four),
            new Card(SUIT.Club, RANK.Five),
            new Card(SUIT.Club, RANK.Six),
            new Card(SUIT.Club, RANK.Seven),
            new Card(SUIT.Club, RANK.Eight),
            new Card(SUIT.Club, RANK.Nine),
            new Card(SUIT.Club, RANK.Ten),
            new Card(SUIT.Club, RANK.Jack),
            new Card(SUIT.Club, RANK.Queen),
            new Card(SUIT.Club, RANK.King),
            new Card(SUIT.Club, RANK.Ace),
            new Card(SUIT.Spade, RANK.Two),
            new Card(SUIT.Spade, RANK.Three),
            new Card(SUIT.Spade, RANK.Four),
            new Card(SUIT.Spade, RANK.Five),
            new Card(SUIT.Spade, RANK.Six),
            new Card(SUIT.Spade, RANK.Seven),
            new Card(SUIT.Spade, RANK.Eight),
            new Card(SUIT.Spade, RANK.Nine),
            new Card(SUIT.Spade, RANK.Ten),
            new Card(SUIT.Spade, RANK.Jack),
            new Card(SUIT.Spade, RANK.Queen),
            new Card(SUIT.Spade, RANK.King),
            new Card(SUIT.Spade, RANK.Ace),
            new Card(SUIT.Diamond, RANK.Two),
            new Card(SUIT.Diamond, RANK.Three),
            new Card(SUIT.Diamond, RANK.Four),
            new Card(SUIT.Diamond, RANK.Five),
            new Card(SUIT.Diamond, RANK.Six),
            new Card(SUIT.Diamond, RANK.Seven),
            new Card(SUIT.Diamond, RANK.Eight),
            new Card(SUIT.Diamond, RANK.Nine),
            new Card(SUIT.Diamond, RANK.Ten),
            new Card(SUIT.Diamond, RANK.Jack),
            new Card(SUIT.Diamond, RANK.Queen),
            new Card(SUIT.Diamond, RANK.King),
            new Card(SUIT.Diamond, RANK.Ace),
            new Card(SUIT.Heart, RANK.Two),
            new Card(SUIT.Heart, RANK.Three),
            new Card(SUIT.Heart, RANK.Four),
            new Card(SUIT.Heart, RANK.Five),
            new Card(SUIT.Heart, RANK.Six),
            new Card(SUIT.Heart, RANK.Seven),
            new Card(SUIT.Heart, RANK.Eight),
            new Card(SUIT.Heart, RANK.Nine),
            new Card(SUIT.Heart, RANK.Ten),
            new Card(SUIT.Heart, RANK.Jack),
            new Card(SUIT.Heart, RANK.Queen),
            new Card(SUIT.Heart, RANK.King),
            new Card(SUIT.Heart, RANK.Ace)
    };

    public static Card[] getStandardCardDeck() {
        return standardCardDeck;
    }
}
