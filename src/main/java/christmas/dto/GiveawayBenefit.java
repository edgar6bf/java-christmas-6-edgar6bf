package christmas.dto;

public record GiveawayBenefit(String giveawayName, int count) {

    public static GiveawayBenefit of(String giveawayName, int count) {
        return new GiveawayBenefit(giveawayName, count);
    }
}
