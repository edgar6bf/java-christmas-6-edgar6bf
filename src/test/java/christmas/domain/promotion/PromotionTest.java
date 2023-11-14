package christmas.domain.promotion;

import christmas.domain.benefit.PromotionBenefits;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.PromotionTitle.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("[Promotion] : 이벤트 공통 테스트")
class PromotionTest {

    @DisplayName("이벤트에서 지정한 조건 금액보다 낮은 총주문 금액의 주문 목록을 입력하면 할인 혜택을 받을 수 없다.")
    @MethodSource("promotionsAndValidOrderDates")
    @ParameterizedTest(name = "[{index}] {2} 테스트")
    void applyPromotionWithUnderMinimumTotalOrderPrice(Promotion promotion, OrderDate orderDate, String promotionTitle) throws Exception {
        // Given
        OrderMenus orderMenusHasInvalidTotalOrderPrice = new OrderMenus(
                List.of(new OrderMenu("타파스", 1))
        );

        // When
        PromotionBenefits promotionBenefits = promotion.applyPromotion(orderDate, orderMenusHasInvalidTotalOrderPrice);

        // Then
        assertThat(promotionBenefits.hasBenefits()).isFalse();
    }

    private static Stream<Arguments> promotionsAndValidOrderDates() {
        return Stream.of(
                arguments(new ChristmasDdayDiscountPromotion(), new OrderDate(23), CHRISTMAS_DDAY_DISCOUNT.getTitle()),
                arguments(new WeekdayDiscountPromotion(), new OrderDate(3), WEEKDAY_DISCOUNT.getTitle()),
                arguments(new WeekendDiscountPromotion(), new OrderDate(2), WEEKEND_DISCOUNT.getTitle()),
                arguments(new SpecialDiscountPromotion(), new OrderDate(3), SPECIAL_DISCOUNT.getTitle()),
                arguments(new GiveawayPromotion(), new OrderDate(3), GIVEAWAY.getTitle())
        );
    }

    @DisplayName("이벤트 기간에 포함되지 않는 주문 날짜를 입력하면 할인 혜택을 받을 수 없다.")
    @MethodSource("promotionsAndInvalidOrderDates")
    @ParameterizedTest(name = "[{index}] {2} 테스트")
    void applyPromotionWithInvalidOrderDate(Promotion promotion, OrderDate orderDate, String promotionTitle) throws Exception {
        // Given
        OrderMenus orderMenus = new OrderMenus(
                List.of(
                        new OrderMenu("티본스테이크", 10),
                        new OrderMenu("초코케이크", 5)
                )
        );

        // When
        PromotionBenefits promotionBenefits = promotion.applyPromotion(orderDate, orderMenus);

        // Then
        assertThat(promotionBenefits.hasBenefits()).isFalse();
    }

    private static Stream<Arguments> promotionsAndInvalidOrderDates() {
        return Stream.of(
                arguments(new ChristmasDdayDiscountPromotion(), new OrderDate(28), CHRISTMAS_DDAY_DISCOUNT.getTitle()),
                arguments(new WeekdayDiscountPromotion(), new OrderDate(1), WEEKDAY_DISCOUNT.getTitle()),
                arguments(new WeekendDiscountPromotion(), new OrderDate(3), WEEKEND_DISCOUNT.getTitle()),
                arguments(new SpecialDiscountPromotion(), new OrderDate(4), SPECIAL_DISCOUNT.getTitle())
        );
    }
}
