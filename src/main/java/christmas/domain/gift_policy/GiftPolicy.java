package christmas.domain.gift_policy;

import christmas.constant.Menu;
import christmas.domain.Order;

public interface GiftPolicy {
    int getGiftAmount(Order order);

    Menu getGiftMenu();
}
