package christmas.domain.promotion;

import christmas.constant.Giveaway;
import christmas.constant.PromotionTitle;

import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.NO_PROMOTION;

public class PromotionBenefits {

    private static final int ZERO_AMOUNT = 0;

    private final PromotionTitle promotionTitle;
    private final int discountPrice;
    private final Giveaway giveaway;
    private final int giveawayCount;

    public PromotionBenefits(
            PromotionTitle promotionTitle,
            int discountPrice,
            Giveaway giveaway,
            int giveawayCount
    ) {
        validateDiscountPrice(discountPrice);
        validateGiveaway(giveaway, giveawayCount);

        this.promotionTitle = promotionTitle;
        this.discountPrice = discountPrice;
        this.giveaway = giveaway;
        this.giveawayCount = giveawayCount;
    }

    private void validateDiscountPrice(int discountPrice) {
        if (discountPrice < ZERO_AMOUNT) {
            throw new IllegalArgumentException("유효하지 않은 할인 가격 입니다.");
        }
    }

    private void validateGiveaway(Giveaway giveaway, int giveawayCount) {
        if (giveawayCount < ZERO_AMOUNT
                || (giveaway == NO_GIVEAWAY && giveawayCount != 0)
        ) {
            throw new IllegalArgumentException("유효하지 않은 증정품 개수 입니다.");
        }
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public Giveaway getGiveaway() {
        return giveaway;
    }

    public boolean hasBenefits() {
        return promotionTitle != NO_PROMOTION
                && discountPrice > ZERO_AMOUNT
                && giveaway != NO_GIVEAWAY;
    }

    public int getGiveawayCount() {
        return giveawayCount;
    }
}
