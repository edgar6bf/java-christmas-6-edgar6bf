package christmas.config;

import christmas.domain.promotion.*;

import java.util.List;

public class PromotionConfig {

    public static List<Promotion> config() {
        return List.of(
                christmasDdayDiscountPromotion(),
                weekdayDiscountPromotion(),
                weekendDiscountPromotion(),
                specialDiscountPromotion(),
                giveawayPromotion()
        );
    }

    private static ChristmasDdayDiscountPromotion christmasDdayDiscountPromotion() {
        return new ChristmasDdayDiscountPromotion();
    }

    private static WeekdayDiscountPromotion weekdayDiscountPromotion() {
        return new WeekdayDiscountPromotion();
    }

    private static WeekendDiscountPromotion weekendDiscountPromotion() {
        return new WeekendDiscountPromotion();
    }

    private static SpecialDiscountPromotion specialDiscountPromotion() {
        return new SpecialDiscountPromotion();
    }

    private static GiveawayPromotion giveawayPromotion() {
        return new GiveawayPromotion();
    }
}
