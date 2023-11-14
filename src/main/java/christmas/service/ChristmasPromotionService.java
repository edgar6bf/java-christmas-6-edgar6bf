package christmas.service;

import christmas.constant.DecemberPromotionBadge;
import christmas.constant.GiveawayMenu;
import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenus;
import christmas.domain.promotion.Promotion;
import christmas.dto.GiveawayBenefit;
import christmas.dto.PromotionApplyResult;
import christmas.dto.PromotionBenefitHistory;

import java.util.List;
import java.util.Objects;

public class ChristmasPromotionService {

    private final List<Promotion> promotions;

    public ChristmasPromotionService(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public PromotionApplyResult checkPromotionBenefits(OrderDate orderDate, OrderMenus orderMenus) {
        List<PromotionBenefits> promotionBenefits = applyAllPromotions(orderDate, orderMenus);
        int preDiscountTotalOrderPrice = orderMenus.getTotalOrderPrice();
        int totalBenefitAmount = calculateTotalBenefitAmount(promotionBenefits);

        return PromotionApplyResult.of(
                orderDate.getDate(),
                orderMenus.getOrderHistory(),
                preDiscountTotalOrderPrice,
                parseGiveaways(promotionBenefits),
                parseBenefitHistories(promotionBenefits),
                totalBenefitAmount,
                calculatePostDiscountExpectedPayment(promotionBenefits, preDiscountTotalOrderPrice),
                DecemberPromotionBadge.of(totalBenefitAmount).getTitle()
        );
    }

    private List<PromotionBenefits> applyAllPromotions(OrderDate orderDate, OrderMenus orderMenus) {
        return promotions.stream()
                .map(promotion -> promotion.applyPromotion(orderDate, orderMenus))
                .filter(PromotionBenefits::hasBenefits)
                .toList();
    }

    private int calculateTotalBenefitAmount(List<PromotionBenefits> promotionBenefits) {
        return promotionBenefits.stream()
                .mapToInt(
                        promotionBenefit -> promotionBenefit.getPromotionBenefitHistory()
                                .benefitAmount()
                )
                .sum();
    }

    private List<GiveawayBenefit> parseGiveaways(List<PromotionBenefits> promotionBenefits) {
        return promotionBenefits.stream()
                .map(PromotionBenefits::applyBenefit)
                .filter(giveawayBenefit -> !Objects.equals(giveawayBenefit.giveawayName(), GiveawayMenu.NO_GIVEAWAY.getName()))
                .toList();
    }

    private List<PromotionBenefitHistory> parseBenefitHistories(List<PromotionBenefits> promotionBenefits) {
        return promotionBenefits.stream()
                .map(PromotionBenefits::getPromotionBenefitHistory)
                .toList();
    }

    private int calculatePostDiscountExpectedPayment(List<PromotionBenefits> promotionBenefits, int preDiscountTotalOrderAmount) {
        return promotionBenefits.stream()
                .reduce(
                        preDiscountTotalOrderAmount,
                        (total, benefits) -> benefits.applyBenefit(total),
                        (a, b) -> b
                );
    }
}
