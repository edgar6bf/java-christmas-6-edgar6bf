package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
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

    @DisplayName("입력된 기간에 주문 날짜가 포함되는지 여부를 검사한다.")
    @MethodSource("dateAndResults")
    @ParameterizedTest(name = "[{index}] \"{0}일\" => {1}")
    void isOrderDateInclude(int date, boolean result) throws Exception {
        // Given
        int startDate = 1;
        int endDate = 25;
        OrderDate orderDate = new OrderDate(date);

        // When
        boolean isOrderDateInclude = orderDate.isOrderDateInclude(startDate, endDate);

        // Then
        assertThat(isOrderDateInclude).isEqualTo(result);
    }

    private static Stream<Arguments> dateAndResults() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(20, true),
                Arguments.of(27, false),
                Arguments.of(29, false)
        );
    }

    @DisplayName("입력된 날짜와 주문 날짜의 차이를 계산한다.")
    @Test
    void calculateDateDifference() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(23);
        int inputDate = 25;
        int expected = 2;

        // When
        int difference = orderDate.calculateDateDifference(inputDate);

        // Then
        assertThat(difference).isEqualTo(expected);
    }

    @DisplayName("입력된 날짜 목록에 주문 날짜가 포함되는지 검사한다.")
    @Test
    void isOrderDateIncludeInDates() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(23);
        List<Integer> dates = List.of(1, 3, 5, 23);

        // When
        boolean isOrderDateInclude = orderDate.isOrderDateInclude(dates);

        // Then
        assertThat(isOrderDateInclude).isEqualTo(true);
    }
}
