package christmas.domain.gift_policy.concrete;

import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.domain.gift_policy.GiftPolicy;

import static christmas.config.EventConfig.DEFAULT_CHAMPAGNE_COUNT;
import static christmas.config.GiftConfig.CHAMPAGNE_THRESHOLD;


public class ChampagneGiftPolicy implements GiftPolicy {

    @Override
    public int getGiftAmount(Order order) {
        if (order.getTotalPrice() >= CHAMPAGNE_THRESHOLD) {
            return DEFAULT_CHAMPAGNE_COUNT;
        }

        return 0;
    }

    @Override
    public Menu getGiftMenu() {
        return Menu.CHAMPAGNE;
    }

}
