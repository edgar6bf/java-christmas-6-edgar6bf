package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuTest {

    @DisplayName("메뉴 명과 개수를 입력하면 OrderMenu가 생성된다.")
    @Test
    void createOrderMenu() throws Exception {
        // Given
        String menuName = "바비큐립";
        int menuCount = 2;

        // When
        OrderMenu orderMenu = new OrderMenu(menuName, menuCount);

        // Then
        assertThat(orderMenu).isNotNull();
    }

    @DisplayName("유효하지 않은 메뉴 명이 입력되면 예외가 발생한다.")
    @MethodSource("invalidMenuNames")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void createOrderMenuWithInvalidMenuName(String invalidMenuName) throws Exception {
        // Given
        int menuCount = 3;

        // When & Then
        assertThatThrownBy(() -> new OrderMenu(invalidMenuName, menuCount))
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

    @DisplayName("유효하지 않은 메뉴 개수가 입력되면 예외가 발생한다.")
    @MethodSource("invalidMenuCounts")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void createOrderMenuWithInvalidMenuCount(int invalidMenuCount) throws Exception {
        // Given
        String menuName = "제로콜라";

        // When & Then
        assertThatThrownBy(() -> new OrderMenu(menuName, invalidMenuCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 메뉴 개수입니다.");
    }

    private static Stream<Integer> invalidMenuCounts() {
        return Stream.of(-1, -2, 0, 21, 12312, 232323232);
    }

    @DisplayName("주문 메뉴의 총가격을 계산한다.")
    @Test
    void calculateTotalPrice() throws Exception {
        // Given
        OrderMenu orderMenu = new OrderMenu("타파스", 3);
        int expected = 16500;

        // When
        int totalPrice = orderMenu.calculateMenuPrice();

        // Then
        assertThat(totalPrice).isEqualTo(expected);
    }
}
