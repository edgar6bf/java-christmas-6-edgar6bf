package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

class OrderDateTest {

    @DisplayName("유효한 범위 내 날짜를 입력하면 OrderDate가 생성된다.")
    @Test
    void createOrderDateWithValidDate() throws Exception {
        // Given
        int validDate = 25;

        // When
        OrderDate orderDate = new OrderDate(validDate);

        // Then
        assertThat(orderDate).isNotNull();
    }

    @DisplayName("유효 범위에 속하지 않는 날짜를 입력하면 예외가 발생한다.")
    @MethodSource("notInRangeDates")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void createOrderDateWithNotInRangeDate(int notInRangeDate) throws Exception {
        // When & Then
        assertThatThrownBy(() -> new OrderDate(notInRangeDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 날짜입니다.");
    }

    private static Stream<Integer> notInRangeDates() {
        return Stream.of(-1, -2, -3, -100, 32, 33, 34, 500);
    }
}
