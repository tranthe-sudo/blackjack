package gameComponent;

public enum SUIT {
    Spade ("spade"),
    Club ("club"),
    Diamond ("diamond"),
    Heart ("heart");


    private String string;

    SUIT(String string) {
        this.string = string;
    };

    @Override
    public String toString() {
        return string;
    }
}
