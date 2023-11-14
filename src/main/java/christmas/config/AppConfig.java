package christmas.config;

import christmas.controller.ChristmasPromotionController;
import christmas.domain.console.Console;
import christmas.domain.console.ProductionConsole;
import christmas.service.ChristmasPromotionService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class AppConfig {

    public static ChristmasPromotionController config() {
        return christmasPromotionController();
    }

    private static ChristmasPromotionController christmasPromotionController() {
        return new ChristmasPromotionController(inputView(), outputView(), christmasPromotionService());
    }

    private static InputView inputView() {
        return new InputView(console());
    }

    private static Console console() {
        return new ProductionConsole();
    }

    private static OutputView outputView() {
        return new OutputView();
    }

    private static ChristmasPromotionService christmasPromotionService() {
        return new ChristmasPromotionService(PromotionConfig.config());
    }
}
