package christmas.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenusTest {

    @DisplayName("메뉴 목록을 입력하면 OrderMenus를 생성한다.")
    @Test
    void createOrderMenus() throws Exception {
        // Given
        List<OrderMenu> inputOrderMenus = List.of(
                new OrderMenu("바비큐립", 2),
                new OrderMenu("제로콜라", 1)
        );

        // When
        OrderMenus orderMenus = new OrderMenus(inputOrderMenus);

        // Then
        assertThat(orderMenus).isNotNull();
    }

    @DisplayName("음료 메뉴만 포함된 메뉴 목록을 입력하면 예외가 발생한다.")
    @Test
    void createOrderMenusWithOnlyBeverageOrderMenus() throws Exception {
        // Given
        List<OrderMenu> inputOrderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("레드와인", 3)
        );

        // When & Then
        assertThatThrownBy(() -> new OrderMenus(inputOrderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료 메뉴만 주문할 수 없습니다.");
    }

    @DisplayName("중복된 메뉴가 포함된 메뉴 목록을 입력하면 예외가 발생한다.")
    @Test
    void createOrderMenusWithDuplicateOrderMenus() throws Exception {
        // Given
        List<OrderMenu> inputOrderMenus = List.of(
                new OrderMenu("제로콜라", 1),
                new OrderMenu("티본스테이크", 3),
                new OrderMenu("제로콜라", 2)
        );

        // When & Then
        assertThatThrownBy(() -> new OrderMenus(inputOrderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 메뉴는 주문할 수 없습니다.");
    }

    @DisplayName("총주문 개수가 20이 넘는 메뉴 목록을 입력하면 예외가 발생한다.")
    @Test
    void createOrderMenusWithOverSizeOrderMenus() throws Exception {
        // Given
        List<OrderMenu> inputOrderMenus = List.of(
                new OrderMenu("제로콜라", 5),
                new OrderMenu("티본스테이크", 7),
                new OrderMenu("바비큐립", 12)
        );

        // When & Then
        assertThatThrownBy(() -> new OrderMenus(inputOrderMenus))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 총주문 메뉴 개수입니다.");
    }
}
