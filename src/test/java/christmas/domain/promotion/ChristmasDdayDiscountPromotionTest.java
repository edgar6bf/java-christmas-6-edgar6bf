package christmas.domain.promotion;

import christmas.constant.Giveaway;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.Giveaway.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ChristmasDdayDiscountPromotionTest {

    @DisplayName("크리스마스가 다가올수록 날마다 100원씩 증가한 할인 혜택을 받을 수 있다.")
    @MethodSource("orderDates")
    @ParameterizedTest(name = "[{index}] \"{0}일\" => {1}원 할인")
    void applyPromotion(int date, int discountPrice) throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(date);
        OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu("타파스", 4)));
        Promotion promotion = new ChristmasDdayDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = promotion.applyPromotion(orderDate, orderMenus);

        // Then
        assertThat(promotionBenefits.getDiscountPrice()).isEqualTo(discountPrice);
        assertThat(promotionBenefits.getGiveaway()).isEqualTo(NO_GIVEAWAY);
    }

    private static Stream<Arguments> orderDates() {
        return Stream.of(
                arguments(1, 1000),
                arguments(2, 1100),
                arguments(3, 1200),
                arguments(4, 1300),
                arguments(5, 1400),
                arguments(6, 1500),
                arguments(7, 1600),
                arguments(8, 1700),
                arguments(9, 1800),
                arguments(10, 1900),
                arguments(23, 3200),
                arguments(24, 3300),
                arguments(25, 3400)
        );
    }

    @DisplayName("이벤트 기간이 아닌 주문 날짜를 입력하면 할인 혜택을 받을 수 없다.")
    @Test
    void applyPromotionWithNotPromotionDate() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(27);
        OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu("타파스", 4)));
        int expectedDiscountPrice = 0;
        Promotion promotion = new ChristmasDdayDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = promotion.applyPromotion(orderDate, orderMenus);

        // Then
        assertThat(promotionBenefits.getDiscountPrice()).isEqualTo(expectedDiscountPrice);
        assertThat(promotionBenefits.getGiveaway()).isEqualTo(NO_GIVEAWAY);
    }

    @DisplayName("10000원 미만의 주문 메뉴 목록을 입력하면 할인 혜택을 받을 수 없다.")
    @Test
    void applyPromotionWithUnderMinimumTotalPrice() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(22);
        OrderMenus orderMenus = new OrderMenus(List.of(new OrderMenu("타파스", 1)));
        int expectedDiscountPrice = 0;
        Promotion promotion = new ChristmasDdayDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = promotion.applyPromotion(orderDate, orderMenus);

        // Then
        assertThat(promotionBenefits.getDiscountPrice()).isEqualTo(expectedDiscountPrice);
        assertThat(promotionBenefits.getGiveaway()).isEqualTo(NO_GIVEAWAY);
    }
}