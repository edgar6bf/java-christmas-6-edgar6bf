package christmas.view;

import christmas.domain.console.Console;
import christmas.dto.MenuNameAndCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[InputView] : 사용자 입력 테스트")
class InputViewTest {

    @DisplayName("예상 방문 날짜를 입력받는다.")
    @Test
    void inputExpectedVisitDate() throws Exception {
        // Given
        String validInputValue = "25";
        int expectedValue = Integer.parseInt(validInputValue);
        InputView inputView = new InputView(setTestConsole(validInputValue));

        // When
        int inputNumber = inputView.inputExpectedVisitDate();

        // Then
        assertThat(inputNumber).isEqualTo(expectedValue);
    }

    private Console setTestConsole(String input) {
        return () -> input;
    }

    @DisplayName("정수가 아닌 날짜가 입력되면 예외가 발생한다.")
    @MethodSource("invalidInputValues")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void inputNotNumberValue(String invalidInputValue) throws Exception {
        // Given
        InputView inputView = new InputView(setTestConsole(invalidInputValue));

        // When & Then
        assertThatThrownBy(inputView::inputExpectedVisitDate)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("입력값이 정수가 아닙니다.");
    }

    private static Stream<String> invalidInputValues() {
        return Stream.of("a", " 3", "4 ", "ffefe", "feafef-3 3", "-4f");
    }

    @DisplayName("주문 메뉴 정보를 입력받는다.")
    @Test
    void inputOrderMenus() throws Exception {
        // Given
        String inputValue = "타파스-1,제로콜라-2";
        List<String> expectedMenuNames = List.of("타파스", "제로콜라");
        List<Integer> expectedMenuCounts = List.of(1, 2);
        InputView inputView = new InputView(setTestConsole(inputValue));

        // When
        List<MenuNameAndCount> menuNameAndCounts = inputView.inputOrderMenus();

        // Then
        assertThat(menuNameAndCounts.get(0).name()).isEqualTo(expectedMenuNames.get(0));
        assertThat(menuNameAndCounts.get(0).count()).isEqualTo(expectedMenuCounts.get(0));
        assertThat(menuNameAndCounts.get(1).name()).isEqualTo(expectedMenuNames.get(1));
        assertThat(menuNameAndCounts.get(1).count()).isEqualTo(expectedMenuCounts.get(1));
    }

    @DisplayName("유효하지 않은 형식의 주문 메뉴를 입력받으면 예외가 발생한다.")
    @MethodSource("invalidInputOrderMenus")
    @ParameterizedTest(name = "[{index}] \"{0}\" => Throw Exception")
    void inputInvalidOrderMenus(String invalidInput) throws Exception {
        // Given
        InputView inputView = new InputView(setTestConsole(invalidInput));

        // When & Then
        assertThatThrownBy(inputView::inputOrderMenus)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 주문 입력 형식입니다.");
    }

    private static Stream<String> invalidInputOrderMenus() {
        return Stream.of(
                "티본스테이크-1.바비큐립-1.초코케이크-2.제로콜라-1",
                "티본스테이크- 1.바비큐립- 1.초코케이크- 2.제로콜라- 1",
                "티본스테이크 1,바비큐립 1,초코케이크 2, 제로콜라 1",
                "티본스테이크:1,바비큐립:1,초코케이크:2,제로콜라:1"
        );
    }
}
