package christmas.constant;

public enum PromotionTitle {
    CHRISTMAS_DDAY_DISCOUNT("크리스마스 디데이 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    GIVEAWAY("증정 이벤트"),
    NO_PROMOTION("없음");

    private final String title;

    PromotionTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
