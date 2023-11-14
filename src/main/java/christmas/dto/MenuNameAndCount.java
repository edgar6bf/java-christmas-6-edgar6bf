package christmas.dto;

import christmas.domain.order.OrderMenu;

public record MenuNameAndCount(String name, int count) {

    public static MenuNameAndCount from(OrderMenu orderMenu) {
        return new MenuNameAndCount(orderMenu.getMenuName(), orderMenu.getOrderCount());
    }
}
