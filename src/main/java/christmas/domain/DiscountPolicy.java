package christmas.domain;

import christmas.constant.BenefitType;

public interface DiscountPolicy {
    int getDiscountAmount(Order order);
    BenefitType getBenefitType();
}
