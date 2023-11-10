package christmas.view;

import christmas.domain.console.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}
