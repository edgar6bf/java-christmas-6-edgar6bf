package christmas.domain.promotion;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import static christmas.constant.Giveaway.CHAMPAGNE;
import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.GIVEAWAY;
import static christmas.constant.PromotionTitle.NO_PROMOTION;

public class GiveawayPromotion implements Promotion {

    private static final int MINIMUM_VALID_TOTAL_ORDER_PRICE = 120000;
    private static final int PROMOTION_START_DATE = 1;
    private static final int PROMOTION_END_DATE = 31;
    private static final int ZERO_VALUE = 0;
    private static final int GIVEAWAY_COUNT = 1;

    @Override
    public PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus) {
        if (!isAvailableCondition(orderDate, orderMenus)) {
            return new PromotionBenefits(
                    NO_PROMOTION,
                    ZERO_VALUE,
                    NO_GIVEAWAY,
                    ZERO_VALUE
            );
        }

        return createBenefits(orderMenus);
    }

    private boolean isAvailableCondition(OrderDate orderDate, OrderMenus orderMenus) {
        return isAvailableDate(orderDate) && isValidTotalOrderPrice(orderMenus);
    }

    private boolean isAvailableDate(OrderDate orderDate) {
        return orderDate.isOrderDateInclude(PROMOTION_START_DATE, PROMOTION_END_DATE);
    }

    private boolean isValidTotalOrderPrice(OrderMenus orderMenus) {
        return orderMenus.hasOverOrEqualTotalOrderPrice(MINIMUM_VALID_TOTAL_ORDER_PRICE);
    }

    private PromotionBenefits createBenefits(OrderMenus orderMenus) {
        return new PromotionBenefits(
                GIVEAWAY,
                ZERO_VALUE,
                CHAMPAGNE,
                GIVEAWAY_COUNT
        );
    }
}