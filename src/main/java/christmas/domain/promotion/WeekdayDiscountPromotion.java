package christmas.domain.promotion;

import christmas.domain.benefit.DiscountAmount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import java.util.List;

import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;
import static christmas.constant.MenuCategory.DESSERT;
import static christmas.constant.PromotionTitle.*;

public class WeekdayDiscountPromotion implements Promotion {

    private static final int MINIMUM_VALID_TOTAL_ORDER_PRICE = 10000;
    private static final int ZERO_VALUE = 0;
    private static final int DISCOUNT_PRICE = 2023;
    private static final List<Integer> applicablePromotionDates =
            List.of(3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31);

    @Override
    public PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus) {
        if (!isAvailableCondition(orderDate, orderMenus)) {
            return noPromotion();
        }

        return createBenefits(orderMenus);
    }

    private boolean isAvailableCondition(OrderDate orderDate, OrderMenus orderMenus) {
        return isAvailableDate(orderDate)
                && isValidTotalOrderPrice(orderMenus)
                && hasDesertMenu(orderMenus);
    }

    private boolean isAvailableDate(OrderDate orderDate) {
        return orderDate.isOrderDateInclude(applicablePromotionDates);
    }

    private boolean isValidTotalOrderPrice(OrderMenus orderMenus) {
        return orderMenus.hasOverOrEqualTotalOrderPrice(MINIMUM_VALID_TOTAL_ORDER_PRICE);
    }

    private boolean hasDesertMenu(OrderMenus orderMenus) {
        return orderMenus.hasMenusInCategory(DESSERT);
    }

    private PromotionBenefits createBenefits(OrderMenus orderMenus) {
        int dessertCount = orderMenus.getOrderCountInCategory(DESSERT);

        return new PromotionBenefits(
                WEEKDAY_DISCOUNT,
                new DiscountAmount(DISCOUNT_PRICE * dessertCount),
                new Giveaway(NO_GIVEAWAY, ZERO_VALUE)
        );
    }
}
