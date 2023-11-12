package christmas.domain.dicount_policy;

import christmas.constant.Benefit;
import christmas.domain.Order;

public interface DiscountPolicy {
    int getDiscountAmount(Order order);

    Benefit getBenefitType();
}
