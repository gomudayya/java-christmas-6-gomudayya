package christmas.domain.dicount_policy.concrete;

import christmas.constant.Benefit;
import christmas.domain.Order;
import christmas.domain.dicount_policy.DiscountPolicy;

import static christmas.config.DiscountConfig.SPECIAL_DISCOUNT_AMOUNT;
import static christmas.config.DiscountConfig.SPECIAL_DISCOUNT_DAYS;

public class SpecialDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        if (SPECIAL_DISCOUNT_DAYS.contains(order.getDayOfMonth())) {
            return SPECIAL_DISCOUNT_AMOUNT;
        }

        return 0;
    }

    @Override
    public Benefit getBenefitType() {
        return Benefit.SPECIAL_DISCOUNT;
    }
}
