package christmas.domain.scanner;

import christmas.domain.scanner.console.Console;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScannerTest {

    @DisplayName("지정한 범위 내 정수를 입력받는다.")
    @Test
    void inputNumberInRange() throws Exception {
        // Given
        int startRange = 1;
        int endRange = 31;
        String validInputValue = "25";
        Scanner scanner = new Scanner(setTestConsole(validInputValue));

        // When
        int inputNumber = scanner.inputNumberWithInRange(startRange, endRange);

        // Then
        assertThat(inputNumber).isBetween(startRange, endRange);
    }

    private Console setTestConsole(String input) {
        return () -> input;
    }

    @DisplayName("지정한 범위 밖 정수 혹은 정수 외 값을 입력하면 예외가 발생한다.")
    @MethodSource("invalidInputValues")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {1}")
    void inputInvalidValue(String invalidInputValue) throws Exception {
        // Given
        int startRange = 1;
        int endRange = 31;
        Scanner scanner = new Scanner(setTestConsole(invalidInputValue));

        // When & Then
        assertThatThrownBy(() -> scanner.inputNumberWithInRange(startRange, endRange))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<String> invalidInputValues() {
        return Stream.of(
                "-1",
                "-13",
                "32",
                "32343",
                "aafefe",
                "d e e fef e e e e ef efe e d",
                "d e e fef e e e  543 343434"
        );
    }
}
