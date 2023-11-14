package christmas.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.constant.DecemberPromotionBadge.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[DecemberPromotionBadge] : 이벤트 배지 테스트")
class DecemberPromotionBadgeTest {

    @DisplayName("총혜택 금액을 입력하면 적절한 이벤트 배지를 부여한다.")
    @MethodSource("totalBenefitAmountsAndBadges")
    @ParameterizedTest(name = "[{index}] \"{0}원\" => {2}")
    void getPromotionBadge(int totalBenefitAmount, DecemberPromotionBadge expected, String badgeTitle) throws Exception {
        // When
        DecemberPromotionBadge decemberPromotionBadge = of(totalBenefitAmount);

        // Then
        assertThat(decemberPromotionBadge).isEqualTo(expected);
    }

    private static Stream<Arguments> totalBenefitAmountsAndBadges() {
        return Stream.of(
                Arguments.of(5200, STAR, STAR.getTitle()),
                Arguments.of(12300, TREE, TREE.getTitle()),
                Arguments.of(25900, SANTA, SANTA.getTitle()),
                Arguments.of(2000, NO_BADGE, NO_BADGE.getTitle()),
                Arguments.of(-1500, NO_BADGE, NO_BADGE.getTitle())
        );
    }
}
