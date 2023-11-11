package christmas.domain.order;

import christmas.constant.MenuCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.MenuCategory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[OrderMenus] : 주문 메뉴 목록 테스트")
class OrderMenusTest {

    @DisplayName("주문 메뉴 목록을 입력하면 OrderMenus를 생성한다.")
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
    void createOrderMenusWithHasOnlyBeverageOrderMenu() throws Exception {
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

    @DisplayName("총주문 개수가 20이 넘는 목록을 입력하면 예외가 발생한다.")
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
                .hasMessage("유효하지 않은 총주문 개수입니다.");
    }

    @DisplayName("총주문 금액이 입력된 가격 이상인지 검사한다.")
    @MethodSource("priceAndResult")
    @ParameterizedTest(name = "[{index}] \"{0}원\" => {1}")
    void hasOverOrEqualTotalPrice(int price, boolean result) throws Exception {
        // Given
        OrderMenus orderMenus = new OrderMenus(
                List.of(
                        new OrderMenu("제로콜라", 5),
                        new OrderMenu("티본스테이크", 1)
                )
        );

        // When
        boolean isOverOrEqualTotalPrice = orderMenus.hasOverOrEqualTotalOrderPrice(price);

        // Then
        assertThat(isOverOrEqualTotalPrice).isEqualTo(result);
    }

    private static Stream<Arguments> priceAndResult() {
        return Stream.of(
                Arguments.of(10000, true),
                Arguments.of(500000, false)
        );
    }

    @DisplayName("주문 메뉴 목록에 입력된 카테고리의 메뉴가 포함되어 있는지 검사한다.")
    @MethodSource("categoryAndHasMenu")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void hasMenusInCategory(MenuCategory category, boolean result) throws Exception {
        // Given
        OrderMenus orderMenus = new OrderMenus(
                List.of(
                        new OrderMenu("제로콜라", 5),
                        new OrderMenu("티본스테이크", 1)
                )
        );

        // When
        boolean hasMenu = orderMenus.hasMenusInCategory(category);

        // Then
        assertThat(hasMenu).isEqualTo(result);
    }

    private static Stream<Arguments> categoryAndHasMenu() {
        return Stream.of(
                Arguments.of(BEVERAGE, true),
                Arguments.of(MAIN, true),
                Arguments.of(DESSERT, false)
        );
    }

    @DisplayName("주문 메뉴 목록에서 입력된 카테고리의 메뉴 개수를 계산한다.")
    @MethodSource("categoryAndExceptedCount")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void getMenuCountInCategory(MenuCategory category, int exceptedCount) throws Exception {
        // Given
        OrderMenus orderMenus = new OrderMenus(
                List.of(
                        new OrderMenu("제로콜라", 5),
                        new OrderMenu("티본스테이크", 1)
                )
        );

        // When
        int menuCountInCategory = orderMenus.getOrderCountInCategory(category);

        // Then
        assertThat(menuCountInCategory).isEqualTo(exceptedCount);
    }

    private static Stream<Arguments> categoryAndExceptedCount() {
        return Stream.of(
                Arguments.of(BEVERAGE, 5),
                Arguments.of(MAIN, 1),
                Arguments.of(DESSERT, 0)
        );
    }
}
