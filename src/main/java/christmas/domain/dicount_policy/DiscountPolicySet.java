package christmas.domain.dicount_policy;

import christmas.domain.BenefitDetails;
import christmas.domain.Order;
import christmas.domain.dicount_policy.DdayDiscountPolicy;
import christmas.domain.dicount_policy.DiscountPolicy;
import christmas.domain.dicount_policy.SpecialDiscountPolicy;
import christmas.domain.dicount_policy.WeekdayDiscountPolicy;
import christmas.domain.dicount_policy.WeekendDiscountPolicy;

import java.util.HashSet;
import java.util.Set;

public class DiscountPolicySet {
    private final Set<DiscountPolicy> discountPolicies = new HashSet<>();

    public DiscountPolicySet() {
        discountPolicies.add(new DdayDiscountPolicy());
        discountPolicies.add(new SpecialDiscountPolicy());
        discountPolicies.add(new WeekdayDiscountPolicy());
        discountPolicies.add(new WeekendDiscountPolicy());
    }

    public void summarizeDiscountBenefit(BenefitDetails benefitDetails, Order order) {
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountAmount = discountPolicy.getDiscountAmount(order);

            if (discountAmount != 0) {
                benefitDetails.addBenefit(discountPolicy.getBenefitType(), discountAmount);
            }
        }
    }
}
