package christmas.constant;

public enum Giveaway {
    CHAMPAGNE("샴페인", 25000),
    NO_GIVEAWAY("없음", 0);

    private final String name;
    private final int price;

    Giveaway(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
