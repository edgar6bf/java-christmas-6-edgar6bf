package christmas.domain.scanner;

import christmas.domain.scanner.console.Console;

public class Scanner {

    private final Console console;

    public Scanner(Console console) {
        this.console = console;
    }

    public int inputNumber() {
        return parseNumber(console.readLine());
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }
}
