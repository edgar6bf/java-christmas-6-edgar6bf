package christmas.view.console;

public class ProductionConsole implements Console {

    @Override
    public String readLine() {
        return camp.nextstep.edu.missionutils.Console.readLine();
    }
}
