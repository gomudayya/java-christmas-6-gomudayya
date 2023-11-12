package christmas.domain;

import static christmas.config.EventConfig.CHAMPAGNE_THRESHOLD;

public class ChampagneGiftEvent {
    public boolean hasEligibility(Order order) {
        return order.getTotalPrice() >= CHAMPAGNE_THRESHOLD;
    }
}
