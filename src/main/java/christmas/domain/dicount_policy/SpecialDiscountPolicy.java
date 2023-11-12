package christmas.domain.dicount_policy;

import christmas.constant.BenefitType;
import christmas.domain.Order;

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
    public BenefitType getBenefitType() {
        return BenefitType.SPECIAL_DISCOUNT;
    }
}
