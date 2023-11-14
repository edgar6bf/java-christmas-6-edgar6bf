package christmas.domain.benefit;

import christmas.constant.GiveawayMenu;
import christmas.dto.GiveawayBenefit;

import static christmas.constant.GiveawayMenu.NO_GIVEAWAY;

public class Giveaway {

    private static final int ZERO_AMOUNT = 0;

    private final GiveawayMenu giveawayMenu;
    private final int count;

    public Giveaway(GiveawayMenu giveawayMenu, int count) {
        validateGiveaway(giveawayMenu, count);
        this.giveawayMenu = giveawayMenu;
        this.count = count;
    }

    private void validateGiveaway(GiveawayMenu giveawayMenu, int count) {
        if (count < ZERO_AMOUNT
                || (giveawayMenu == NO_GIVEAWAY && count != ZERO_AMOUNT)
        ) {
            throw new IllegalArgumentException("유효하지 않은 증정품 개수 입니다.");
        }
    }

    public boolean hasGiveaway() {
        return giveawayMenu != NO_GIVEAWAY && count > ZERO_AMOUNT;
    }

    public int getBenefitAmount() {
        return giveawayMenu.getPrice() * count;
    }

    public GiveawayBenefit getGiveawayBenefit() {
        return GiveawayBenefit.of(giveawayMenu.getName(), count);
    }
}
