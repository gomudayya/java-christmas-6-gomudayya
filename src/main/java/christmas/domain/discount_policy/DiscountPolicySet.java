package christmas.domain.discount_policy;

import christmas.domain.Order;

import java.util.HashSet;
import java.util.Set;

public class DiscountPolicySet {
    public Set<DiscountPolicy> discountPolicies = new HashSet<>();

    public DiscountPolicySet(Set<DiscountPolicy> discountPolicies) {
        discountPolicies.add(new DdayDiscountPolicy());
        discountPolicies.add(new SpecialDiscountPolicy());
        discountPolicies.add(new WeekdayDiscountPolicy());
        discountPolicies.add(new WeekendDiscountPolicy());
    }

    public int getTotalDiscountAmount(Order order) {
        return discountPolicies.stream()
                .mapToInt(discountPolicy -> discountPolicy.getDiscountAmount(order))
                .sum();
    }
}
