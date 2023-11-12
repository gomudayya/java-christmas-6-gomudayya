package christmas.domain.dicount_policy;

import christmas.constant.BenefitType;
import christmas.domain.Order;

public interface DiscountPolicy {
    int getDiscountAmount(Order order);
    BenefitType getBenefitType();
}
