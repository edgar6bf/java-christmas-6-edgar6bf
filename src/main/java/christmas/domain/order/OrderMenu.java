package christmas.domain.order;

import christmas.constant.SalesMenu;
import christmas.constant.MenuCategory;

import static christmas.constant.SalesMenu.*;

public class OrderMenu {

    private static final int MINIMUM_AVAILABLE_COUNT = 1;
    private static final int MAXIMUM_AVAILABLE_COUNT = 20;

    private final SalesMenu salesMenu;
    private final int orderCount;

    public OrderMenu(String menu, int orderCount) {
        validateOrderCount(orderCount);
        this.salesMenu = convertMenuNameToMenu(menu);
        this.orderCount = orderCount;
    }

    private void validateOrderCount(int orderCount) {
        if (orderCount < MINIMUM_AVAILABLE_COUNT || orderCount > MAXIMUM_AVAILABLE_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 주문 개수입니다.");
        }
    }

    public boolean checkMenuCategory(MenuCategory category) {
        return salesMenu.hasCategory(category);
    }

    public String getMenuName() {
        return salesMenu.getName();
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int calculateTotalOrderPrice() {
        return salesMenu.getPrice() * orderCount;
    }
}
