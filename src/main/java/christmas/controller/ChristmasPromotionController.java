package christmas.controller;

import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.dto.MenuNameAndCount;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

import static christmas.view.message.ErrorMessage.INPUT_EXPECTED_VISIT_DATE_ERROR_MESSAGE;
import static christmas.view.message.ErrorMessage.INPUT_ORDER_MENUS_ERROR_MESSAGE;

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
        OrderMenus orderMenus = inputOrderMenus();
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

    private OrderMenus inputOrderMenus() {
        try {
            List<MenuNameAndCount> inputMenus = inputView.inputOrderMenus();

            return convertInputMenusToOrderMenus(inputMenus);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(INPUT_ORDER_MENUS_ERROR_MESSAGE);

            return inputOrderMenus();
        }
    }

    private OrderMenus convertInputMenusToOrderMenus(List<MenuNameAndCount> inputMenus) {
        List<OrderMenu> orderMenus = inputMenus.stream()
                .map(inputMenu -> new OrderMenu(inputMenu.name(), inputMenu.count()))
                .toList();

        return new OrderMenus(orderMenus);
    }
}
