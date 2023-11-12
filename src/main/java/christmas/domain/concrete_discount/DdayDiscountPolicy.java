package christmas.domain.concrete_discount;

import christmas.domain.DiscountPolicy;
import christmas.domain.Order;

import static christmas.config.DiscountConfig.D_DAY_DISCOUNT_AMOUNT_PER_DAY;
import static christmas.config.DiscountConfig.D_DAY_DISCOUNT_BASE_AMOUNT;
import static christmas.config.DiscountConfig.D_DAY_DISCOUNT_END_DAY;
import static christmas.config.DiscountConfig.D_DAY_DISCOUNT_START_DAY;

public class DdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int dayOfMonth = order.getDayOfMonth();
        if (D_DAY_DISCOUNT_START_DAY <= dayOfMonth && dayOfMonth <= D_DAY_DISCOUNT_END_DAY) {
            return D_DAY_DISCOUNT_BASE_AMOUNT + (dayOfMonth * D_DAY_DISCOUNT_AMOUNT_PER_DAY);
        }

        return 0;
    }
}
