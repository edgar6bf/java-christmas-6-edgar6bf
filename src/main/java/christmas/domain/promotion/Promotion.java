package christmas.domain.promotion;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;

public interface Promotion {

    PromotionBenefits applyPromotion(OrderDate orderDate, OrderMenus orderMenus);
}
