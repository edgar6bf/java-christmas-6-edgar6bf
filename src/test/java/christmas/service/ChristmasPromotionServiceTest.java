package christmas.service;

import christmas.config.PromotionConfig;
import christmas.domain.order.OrderDate;
import christmas.domain.order.OrderMenu;
import christmas.domain.order.OrderMenus;
import christmas.dto.GiveawayBenefit;
import christmas.dto.MenuNameAndCount;
import christmas.dto.PromotionApplyResult;
import christmas.dto.PromotionBenefitHistory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static christmas.constant.DecemberPromotionBadge.NO_BADGE;
import static christmas.constant.DecemberPromotionBadge.SANTA;
import static christmas.constant.PromotionTitle.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("[ChristmasPromotionService] : 서비스 클래스 테스트")
class ChristmasPromotionServiceTest {

    @DisplayName("주문 날짜와 주문 메뉴 목록을 입력하면 제공 받을 수 있는 혜택 정보를 반환한다.")
    @MethodSource("orderDatesAndOrderMenus")
    @ParameterizedTest(name = "[{index}] \"{3}\"")
    void checkPromotionBenefits(OrderDate orderDate, OrderMenus orderMenus, PromotionApplyResult expected, String description) throws Exception {
        // Given
        ChristmasPromotionService christmasPromotionService = new ChristmasPromotionService(PromotionConfig.config());

        // When
        PromotionApplyResult promotionApplyResult = christmasPromotionService.checkPromotionBenefits(orderDate, orderMenus);

        // Then
        assertThat(promotionApplyResult).isEqualTo(expected);
    }

    private static Stream<Arguments> orderDatesAndOrderMenus() {
        return Stream.of(
                Arguments.of(
                        new OrderDate(3),
                        new OrderMenus(
                                List.of(
                                        new OrderMenu("티본스테이크", 1),
                                        new OrderMenu("바비큐립", 1),
                                        new OrderMenu("초코케이크", 2),
                                        new OrderMenu("제로콜라", 1)
                                )
                        ),
                        hasBenefitResult(),
                        "적용할 수 있는 혜택이 존재할 경우"
                ),
                Arguments.of(
                        new OrderDate(26),
                        new OrderMenus(
                                List.of(
                                        new OrderMenu("타파스", 1),
                                        new OrderMenu("제로콜라", 1))
                        ),
                        noBenefitResult(),
                        "적용할 수 있는 혜택이 존재하지 않을 경우"
                )
        );
    }

    private static PromotionApplyResult hasBenefitResult() {
        return PromotionApplyResult.of(
                3,
                List.of(
                        new MenuNameAndCount("티본스테이크", 1),
                        new MenuNameAndCount("바비큐립", 1),
                        new MenuNameAndCount("초코케이크", 2),
                        new MenuNameAndCount("제로콜라", 1)
                ),
                142000,
                List.of(GiveawayBenefit.of("샴페인", 1)),
                List.of(
                        PromotionBenefitHistory.of(CHRISTMAS_DDAY_DISCOUNT.getTitle(), 1200),
                        PromotionBenefitHistory.of(WEEKDAY_DISCOUNT.getTitle(), 4046),
                        PromotionBenefitHistory.of(SPECIAL_DISCOUNT.getTitle(), 1000),
                        PromotionBenefitHistory.of(GIVEAWAY.getTitle(), 25000)
                ),
                31246,
                135754,
                SANTA.getTitle()
        );
    }

    private static PromotionApplyResult noBenefitResult() {
        return PromotionApplyResult.of(
                26,
                List.of(
                        new MenuNameAndCount("타파스", 1),
                        new MenuNameAndCount("제로콜라", 1)
                ),
                8500,
                Collections.emptyList(),
                Collections.emptyList(),
                0,
                8500,
                NO_BADGE.getTitle()
        );
    }
}
