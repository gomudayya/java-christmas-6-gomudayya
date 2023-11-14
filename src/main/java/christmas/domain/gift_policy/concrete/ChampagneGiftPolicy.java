package christmas.domain.gift_policy.concrete;

import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.domain.gift_policy.GiftPolicy;

import static christmas.config.GiftConfig.CHAMPAGNE_THRESHOLD;
import static christmas.config.GiftConfig.DEFAULT_CHAMPAGNE_QUANTITY;


public class ChampagneGiftPolicy implements GiftPolicy {

    @Override
    public int getGiftQuantity(Order order) {
        if (order.getTotalPrice() >= CHAMPAGNE_THRESHOLD) {
            return DEFAULT_CHAMPAGNE_QUANTITY;
        }

        return 0;
    }

    @Override
    public Menu getGiftMenu() {
        return Menu.CHAMPAGNE;
    }

}
