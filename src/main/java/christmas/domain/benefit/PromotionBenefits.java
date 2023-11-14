package christmas.domain.benefit;

import christmas.constant.PromotionTitle;
import christmas.dto.GiveawayBenefit;
import christmas.dto.PromotionBenefitHistory;

public class PromotionBenefits {

    private final PromotionTitle promotionTitle;
    private final DiscountAmount discountAmount;
    private final Giveaway giveaway;

    public PromotionBenefits(PromotionTitle promotionTitle, DiscountAmount discountAmount, Giveaway giveaway) {
        this.promotionTitle = promotionTitle;
        this.discountAmount = discountAmount;
        this.giveaway = giveaway;
    }

    public boolean hasBenefits() {
        return discountAmount.applicableDiscount() || giveaway.hasGiveaway();
    }

    public PromotionBenefitHistory checkPromotionBenefitHistory() {
        int benefitsAmount = discountAmount.getBenefitAmount() + giveaway.getBenefitAmount();

        return PromotionBenefitHistory.of(promotionTitle.getTitle(), benefitsAmount);
    }

    public int applyBenefit(int price) {
        return discountAmount.applyDiscount(price);
    }

    public GiveawayBenefit applyBenefit() {
        return giveaway.getGiveawayBenefit();
    }
}
