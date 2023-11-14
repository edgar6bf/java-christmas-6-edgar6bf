package christmas.dto;

public record PromotionBenefitHistory(String promotionTitle, int benefitAmount) {

    public static PromotionBenefitHistory of(String promotionTitle, int benefitAmount) {
        return new PromotionBenefitHistory(promotionTitle, benefitAmount);
    }
}
