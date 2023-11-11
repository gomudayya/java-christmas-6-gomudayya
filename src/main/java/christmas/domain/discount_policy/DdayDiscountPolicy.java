package christmas.domain.discount_policy;

import christmas.domain.Order;
import christmas.domain.Receipt;

import static christmas.config.DiscountConfig.DDAY_DISCOUNT_AMOUNT_PER_DAY;
import static christmas.config.DiscountConfig.DDAY_DISCOUNT_BASE_AMOUNT;
import static christmas.config.DiscountConfig.DDAY_DISCOUNT_END_DAY;
import static christmas.config.DiscountConfig.DDAY_DISCOUNT_START_DAY;

public class DdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int dayOfMonth = order.getDayOfMonth();
        if (DDAY_DISCOUNT_START_DAY <= dayOfMonth && dayOfMonth <= DDAY_DISCOUNT_END_DAY) {
            return DDAY_DISCOUNT_BASE_AMOUNT + (dayOfMonth * DDAY_DISCOUNT_AMOUNT_PER_DAY);
        }

        return 0;
    }
}
