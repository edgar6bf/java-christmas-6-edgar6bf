package christmas.domain.promotion;

import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.dto.GiveawayBenefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.constant.GiveawayMenu.CHAMPAGNE;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[GiveawayPromotion] : 증정 이벤트 테스트")
class GiveawayPromotionTest {

    @DisplayName("샴페인 1개를 증정한다.")
    @Test
    void applyPromotion() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(3);
        OrderMenus notHasDessertMenu = new OrderMenus(
                List.of(
                        new OrderMenu("티본스테이크", 10)
                )
        );
        int expectedGiveawayCount = 1;
        GiveawayPromotion giveawayPromotion = new GiveawayPromotion();

        // When
        PromotionBenefits promotionBenefits = giveawayPromotion.applyPromotion(orderDate, notHasDessertMenu);
        GiveawayBenefit giveawayBenefit = promotionBenefits.applyBenefit();

        // Then
        assertThat(giveawayBenefit.giveawayName()).isEqualTo(CHAMPAGNE.getName());
        assertThat(giveawayBenefit.count()).isEqualTo(expectedGiveawayCount);
    }
}
