package christmas.controller;

import christmas.domain.order.OrderDate;
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
        OrderDate orderDate = inputOrderDate();
    }

    private OrderDate inputOrderDate() {
        try {
            int expectedVisitDate = inputView.inputExpectedVisitDate();

            return new OrderDate(expectedVisitDate);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(INPUT_EXPECTED_VISIT_DATE_ERROR_MESSAGE);

            return inputOrderDate();
        }
    }
}
