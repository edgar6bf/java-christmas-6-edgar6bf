package christmas.domain.benefit;

import christmas.constant.GiveawayMenu;
import christmas.constant.PromotionTitle;
import christmas.dto.PromotionBenefitHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.constant.GiveawayMenu.CHAMPAGNE;
import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[PromotionBenefits] : 이벤트 혜택 테스트")
class PromotionBenefitsTest {

    @DisplayName("할인 금액과 증정품을 입력하면 PromotionBenefits가 생성된다.")
    @Test
    void createPromotionBenefits() throws Exception {
        // Given
        DiscountAmount discountAmount = new DiscountAmount(2000);
        Giveaway giveaway = new Giveaway(CHAMPAGNE, 1);

        // When
        PromotionBenefits promotionBenefits = new PromotionBenefits(
                CHRISTMAS_DDAY_DISCOUNT,
                discountAmount,
                giveaway
        );

        // Then
        assertThat(promotionBenefits).isNotNull();
    }

    @DisplayName("프로모션 혜택이 존재하는지 여부를 반환한다.")
    @MethodSource("promotionBenefitsAndHasBenefits")
    @ParameterizedTest(name = "[{index}] \"{4}\" => {3}")
    void hasBenefits(int discountAmountValue, GiveawayMenu giveawayMenu, int giveawayCount, boolean expected, String description) throws Exception {
        // Given
        DiscountAmount discountAmount = new DiscountAmount(discountAmountValue);
        Giveaway giveaway = new Giveaway(giveawayMenu, giveawayCount);
        PromotionBenefits promotionBenefits = new PromotionBenefits(
                CHRISTMAS_DDAY_DISCOUNT,
                discountAmount,
                giveaway
        );

        // When
        boolean hasBenefits = promotionBenefits.hasBenefits();

        // Then
        assertThat(hasBenefits).isEqualTo(expected);
    }

    private static Stream<Arguments> promotionBenefitsAndHasBenefits() {
        return Stream.of(
                Arguments.of(3000, CHAMPAGNE, 2, true, "할인 혜택 & 증정 혜택 보유"),
                Arguments.of(3000, NO_GIVEAWAY, 0, true, "할인 혜택 보유"),
                Arguments.of(0, CHAMPAGNE, 1, true, "증정품 혜택 보유"),
                Arguments.of(0, NO_GIVEAWAY, 0, false, "혜택 없음")
        );
    }

    @DisplayName("프로모션 혜택 내역을 반환한다.")
    @MethodSource("promotionBenefitsAndHistories")
    @ParameterizedTest(name = "[{index}] \"{4}\"")
    void checkPromotionBenefitHistory(PromotionTitle promotionTitle, DiscountAmount discountAmount, Giveaway giveaway, PromotionBenefitHistory expected, String description) throws Exception {
        // Given
        PromotionBenefits promotionBenefits = new PromotionBenefits(
                promotionTitle,
                discountAmount,
                giveaway
        );

        // When
        PromotionBenefitHistory promotionBenefitHistory = promotionBenefits.checkPromotionBenefitHistory();

        // Then
        assertThat(promotionBenefitHistory).isEqualTo(expected);
    }

    private static Stream<Arguments> promotionBenefitsAndHistories() {
        return Stream.of(
                Arguments.of(
                        CHRISTMAS_DDAY_DISCOUNT,
                        new DiscountAmount(3000),
                        new Giveaway(NO_GIVEAWAY, 0),
                        PromotionBenefitHistory.of(CHRISTMAS_DDAY_DISCOUNT.getTitle(), 3000),
                        CHRISTMAS_DDAY_DISCOUNT.getTitle()
                ),
                Arguments.of(
                        GIVEAWAY,
                        new DiscountAmount(0),
                        new Giveaway(CHAMPAGNE, 2),
                        PromotionBenefitHistory.of(GIVEAWAY.getTitle(), 50000),
                        GIVEAWAY.getTitle()
                ),
                Arguments.of(
                        NO_PROMOTION,
                        new DiscountAmount(0),
                        new Giveaway(NO_GIVEAWAY, 0),
                        PromotionBenefitHistory.of(NO_PROMOTION.getTitle(), 0),
                        NO_PROMOTION.getTitle()
                )
        );
    }
}
