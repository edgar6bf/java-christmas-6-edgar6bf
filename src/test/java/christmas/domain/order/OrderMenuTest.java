package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuTest {

    @DisplayName("메뉴 이름과 주문 개수를 입력하면 OrderMenu가 생성된다.")
    @Test
    void createOrderMenu() throws Exception {
        // Given
        String menuName = "바비큐립";
        int orderCount = 2;

        // When
        OrderMenu orderMenu = new OrderMenu(menuName, orderCount);

        // Then
        assertThat(orderMenu.getMenuName()).isEqualTo(menuName);
        assertThat(orderMenu.getOrderCount()).isEqualTo(orderCount);
    }

    @DisplayName("유효하지 않은 메뉴 이름이 입력되면 예외가 발생한다.")
    @MethodSource("invalidMenuNames")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void createOrderMenuWithInvalidMenuName(String invalidMenuName) throws Exception {
        // Given
        int orderCount = 3;

        // When & Then
        assertThatThrownBy(() -> new OrderMenu(invalidMenuName, orderCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 메뉴입니다.");
    }

    private static Stream<String> invalidMenuNames() {
        return Stream.of(
                "제트본스테이크",
                "환타",
                "볶음밥",
                "제로콜라3개",
                "우테코 학식"
        );
    }

    @DisplayName("유효하지 않은 주문 개수가 입력되면 예외가 발생한다.")
    @MethodSource("invalidOrderCounts")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void createOrderMenuWithInvalidOrderCount(int invalidOrderCount) throws Exception {
        // Given
        String menuName = "제로콜라";

        // When & Then
        assertThatThrownBy(() -> new OrderMenu(menuName, invalidOrderCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문 개수입니다.");
    }

    private static Stream<Integer> invalidOrderCounts() {
        return Stream.of(-1, -2, 0, 21, 12312, 232323232);
    }

    @DisplayName("총주문 가격을 계산한다.")
    @Test
    void calculateTotalOrderPrice() throws Exception {
        // Given
        OrderMenu orderMenu = new OrderMenu("타파스", 3);
        int expected = 16500;

        // When
        int totalOrderPrice = orderMenu.calculateTotalOrderPrice();

        // Then
        assertThat(totalOrderPrice).isEqualTo(expected);
    }
}
