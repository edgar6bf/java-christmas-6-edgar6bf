package christmas.domain.promotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.constant.Giveaway.CHAMPAGNE;
import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.CHRISTMAS_DDAY_DISCOUNT;
import static christmas.constant.PromotionTitle.NO_PROMOTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[PromotionBenefits] : 이벤트 혜택 테스트")
class PromotionBenefitsTest {

    @DisplayName("PromotionBenefits이 생성된다.")
    @Test
    void createPromotionBenefits() throws Exception {
        // Given
        int discountPrice = 2000;
        int giveawayCount = 1;

        // When
        PromotionBenefits promotionBenefits = new PromotionBenefits(
                CHRISTMAS_DDAY_DISCOUNT,
                discountPrice,
                CHAMPAGNE,
                giveawayCount
        );

        // Then
        assertThat(promotionBenefits).isNotNull();
    }

    @DisplayName("할인 가격으로 음수가 입력되면 예외가 발생한다.")
    @Test
    void createPromotionBenefitsWithNegativeDiscountPrice() throws Exception {
        // Given
        int discountPrice = -2000;
        int giveawayCount = 1;

        // When & Then
        assertThatThrownBy(() -> new PromotionBenefits(CHRISTMAS_DDAY_DISCOUNT, discountPrice, CHAMPAGNE, giveawayCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 할인 가격 입니다.");
    }

    @DisplayName("증정품 개수로 음수가 입력되면 예외가 발생한다.")
    @Test
    void createPromotionBenefitsWithNegativeGiveawayCount() throws Exception {
        // Given
        int discountPrice = 2000;
        int giveawayCount = -1;

        // When & Then
        assertThatThrownBy(() -> new PromotionBenefits(CHRISTMAS_DDAY_DISCOUNT, discountPrice, CHAMPAGNE, giveawayCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 증정품 개수 입니다.");
    }

    @DisplayName("증정품이 없으면서 0이 아닌 증정품 개수가 입력되면 예외가 발생한다.")
    @Test
    void createPromotionBenefitsWithNoGiveawayAndNotZeroGiveawayCount() throws Exception {
        // Given
        int discountPrice = 2000;
        int giveawayCount = 3;

        // When & Then
        assertThatThrownBy(() -> new PromotionBenefits(CHRISTMAS_DDAY_DISCOUNT, discountPrice, NO_GIVEAWAY, giveawayCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 증정품 개수 입니다.");
    }

    @DisplayName("제공된 혜택이 없다면 false를 반환한다.")
    @Test
    void noBenefitsReturnFalse() throws Exception {
        // Given
        PromotionBenefits promotionBenefits = new PromotionBenefits(
                NO_PROMOTION,
                0,
                NO_GIVEAWAY,
                0
        );

        // When
        boolean hasBenefits = promotionBenefits.hasBenefits();

        // Then
        assertThat(hasBenefits).isFalse();
    }
}
