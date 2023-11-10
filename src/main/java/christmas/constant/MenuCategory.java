package christmas.constant;

public enum MenuCategory {
    APPETIZER("애피타이저"),
    MAIN("메인"),
    DESSERT("디저트"),
    BEVERAGE("음료");

    private final String title;

    MenuCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
