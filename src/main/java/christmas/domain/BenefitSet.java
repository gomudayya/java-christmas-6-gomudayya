package christmas.domain;

import christmas.constant.BenefitType;
import christmas.constant.Menu;
import christmas.domain.concrete_discount.DdayDiscountPolicy;
import christmas.domain.concrete_discount.SpecialDiscountPolicy;
import christmas.domain.concrete_discount.WeekdayDiscountPolicy;
import christmas.domain.concrete_discount.WeekendDiscountPolicy;

import java.util.HashSet;
import java.util.Set;

public class BenefitSet {
    private final Set<DiscountPolicy> discountPolicies = new HashSet<>();
    private final ChampagneGiftEvent champagneGiftEvent = new ChampagneGiftEvent();

    public BenefitSet() {
        discountPolicies.add(new DdayDiscountPolicy());
        discountPolicies.add(new SpecialDiscountPolicy());
        discountPolicies.add(new WeekdayDiscountPolicy());
        discountPolicies.add(new WeekendDiscountPolicy());
    }

    public BenefitDetails getBenefitDetails(Order order) {
        BenefitDetails benefitDetails = new BenefitDetails();

        summarizeDiscountBenefit(benefitDetails, order);
        summarizeGiftBenefit(benefitDetails, order);

        return benefitDetails;
    }

    private void summarizeDiscountBenefit(BenefitDetails benefitDetails, Order order) {
        for (DiscountPolicy discountPolicy : discountPolicies) {
            int discountAmount = discountPolicy.getDiscountAmount(order);

            if (discountAmount != 0) {
                benefitDetails.addBenefit(discountPolicy.getBenefitType(), discountAmount);
            }
        }
    }

    private void summarizeGiftBenefit(BenefitDetails benefitDetails, Order order) {
        if (champagneGiftEvent.hasEligibility(order)) {
            benefitDetails.addBenefit(BenefitType.CHAMPAGNE_GIFT, Menu.CHAMPAGNE.getPrice());
        }
    }


    public int getTotalDiscountAmount(Order order) {
        return discountPolicies.stream()
                .mapToInt(discountPolicy -> discountPolicy.getDiscountAmount(order))
                .sum();
    }
}
