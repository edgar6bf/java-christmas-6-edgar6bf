package christmas.domain.promotion;

import christmas.domain.benefit.PromotionBenefits;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("[WeekendDiscountPromotion] : 주말 할인 테스트")
class WeekendDiscountPromotionTest {

    @DisplayName("메인 메뉴 1개당 2,023원 할인한다.")
    @MethodSource("menusAndDiscountPrice")
    @ParameterizedTest(name = "[{index}] 메인 메뉴 개수: {2}개 => {1}원 할인")
    void applyPromotion(List<OrderMenu> menus, int discountAmount, int mainMenuCount) throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(2);
        OrderMenus orderMenus = new OrderMenus(menus);
        int preDiscountTotalOrderPrice = orderMenus.getTotalOrderPrice();
        int expectedDiscountedTotalOrderPrice = preDiscountTotalOrderPrice - discountAmount;
        WeekendDiscountPromotion weekendDiscountPromotion = new WeekendDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = weekendDiscountPromotion.applyPromotion(orderDate, orderMenus);
        int postDiscountTotalOrderPrice = promotionBenefits.applyBenefit(preDiscountTotalOrderPrice);

        // Then
        assertThat(postDiscountTotalOrderPrice).isEqualTo(expectedDiscountedTotalOrderPrice);
    }

    private static Stream<Arguments> menusAndDiscountPrice() {
        return Stream.of(
                arguments(List.of(new OrderMenu("크리스마스파스타", 2)), 4046, 2),
                arguments(List.of(new OrderMenu("해산물파스타", 5)), 10115, 5),
                arguments(List.of(new OrderMenu("타파스", 5)), 0, 0),
                arguments(List.of(new OrderMenu("타파스", 15), new OrderMenu("티본스테이크", 3)), 6069, 3)
        );
    }

    @DisplayName("메인 메뉴가 포함되어 있지 않은 주문 목록을 입력하면 할인 혜택을 받을 수 없다.")
    @Test
    void applyPromotionWithNotHasMainMenu() throws Exception {
        // Given
        OrderDate orderDate = new OrderDate(3);
        OrderMenus notHasMainMenu = new OrderMenus(
                List.of(
                        new OrderMenu("초코케이크", 10)
                )
        );
        WeekendDiscountPromotion weekendDiscountPromotion = new WeekendDiscountPromotion();

        // When
        PromotionBenefits promotionBenefits = weekendDiscountPromotion.applyPromotion(orderDate, notHasMainMenu);

        // Then
        assertThat(promotionBenefits.hasBenefits()).isFalse();
    }
}
