package christmas.view;

import christmas.domain.console.Console;

import static christmas.view.message.InputMessage.INPUT_EXPECTED_VISIT_DATE_MESSAGE;

public class InputView {

    private final Console console;

    public InputView(Console console) {
        this.console = console;
    }

    public int inputExpectedVisitDate() {
        printInputMessage(INPUT_EXPECTED_VISIT_DATE_MESSAGE);

        return parseNumber(console.readLine());
    }

    private void printInputMessage(String message) {
        System.out.println(message);
    }

    private int parseNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력값이 정수가 아닙니다.");
        }
    }
}
