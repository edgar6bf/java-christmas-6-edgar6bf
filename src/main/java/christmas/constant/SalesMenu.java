package christmas.constant;

import java.util.Arrays;

import static christmas.constant.MenuCategory.*;

public enum SalesMenu {
    MUSHROOM_SOUP("양송이수프", 6000, APPETIZER),
    TAPAS("타파스", 5500, APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, APPETIZER),
    T_BONE_STEAK("티본스테이크", 55000, MAIN),
    BARBECUE_RIBS("바비큐립", 54000, MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MAIN),
    CHOCOLATE_CAKE("초코케이크", 15000, DESSERT),
    ICE_CREAM("아이스크림", 5000, DESSERT),
    ZERO_COLA("제로콜라", 3000, BEVERAGE),
    RED_WINE("레드와인", 60000, BEVERAGE),
    CHAMPAGNE("샴페인", 25000, BEVERAGE);

    private final String name;
    private final int price;
    private final MenuCategory category;

    SalesMenu(String name, int price, MenuCategory category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public static SalesMenu of(String menuName) {
        return Arrays.stream(SalesMenu.values())
                .filter(salesMenu -> salesMenu.getName().equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 메뉴 이름입니다."));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getCategory() {
        return category;
    }
}
