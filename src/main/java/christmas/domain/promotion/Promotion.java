package christmas.domain.promotion;

import christmas.domain.benefit.DiscountAmount;
import christmas.domain.benefit.Giveaway;
import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.NO_PROMOTION;

public interface Promotion {

    PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus);

    default PromotionBenefits noPromotion() {
        return new PromotionBenefits(
                NO_PROMOTION,
                new DiscountAmount(0),
                new Giveaway(NO_GIVEAWAY, 0)
        );
    }
}
