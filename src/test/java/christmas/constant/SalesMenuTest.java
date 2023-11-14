package christmas.constant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static christmas.constant.SalesMenu.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("[SalesMenu] : 판매 메뉴 테스트")
class SalesMenuTest {

    @DisplayName("메뉴 이름을 입력하면 적절한 메뉴를 반환한다.")
    @MethodSource("menuNamesAndSalesMenus")
    @ParameterizedTest(name = "[{index}] \"{0}\" => {2}")
    void getSalesMenu(String menuName, SalesMenu expected, String salesMenuTitle) throws Exception {
        // When
        SalesMenu salesMenu = SalesMenu.of(menuName);

        // Then
        assertThat(salesMenu).isEqualTo(expected);
    }

    private static Stream<Arguments> menuNamesAndSalesMenus() {
        return Stream.of(
                Arguments.of("양송이수프", MUSHROOM_SOUP, MUSHROOM_SOUP.name()),
                Arguments.of("티본스테이크", T_BONE_STEAK, T_BONE_STEAK.name()),
                Arguments.of("아이스크림", ICE_CREAM, ICE_CREAM.name()),
                Arguments.of("레드와인", RED_WINE, RED_WINE.name())
        );
    }

    @DisplayName("유효하지 않은 메뉴 이름을 입력하면 예외가 발생한다.")
    @Test
    void getSalesMenuWithInvalidMenuName() throws Exception {
        // When
        String menuName = "우테코 학식";

        // Then
        assertThatThrownBy(() -> SalesMenu.of(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 메뉴 이름입니다.");
    }
}
