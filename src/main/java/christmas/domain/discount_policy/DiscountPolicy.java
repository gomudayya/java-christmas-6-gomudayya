package christmas.domain.discount_policy;

import christmas.domain.Order;

public interface DiscountPolicy {
    int getDiscountAmount(Order order);
}
