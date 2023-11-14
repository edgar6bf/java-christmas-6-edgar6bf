package christmas.domain.order;

import christmas.constant.MenuCategory;
import christmas.dto.MenuNameAndCount;

import java.util.List;
import java.util.stream.Collectors;

import static christmas.constant.MenuCategory.BEVERAGE;

public class OrderMenus {

    private static final int MAXIMUM_AVAILABLE_ORDER_MENU_COUNT = 20;

    private final List<OrderMenu> orderMenus;

    public OrderMenus(List<OrderMenu> orderMenus) {
        validateOrderMenus(orderMenus);
        this.orderMenus = orderMenus;
    }

    private void validateOrderMenus(List<OrderMenu> orderMenus) {
        validateOrderMenusHasOnlyBeverage(orderMenus);
        validateOrderMenusDuplicate(orderMenus);
        validateTotalOrderCount(orderMenus);
    }

    private void validateOrderMenusHasOnlyBeverage(List<OrderMenu> orderMenus) {
        int beverageCount = getOrderMenusInCategory(orderMenus, BEVERAGE).size();

        if (orderMenus.size() == beverageCount) {
            throw new IllegalArgumentException("음료 메뉴만 주문할 수 없습니다.");
        }
    }

    private List<OrderMenu> getOrderMenusInCategory(List<OrderMenu> menus, MenuCategory category) {
        return menus.stream()
                .filter(menu -> menu.checkMenuCategory(category))
                .toList();
    }

    private void validateOrderMenusDuplicate(List<OrderMenu> orderMenus) {
        int orderMenusKindCount = orderMenus.stream()
                .map(OrderMenu::getMenuName)
                .collect(Collectors.toSet())
                .size();

        if (orderMenus.size() != orderMenusKindCount) {
            throw new IllegalArgumentException("중복된 메뉴는 주문할 수 없습니다.");
        }
    }

    private void validateTotalOrderCount(List<OrderMenu> orderMenus) {
        int totalOrderCount = calculateTotalOrderCount(orderMenus);

        if (totalOrderCount > MAXIMUM_AVAILABLE_ORDER_MENU_COUNT) {
            throw new IllegalArgumentException("유효하지 않은 총주문 개수입니다.");
        }
    }

    private int calculateTotalOrderCount(List<OrderMenu> orderMenus) {
        return orderMenus.stream()
                .mapToInt(OrderMenu::getOrderCount)
                .sum();
    }

    public boolean hasOverOrEqualTotalOrderPrice(int price) {
        return calculateTotalOrderPrice() >= price;
    }

    private int calculateTotalOrderPrice() {
        return orderMenus.stream()
                .mapToInt(OrderMenu::calculateTotalOrderPrice)
                .sum();
    }

    public boolean hasMenusInCategory(MenuCategory category) {
        return orderMenus.stream()
                .anyMatch(menu -> menu.checkMenuCategory(category));
    }

    public int getOrderCountInCategory(MenuCategory category) {
        List<OrderMenu> orderMenusInCategory = getOrderMenusInCategory(orderMenus, category);

        return calculateTotalOrderCount(orderMenusInCategory);
    }

    public List<MenuNameAndCount> getOrderHistory() {
        return orderMenus.stream()
                .map(MenuNameAndCount::from)
                .toList();
    }

    public int getTotalOrderPrice() {
        return calculateTotalOrderPrice();
    }
}
