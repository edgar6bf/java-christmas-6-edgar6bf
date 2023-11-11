package christmas.domain.order;

import christmas.constant.Menu;
import christmas.constant.MenuCategory;

import static christmas.constant.Menu.*;

public class OrderMenu {

    private static final int MINIMUM_AVAILABLE_COUNT = 1;
    private static final int MAXIMUM_AVAILABLE_COUNT = 20;

    private final Menu menu;
    private final int orderCount;

    public OrderMenu(String menu, int orderCount) {
        this.menu = parseMenu(menu);

        validateOrderCount(orderCount);
        this.orderCount = orderCount;
    }

    private void validateOrderCount(int orderCount) {
        if (orderCount < MINIMUM_AVAILABLE_COUNT || orderCount > MAXIMUM_AVAILABLE_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 메뉴 개수입니다.");
        }
    }

    public boolean checkMenuCategory(MenuCategory category) {
        return menu.hasCategory(category);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int calculateTotalOrderPrice() {
        return menu.getPrice() * orderCount;
    }
}
