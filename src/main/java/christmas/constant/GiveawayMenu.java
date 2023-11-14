package christmas.constant;

public enum GiveawayMenu {
    CHAMPAGNE("샴페인", 25000),
    NO_GIVEAWAY("없음", 0);

    private final String name;
    private final int price;

    GiveawayMenu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
