package christmas.controller;

import christmas.view.InputView;
import christmas.view.OutputView;

import static christmas.view.message.ErrorMessage.INPUT_EXPECTED_VISIT_DATE_ERROR_MESSAGE;

public class ChristmasPromotionController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasPromotionController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printProgramStartMessage();
        int expectedVisitDate = inputExpectedVisitDate();
        System.out.println("expectedVisitDate = " + expectedVisitDate);
    }

    private int inputExpectedVisitDate() {
        int startDate = 1;
        int endDate = 31;

        try {
            return inputView.inputExpectedVisitDate(startDate, endDate);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(INPUT_EXPECTED_VISIT_DATE_ERROR_MESSAGE);

            return inputExpectedVisitDate();
        }
    }
}
