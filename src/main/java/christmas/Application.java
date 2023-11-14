package christmas;

import christmas.config.AppConfig;
import christmas.controller.ChristmasPromotionController;

public class Application {
    public static void main(String[] args) {
        ChristmasPromotionController controller = AppConfig.config();
        controller.run();
    }
}
