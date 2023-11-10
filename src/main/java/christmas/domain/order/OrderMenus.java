package christmas.domain.order;

import java.util.List;
import java.util.stream.Collectors;

import static christmas.constant.MenuCategory.BEVERAGE;

public class OrderMenus {

    private static final int MAXIMUM_AVAILABLE_ORDER_MENU_COUNT = 20;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(List<OrderMenu> orderMenus) {
        validateMenus(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateMenus(List<OrderMenu> menus) {
        validateOnlyBeverageInMenu(menus);
        validateMenusDuplicate(menus);
        validateMenusTotalCount(menus);
    }

    private void validateOnlyBeverageInMenu(List<OrderMenu> menus) {
        int beverageCount = menus.stream()
                .filter(menu -> menu.checkMenuCategory(BEVERAGE))
                .toList()
                .size();

        if (menus.size() == beverageCount) {
            throw new IllegalArgumentException("음료 메뉴만 주문할 수 없습니다.");
        }
    }

    private void validateMenusDuplicate(List<OrderMenu> menus) {
        int menuKindCount = menus.stream()
                .map(OrderMenu::getMenuName)
                .collect(Collectors.toSet())
                .size();

        if (menus.size() != menuKindCount) {
            throw new IllegalArgumentException("중복된 메뉴는 주문할 수 없습니다.");
        }
    }

    private void validateMenusTotalCount(List<OrderMenu> menus) {
        int totalMenuCount = menus.stream()
                .mapToInt(OrderMenu::getMenuCount)
                .sum();

        if (totalMenuCount > MAXIMUM_AVAILABLE_ORDER_MENU_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 총주문 메뉴 개수입니다.");
        }
    }

    public boolean hasOverOrEqualTotalPrice(int price) {
        return countTotalPrice() >= price;
    }

    private int countTotalPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculateMenuPrice)
                .sum();
    }
}
