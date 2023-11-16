package christmas.domain.dicount_policy;

import christmas.constant.DiscountType;
import christmas.domain.Order;

public interface DiscountPolicy {
    int getDiscountAmount(Order order);

    DiscountType getDiscountType();
}
