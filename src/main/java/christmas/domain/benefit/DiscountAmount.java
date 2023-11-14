package christmas.domain.benefit;

public class DiscountAmount {

    private static final int ZERO_AMOUNT = 0;

    private final int discountAmount;

    public DiscountAmount(int discountAmount) {
        validateDiscountAmount(discountAmount);
        this.discountAmount = discountAmount;
    }

    private void validateDiscountAmount(int discountAmount) {
        if (discountAmount < ZERO_AMOUNT) {
            throw new IllegalArgumentException("유효하지 않은 할인 금액 입니다.");
        }
    }

    public boolean applicableDiscount() {
        return discountAmount > ZERO_AMOUNT;
    }

    public int applyDiscount(int principal) {
        return principal - discountAmount;
    }

    public int getBenefitAmount() {
        return discountAmount;
    }
}
