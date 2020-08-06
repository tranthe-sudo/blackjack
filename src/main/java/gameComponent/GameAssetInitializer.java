package gameComponent;


// Only allocate the Card Deck once instead of creating an array of Cards multiple times
public class GameAssetInitializer {
    static final Card[] standardCardDeck = {
            new Card(Suit.Club, Rank.Two),
            new Card(Suit.Club, Rank.Three),
            new Card(Suit.Club, Rank.Four),
            new Card(Suit.Club, Rank.Five),
            new Card(Suit.Club, Rank.Six),
            new Card(Suit.Club, Rank.Seven),
            new Card(Suit.Club, Rank.Eight),
            new Card(Suit.Club, Rank.Nine),
            new Card(Suit.Club, Rank.Ten),
            new Card(Suit.Club, Rank.Jack),
            new Card(Suit.Club, Rank.Queen),
            new Card(Suit.Club, Rank.King),
            new Card(Suit.Club, Rank.Ace),
            new Card(Suit.Spade, Rank.Two),
            new Card(Suit.Spade, Rank.Three),
            new Card(Suit.Spade, Rank.Four),
            new Card(Suit.Spade, Rank.Five),
            new Card(Suit.Spade, Rank.Six),
            new Card(Suit.Spade, Rank.Seven),
            new Card(Suit.Spade, Rank.Eight),
            new Card(Suit.Spade, Rank.Nine),
            new Card(Suit.Spade, Rank.Ten),
            new Card(Suit.Spade, Rank.Jack),
            new Card(Suit.Spade, Rank.Queen),
            new Card(Suit.Spade, Rank.King),
            new Card(Suit.Spade, Rank.Ace),
            new Card(Suit.Diamond, Rank.Two),
            new Card(Suit.Diamond, Rank.Three),
            new Card(Suit.Diamond, Rank.Four),
            new Card(Suit.Diamond, Rank.Five),
            new Card(Suit.Diamond, Rank.Six),
            new Card(Suit.Diamond, Rank.Seven),
            new Card(Suit.Diamond, Rank.Eight),
            new Card(Suit.Diamond, Rank.Nine),
            new Card(Suit.Diamond, Rank.Ten),
            new Card(Suit.Diamond, Rank.Jack),
            new Card(Suit.Diamond, Rank.Queen),
            new Card(Suit.Diamond, Rank.King),
            new Card(Suit.Diamond, Rank.Ace),
            new Card(Suit.Heart, Rank.Two),
            new Card(Suit.Heart, Rank.Three),
            new Card(Suit.Heart, Rank.Four),
            new Card(Suit.Heart, Rank.Five),
            new Card(Suit.Heart, Rank.Six),
            new Card(Suit.Heart, Rank.Seven),
            new Card(Suit.Heart, Rank.Eight),
            new Card(Suit.Heart, Rank.Nine),
            new Card(Suit.Heart, Rank.Ten),
            new Card(Suit.Heart, Rank.Jack),
            new Card(Suit.Heart, Rank.Queen),
            new Card(Suit.Heart, Rank.King),
            new Card(Suit.Heart, Rank.Ace)
    };

    public static Card[] getStandardCardDeck() {
        return standardCardDeck;
    }
}
