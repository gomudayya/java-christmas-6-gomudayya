package christmas.domain.gift_policy;

import christmas.constant.BenefitType;
import christmas.constant.Menu;
import christmas.domain.Order;

import static christmas.config.EventConfig.CHAMPAGNE_THRESHOLD;

public class ChampagneGiftPolicy implements GiftPolicy {
    public boolean hasEligibility(Order order) {
        return order.getTotalPrice() >= CHAMPAGNE_THRESHOLD;
    }

    @Override
    public int getGiftAmount(Order order) {
        if (order.getTotalPrice() >= CHAMPAGNE_THRESHOLD) {
            return Menu.CHAMPAGNE.getPrice();
        }

        return 0;
    }

    @Override
    public BenefitType getBenefitType() {
        return BenefitType.CHAMPAGNE_GIFT;
    }
}
