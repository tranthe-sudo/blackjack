package gameComponent;

public enum Suit {
    Spade ("spade"),
    Club ("club"),
    Diamond ("diamond"),
    Heart ("heart");


    private String string;

    Suit(String string) {
        this.string = string;
    };

    @Override
    public String toString() {
        return string;
    }
}
