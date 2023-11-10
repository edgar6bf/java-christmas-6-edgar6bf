package christmas.view;

import static christmas.view.message.OutputMessage.PROGRAM_START_MESSAGE;

public class OutputView {

    public void printProgramStartMessage() {
        System.out.println(PROGRAM_START_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }
}
