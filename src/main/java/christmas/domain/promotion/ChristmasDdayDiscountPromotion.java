package christmas.domain.promotion;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import static christmas.constant.Giveaway.*;

public class ChristmasDdayDiscountPromotion implements Promotion {

    private final static int MINIMUM_VALID_TOTAL_ORDER_PRICE = 10000;
    private final static int PROMOTION_START_DATE = 1;
    private final static int PROMOTION_END_DATE = 25;
    private final static int NOT_DISCOUNT_PRICE = 0;
    private final static int MAXIMUM_DISCOUNT_PRICE = 3400;
    private final static int ONE_DATE_DISCOUNT_PRICE = 100;

    @Override
    public PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus) {
        if (!isAvailableCondition(orderDate, orderMenus)) {
            return new PromotionBenefits(NOT_DISCOUNT_PRICE, NO_GIVEAWAY);
        }

        return createBenefits(orderDate);
    }

    private boolean isAvailableCondition(OrderDate orderDate, OrderMenus orderMenus) {
        return isAvailableDate(orderDate) && isValidTotalOrderPrice(orderMenus);
    }

    private boolean isAvailableDate(OrderDate orderDate) {
        return orderDate.isOrderDateInclude(PROMOTION_START_DATE, PROMOTION_END_DATE);
    }

    private boolean isValidTotalOrderPrice(OrderMenus orderMenus) {
        return orderMenus.hasOverOrEqualTotalPrice(MINIMUM_VALID_TOTAL_ORDER_PRICE);
    }

    private PromotionBenefits createBenefits(OrderDate orderDate) {
        int discountPrice = MAXIMUM_DISCOUNT_PRICE - (ONE_DATE_DISCOUNT_PRICE * orderDate.calculateDateDifference(PROMOTION_END_DATE));

        return new PromotionBenefits(
                discountPrice,
                NO_GIVEAWAY
        );
    }
}
