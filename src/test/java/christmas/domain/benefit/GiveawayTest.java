package christmas.domain.benefit;

import christmas.constant.GiveawayMenu;
import christmas.dto.GiveawayBenefit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.constant.GiveawayMenu.CHAMPAGNE;
import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[Giveaway] : 증정품 테스트")
class GiveawayTest {

    @DisplayName("증정품 항목과 개수를 입력하면 Giveaway가 생성된다.")
    @Test
    void createGiveaway() throws Exception {
        // Given
        GiveawayMenu giveawayMenu = CHAMPAGNE;
        int count = 1;

        // When
        Giveaway giveaway = new Giveaway(giveawayMenu, count);

        // Then
        assertThat(giveaway).isNotNull();
    }

    @DisplayName("증정품 개수로 음수가 입력되면 예외가 발생한다.")
    @Test
    void createGiveawayWithNegativeCountValue() throws Exception {
        // Given
        GiveawayMenu giveawayMenu = CHAMPAGNE;
        int count = -3;

        // When & Then
        assertThatThrownBy(() -> new Giveaway(giveawayMenu, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 증정품 개수 입니다.");
    }

    @DisplayName("증정품이 없으면서 0이 아닌 증정품 개수가 입력되면 예외가 발생한다.")
    @Test
    void createGiveawayWithNoGiveawayAndNotZeroCountValue() throws Exception {
        // Given
        GiveawayMenu giveawayMenu = NO_GIVEAWAY;
        int count = 3;

        // When & Then
        assertThatThrownBy(() -> new Giveaway(giveawayMenu, count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 증정품 개수 입니다.");
    }

    @DisplayName("증정품 혜택이 존재하는지 여부를 반환한다.")
    @MethodSource("giveawayAndHasGiveawayBenefits")
    @ParameterizedTest(name = "[{index}] 설정된 증정품 항목 : \"{3}\" => {2}")
    void returnHasDiscountBenefit(GiveawayMenu giveawayMenu, int count, boolean expected, String giveawayName) throws Exception {
        // Given
        Giveaway giveaway = new Giveaway(giveawayMenu, count);

        // When
        boolean hasGiveaway = giveaway.hasGiveaway();

        // Then
        assertThat(hasGiveaway).isEqualTo(expected);
    }

    private static Stream<Arguments> giveawayAndHasGiveawayBenefits() {
        return Stream.of(
                Arguments.of(CHAMPAGNE, 1, true, CHAMPAGNE.getName()),
                Arguments.of(NO_GIVEAWAY, 0, false, NO_GIVEAWAY.getName())
        );
    }

    @DisplayName("증정품 혜택의 금액 가치를 반환한다.")
    @Test
    void getBenefitAmount() throws Exception {
        // Given
        Giveaway giveaway = new Giveaway(CHAMPAGNE, 3);
        int expected = CHAMPAGNE.getPrice() * 3;

        // When
        int benefitAmount = giveaway.getBenefitAmount();

        // Then
        assertThat(benefitAmount).isEqualTo(expected);
    }

    @DisplayName("증정품 혜택을 반환한다.")
    @Test
    void getGiveawayBenefit() throws Exception {
        // Given
        Giveaway giveaway = new Giveaway(CHAMPAGNE, 3);
        String expectedGiveawayName = CHAMPAGNE.getName();
        int expectedGiveawayCount = 3;

        // When
        GiveawayBenefit giveawayBenefit = giveaway.getGiveawayBenefit();

        // Then
        assertThat(giveawayBenefit.giveawayName()).isEqualTo(expectedGiveawayName);
        assertThat(giveawayBenefit.count()).isEqualTo(expectedGiveawayCount);
    }
}
