package christmas.domain.promotion;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import java.util.List;

import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.NO_PROMOTION;
import static christmas.constant.PromotionTitle.SPECIAL_DISCOUNT;

public class SpecialDiscountPromotion implements Promotion {

    private static final int MINIMUM_VALID_TOTAL_ORDER_PRICE = 10000;
    private static final int GIVEAWAY_COUNT = 0;
    private static final int DISCOUNT_PRICE = 1000;
    private static final List<Integer> applicablePromotionDates = List.of(3, 10, 17, 24, 25, 31);

    @Override
    public PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus) {
        if (!isAvailableCondition(orderDate, orderMenus)) {
            return new PromotionBenefits(
                    NO_PROMOTION,
                    GIVEAWAY_COUNT,
                    NO_GIVEAWAY,
                    GIVEAWAY_COUNT
            );
        }

        return createBenefits(orderMenus);
    }

    private boolean isAvailableCondition(OrderDate orderDate, OrderMenus orderMenus) {
        return isAvailableDate(orderDate) && isValidTotalOrderPrice(orderMenus);
    }

    private boolean isAvailableDate(OrderDate orderDate) {
        return orderDate.isOrderDateInclude(applicablePromotionDates);
    }

    private boolean isValidTotalOrderPrice(OrderMenus orderMenus) {
        return orderMenus.hasOverOrEqualTotalOrderPrice(MINIMUM_VALID_TOTAL_ORDER_PRICE);
    }

    private PromotionBenefits createBenefits(OrderMenus orderMenus) {
        return new PromotionBenefits(
                SPECIAL_DISCOUNT,
                DISCOUNT_PRICE,
                NO_GIVEAWAY,
                GIVEAWAY_COUNT
        );
    }
}
