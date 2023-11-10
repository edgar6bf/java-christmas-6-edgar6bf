package christmas.view;

import christmas.domain.scanner.Scanner;

import static christmas.view.message.InputMessage.INPUT_EXPECTED_VISIT_DATE_MESSAGE;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputExpectedVisitDate() {
        printInputMessage(INPUT_EXPECTED_VISIT_DATE_MESSAGE);

        return scanner.inputNumber();
    }

    private void printInputMessage(String message) {
        System.out.println(message);
    }
}
