package christmas.view;

import christmas.dto.GiveawayBenefit;
import christmas.dto.MenuNameAndCount;
import christmas.dto.PromotionApplyResult;
import christmas.dto.PromotionBenefitHistory;

import java.text.NumberFormat;
import java.util.List;

import static christmas.view.message.OutputMessage.*;

public class OutputView {

    public void printProgramStartMessage() {
        System.out.println(PROGRAM_START_MESSAGE);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public void printPromotionBenefits(PromotionApplyResult result) {
        printPromotionBenefitsTitle(result.orderDate());
        printOrderMenus(result.orderMenus());
        printPreDiscountTotalOrderAmount(result.preDiscountTotalOrderAmount());
        printGiveaways(result.giveawayBenefits());
        printBenefitHistories(result.promotionBenefitHistories());
        printTotalBenefitAmount(result.totalBenefitAmount());
        printPostDiscountExpectedPayment(result.postDiscountExpectedPayment());
        printDecemberPromotionBadge(result.decemberPromotionBadge());
    }

    private void printPromotionBenefitsTitle(int date) {
        System.out.println("12월 " + date + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
    }

    private void printOrderMenus(List<MenuNameAndCount> orderMenus) {
        System.out.println(ORDER_MENUS_HEADER_MESSAGE);

        if (orderMenus.isEmpty()) {
            printNoBenefitMessage();

            return;
        }
        orderMenus.forEach(menu -> printMenuItem(menu.name(), menu.count()));
        System.out.println();
    }

    private void printNoBenefitMessage() {
        System.out.println(NO_BENEFIT_MESSAGE);
        System.out.println();
    }

    private void printMenuItem(String name, int count) {
        System.out.println(name + " " + count + "개");
    }

    private void printPreDiscountTotalOrderAmount(int preDiscountTotalOrderAmount) {
        String printAmountValue = convertPriceValueToPrintFormat(preDiscountTotalOrderAmount);
        System.out.println(PRE_DISCOUNT_TOTAL_ORDER_AMOUNT_HEADER_MESSAGE);
        System.out.println(printAmountValue);
        System.out.println();
    }

    private String convertPriceValueToPrintFormat(int price) {
        return NumberFormat.getInstance()
                .format(price)
                + "원";
    }

    private void printGiveaways(List<GiveawayBenefit> giveawayBenefits) {
        System.out.println(GIVEAWAY_MENUS_HEADER_MESSAGE);

        if (giveawayBenefits.isEmpty()) {
            printNoBenefitMessage();

            return;
        }
        giveawayBenefits.forEach(giveaway -> printMenuItem(giveaway.giveawayName(), giveaway.count()));
        System.out.println();
    }

    private void printBenefitHistories(List<PromotionBenefitHistory> promotionBenefitHistories) {
        System.out.println(PROMOTION_BENEFIT_HISTORIES_HEADER_MESSAGE);

        if (promotionBenefitHistories.isEmpty()) {
            printNoBenefitMessage();

            return;
        }
        promotionBenefitHistories.forEach(
                promotionBenefitHistory -> printBenefitItem(
                        promotionBenefitHistory.promotionTitle(),
                        convertPriceValueToPrintFormat(promotionBenefitHistory.benefitAmount())
                )
        );
        System.out.println();
    }

    private void printBenefitItem(String title, String benefitAmount) {
        System.out.println(title + ": -" + benefitAmount);
    }

    private void printTotalBenefitAmount(int totalBenefitAmount) {
        String printFormat = convertPriceValueToPrintFormat(totalBenefitAmount);

        if (totalBenefitAmount > 0) {
            printFormat = "-" + printFormat;
        }

        System.out.println(TOTAL_BENEFIT_AMOUNT_HEADER_MESSAGE);
        System.out.println(printFormat);
        System.out.println();
    }

    private void printPostDiscountExpectedPayment(int postDiscountExpectedPayment) {
        System.out.println(POST_DISCOUNT_EXPECTED_PAYMENT_HEADER_MESSAGE);
        System.out.println(convertPriceValueToPrintFormat(postDiscountExpectedPayment));
        System.out.println();
    }

    private void printDecemberPromotionBadge(String decemberPromotionBadge) {
        System.out.println(DECEMBER_PROMOTION_BADGE_HEADER_MESSAGE);
        System.out.println(decemberPromotionBadge);
    }
}
