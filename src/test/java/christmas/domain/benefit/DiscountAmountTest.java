package christmas.domain.benefit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("[DiscountAmount] : 할인 금액 테스트")
class DiscountAmountTest {

    @DisplayName("할인 금액을 입력하면 DiscountAmount가 생성된다.")
    @Test
    void createDiscountAmount() throws Exception {
        // Given
        int discountAmountValue = 3000;

        // When
        DiscountAmount discountAmount = new DiscountAmount(discountAmountValue);

        // Then
        assertThat(discountAmount).isNotNull();
    }

    @DisplayName("할인 금액으로 음수가 입력되면 예외가 발생한다.")
    @Test
    void createDiscountAmountWithNegativeValue() throws Exception {
        // Given
        int discountAmountValue = -2000;

        // When & Then
        assertThatThrownBy(() -> new DiscountAmount(discountAmountValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 할인 금액 입니다.");
    }

    @DisplayName("할인 혜택이 존재하는지 여부를 반환한다.")
    @MethodSource("discountAmountAndHasDiscountBenefits")
    @ParameterizedTest(name = "[{index}] 할인 금액 : {0}원 => {1}")
    void applicableDiscount(int discountAmountValue, boolean expected) throws Exception {
        // Given
        DiscountAmount discountAmount = new DiscountAmount(discountAmountValue);

        // When
        boolean hasDiscountBenefit = discountAmount.applicableDiscount();

        // Then
        assertThat(hasDiscountBenefit).isEqualTo(expected);
    }

    private static Stream<Arguments> discountAmountAndHasDiscountBenefits() {
        return Stream.of(
                Arguments.of(3000, true),
                Arguments.of(0, false)
        );
    }

    @DisplayName("원금을 입력하면 할인 금액을 적용한다.")
    @Test
    void applyDiscount() throws Exception {
        // Given
        DiscountAmount discountAmount = new DiscountAmount(3000);
        int principal = 7000;
        int expected = 4000;

        // When
        int discounted = discountAmount.applyDiscount(principal);

        // Then
        assertThat(discounted).isEqualTo(expected);
    }
}
