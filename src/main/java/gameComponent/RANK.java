package gameComponent;

public enum RANK {

    TWO (2, "2"),
    THREE (3, "3"),
    FOUR (4, "4"),
    FIVE (5, "5"),
    SIX (6, "6"),
    SEVEN (7, "7"),
    EIGHT (8, "8"),
    NINE (9, "9"),
    TEN (10, "10"),
    JACK (10, "jack"),
    QUEEN (10, "queen"),
    KING (10, "king"),
    ACE (11, "ace");

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
