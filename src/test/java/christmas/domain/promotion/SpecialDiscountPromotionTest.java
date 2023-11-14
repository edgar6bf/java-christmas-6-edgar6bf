package christmas.domain.promotion;

import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[SpecialDiscountPromotion] : 특별 할인 테스트")
class SpecialDiscountPromotionTest {

    @DisplayName("총주문 금액에서 1,000원 할인한다.")
    @Test
    void applyPromotion() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(3);
        OrderMenus orderMenus = new OrderMenus(
                List.of(
                        new OrderMenu("티본스테이크", 10)
                )
        );
        int preDiscountTotalOrderPrice = orderMenus.getTotalOrderPrice();
        int discountAmount = 1000;
        int expectedDiscountedTotalOrderPrice = preDiscountTotalOrderPrice - discountAmount;
        SpecialDiscountPromotion specialDiscountPromotion = new SpecialDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = specialDiscountPromotion.applyPromotion(orderDate, orderMenus);
        int postDiscountTotalOrderPrice = promotionBenefits.applyBenefit(preDiscountTotalOrderPrice);

        // Then
        assertThat(postDiscountTotalOrderPrice).isEqualTo(expectedDiscountedTotalOrderPrice);
    }
}
