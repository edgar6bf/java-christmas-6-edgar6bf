package christmas.domain.promotion;

import christmas.domain.benefit.DiscountAmount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import java.util.List;

import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;
import static christmas.constant.MenuCategory.MAIN;
import static christmas.constant.PromotionTitle.NO_PROMOTION;
import static christmas.constant.PromotionTitle.WEEKEND_DISCOUNT;

public class WeekendDiscountPromotion implements Promotion {

    private static final int MINIMUM_VALID_TOTAL_ORDER_PRICE = 10000;
    private static final int ZERO_VALUE = 0;
    private static final int DISCOUNT_PRICE = 2023;
    private static final List<Integer> applicablePromotionDates = List.of(1, 2, 8, 9, 15, 16, 22, 23, 29, 30);

    @Override
    public PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus) {
        if (!isAvailableCondition(orderDate, orderMenus)) {
            return new PromotionBenefits(
                    NO_PROMOTION,
                    new DiscountAmount(ZERO_VALUE),
                    new Giveaway(NO_GIVEAWAY, ZERO_VALUE)
            );
        }

        return createBenefits(orderMenus);
    }

    private boolean isAvailableCondition(OrderDate orderDate, OrderMenus orderMenus) {
        return isAvailableDate(orderDate)
                && isValidTotalOrderPrice(orderMenus)
                && hasMainMenu(orderMenus);
    }

    private boolean isAvailableDate(OrderDate orderDate) {
        return orderDate.isOrderDateInclude(applicablePromotionDates);
    }

    private boolean isValidTotalOrderPrice(OrderMenus orderMenus) {
        return orderMenus.hasOverOrEqualTotalOrderPrice(MINIMUM_VALID_TOTAL_ORDER_PRICE);
    }

    private boolean hasMainMenu(OrderMenus orderMenus) {
        return orderMenus.hasMenusInCategory(MAIN);
    }

    private PromotionBenefits createBenefits(OrderMenus orderMenus) {
        int mainMenuCount = orderMenus.getOrderCountInCategory(MAIN);

        return new PromotionBenefits(
                WEEKEND_DISCOUNT,
                new DiscountAmount(DISCOUNT_PRICE * mainMenuCount),
                new Giveaway(NO_GIVEAWAY, ZERO_VALUE)
        );
    }
}
