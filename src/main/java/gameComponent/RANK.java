package gameComponent;

public enum RANK {

    Two (2, "2"),
    Three (3, "3"),
    Four (4, "4"),
    Five (5, "5"),
    Six (6, "6"),
    Seven (7, "7"),
    Eight (8, "8"),
    Nine (9, "9"),
    Ten (10, "10"),
    Jack (10, "jack"),
    Queen(10, "queen"),
    King (10, "king"),
    Ace (11, "ace");

    private int value;
    private String string;
    RANK(int value, String string) {
        this.value = value;
        this.string = string;
    }

    public int getValue () {
        return value;
    }

    @Override
    public String toString() {
        return string;
    }
}
