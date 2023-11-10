package christmas.domain.order;

public class OrderDate {

    private static final int START_DATE = 1;
    private static final int END_DATE = 31;

    private final int date;

    public OrderDate(int date) {
        validateDate(date);
        this.date = date;
    }

    private void validateDate(int date) {
        if (date < START_DATE || date > END_DATE) {
            throw new IllegalArgumentException("유효하지 않은 날짜입니다.");
        }
    }

    public boolean isOrderDateInclude(int startDate, int endDate) {
        return date >= startDate && date <= endDate;
    }

    public int calculateDateDifference(int date) {
        return date - this.date;
    }
}
