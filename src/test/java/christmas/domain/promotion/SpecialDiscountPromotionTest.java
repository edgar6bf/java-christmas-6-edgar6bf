package christmas.domain.promotion;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constant.Giveaway.NO_GIVEAWAY;
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
        int expected = 1000;
        SpecialDiscountPromotion specialDiscountPromotion = new SpecialDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = specialDiscountPromotion.applyPromotion(orderDate, orderMenus);

        // Then
        assertThat(promotionBenefits.getDiscountPrice()).isEqualTo(expected);
        assertThat(promotionBenefits.getGiveaway()).isEqualTo(NO_GIVEAWAY);
    }
}
