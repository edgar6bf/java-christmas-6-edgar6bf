package christmas.domain.order;

import christmas.constant.Menu;
import christmas.constant.MenuCategory;

import static christmas.constant.Menu.*;

public class OrderMenu {

    private static final int MINIMUM_AVAILABLE_COUNT = 1;
    private static final int MAXIMUM_AVAILABLE_COUNT = 20;

    private final Menu menu;
    private final int count;

    public OrderMenu(String menu, int count) {
        this.menu = parseMenu(menu);

        validateCount(count);
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < MINIMUM_AVAILABLE_COUNT || count > MAXIMUM_AVAILABLE_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 메뉴 개수입니다.");
        }
    }

    public boolean checkMenuCategory(MenuCategory category) {
        return menu.hasCategory(category);
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getMenuCount() {
        return count;
    }

    public int calculateMenuPrice() {
        return menu.getPrice() * count;
    }
}
