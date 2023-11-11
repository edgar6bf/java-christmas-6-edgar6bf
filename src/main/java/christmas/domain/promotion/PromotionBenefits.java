package christmas.domain.promotion;

import christmas.constant.Giveaway;
import christmas.constant.PromotionTitle;

import static christmas.constant.Giveaway.NO_GIVEAWAY;
import static christmas.constant.PromotionTitle.NO_PROMOTION;

public class PromotionBenefits {

    private static final int ZERO_DISCOUNT_PRICE = 0;

    private final PromotionTitle promotionTitle;
    private final int discountPrice;
    private final Giveaway giveaway;

    public PromotionBenefits(PromotionTitle promotionTitle, int discountPrice, Giveaway giveaway) {
        validateDiscountPrice(discountPrice);
        this.promotionTitle = promotionTitle;
        this.discountPrice = discountPrice;
        this.giveaway = giveaway;
    }

    private void validateDiscountPrice(int discountPrice) {
        if (discountPrice < ZERO_DISCOUNT_PRICE) {
            throw new IllegalArgumentException("유효하지 않은 할인 가격 입니다.");
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
                && discountPrice > ZERO_DISCOUNT_PRICE
                && giveaway != NO_GIVEAWAY;
    }
}
