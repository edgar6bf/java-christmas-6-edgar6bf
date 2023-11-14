package christmas.constant;

import java.util.Arrays;
import java.util.Comparator;

public enum DecemberPromotionBadge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000),
    NO_BADGE("없음", 0);

    private final String title;
    private final int minimumConditionAmount;

    DecemberPromotionBadge(String title, int minimumConditionAmount) {
        this.title = title;
        this.minimumConditionAmount = minimumConditionAmount;
    }

    public static DecemberPromotionBadge of(int benefitAmount) {
        return Arrays.stream(DecemberPromotionBadge.values())
                .filter(badge -> badge.getMinimumConditionAmount() < benefitAmount)
                .max(Comparator.comparing(DecemberPromotionBadge::getMinimumConditionAmount))
                .orElse(NO_BADGE);
    }

    public String getTitle() {
        return title;
    }

    public int getMinimumConditionAmount() {
        return minimumConditionAmount;
    }
}
