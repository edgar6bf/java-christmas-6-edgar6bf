package christmas.domain.scanner;

import christmas.domain.scanner.console.Console;

public class Scanner {

    private final Console console;

    public Scanner(Console console) {
        this.console = console;
    }

    public int inputNumberWithInRange(int startRange, int endRange) {
        int input = parseNumber(console.readLine());

        validateInputRange(input, startRange, endRange);

        return input;
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }

    private void validateInputRange(int input, int startRange, int endRange) {
        if (input < startRange || input > endRange) {
            throw new IllegalArgumentException("입력값의 범위가 유효하지 않습니다.");
        }
    }
}
