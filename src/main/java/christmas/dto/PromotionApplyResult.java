package christmas.dto;

import java.util.List;

public record PromotionApplyResult(
        int orderDate,
        List<MenuNameAndCount> orderMenus,
        int preDiscountTotalOrderAmount,
        List<GiveawayBenefit> giveawayBenefits,
        List<PromotionBenefitHistory> promotionBenefitHistories,
        int totalBenefitAmount,
        int postDiscountExpectedPayment,
        String decemberPromotionBadge
) {

    public static PromotionApplyResult of(
            int orderDate,
            List<MenuNameAndCount> orderMenus,
            int preDiscountTotalOrderAmount,
            List<GiveawayBenefit> giveawayBenefits,
            List<PromotionBenefitHistory> promotionBenefitHistories,
            int totalBenefitAmount,
            int postDiscountExpectedPayment,
            String decemberPromotionBadge
    ) {
        return new PromotionApplyResult(
                orderDate,
                orderMenus,
                preDiscountTotalOrderAmount,
                giveawayBenefits,
                promotionBenefitHistories,
                totalBenefitAmount,
                postDiscountExpectedPayment,
                decemberPromotionBadge
        );
    }
}
