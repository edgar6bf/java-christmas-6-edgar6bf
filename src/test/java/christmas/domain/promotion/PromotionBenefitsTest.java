package christmas.domain.promotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.Giveaway.CHAMPAGNE;
import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PromotionBenefitsTest {

    @DisplayName("할인 가격과 증정품을 입력하면 PromotionBenefits가 생성된다.")
    @Test
    void createPromotionBenefits() throws Exception {
        // Given
        int discountPrice = 2000;

        // When
        PromotionBenefits promotionBenefits = new PromotionBenefits(discountPrice, CHAMPAGNE);

        // Then
        assertThat(promotionBenefits).isNotNull();
    }

    @DisplayName("할인 가격으로 음수가 입력되면 예외가 발생한다.")
    @Test
    void createPromotionBenefitsWithNegativeDiscountPrice() throws Exception {
        // Given
        int discountPrice = -2000;

        // When & Then
        assertThatThrownBy(() -> new PromotionBenefits(discountPrice, CHAMPAGNE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 할인 가격 입니다.");
    }

    @DisplayName("제공된 혜택이 없다면 false를 반환한다.")
    @Test
    void noBenefitsReturnFalse() throws Exception {
        // Given
        PromotionBenefits promotionBenefits = new PromotionBenefits(0, NO_GIVEAWAY);

        // When
        boolean hasBenefits = promotionBenefits.hasBenefits();

        // Then
        assertThat(hasBenefits).isFalse();
    }
}
