package christmas.view;

import christmas.domain.console.Console;
import christmas.dto.MenuNameAndCount;

import java.util.*;

import static christmas.view.message.InputMessage.INPUT_EXPECTED_VISIT_DATE_MESSAGE;
import static christmas.view.message.InputMessage.INPUT_ORDER_MENUS_MESSAGE;

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

    public List<MenuNameAndCount> inputOrderMenus() {
        printInputMessage(INPUT_ORDER_MENUS_MESSAGE);

        return parseMenus(console.readLine());
    }

    private List<MenuNameAndCount> parseMenus(String input) {
        int menuNameIndex = 0;
        int menuCountIndex = 1;

        return splitMenusFromInput(input).stream()
                .map(menu -> new MenuNameAndCount(menu[menuNameIndex], parseNumber(menu[menuCountIndex])))
                .toList();
    }

    private List<String[]> splitMenusFromInput(String input) {
        String menuDelimiter = ",";
        String menuNameAndMenuCountDelimiter = "-";

        List<String[]> splitMenus = Arrays.stream(input.split(menuDelimiter))
                .map(menu -> menu.split(menuNameAndMenuCountDelimiter))
                .toList();

        splitMenus.forEach(this::validateSplitMenu);

        return splitMenus;
    }

    private void validateSplitMenu(String[] splitMenu) {
        int validMenuPairCount = 2;

        if (splitMenu.length != validMenuPairCount) {
            throw new IllegalArgumentException("유효하지 않은 주문 입력 형식입니다.");
        }
    }
}
