package christmas.domain;


import christmas.domain.dicount_policy.DiscountPolicySet;
import christmas.domain.gift_policy.GiftPolicySet;

import static christmas.config.EventConfig.EVENT_THRESHOLD;

public class ChristmasBenefitSet {
    private final DiscountPolicySet discountPolicySet = new DiscountPolicySet();
    private final GiftPolicySet giftPolicySet = new GiftPolicySet();

    public BenefitDetails getBenefitDetails(Order order) {
        BenefitDetails benefitDetails = new BenefitDetails();

        if (order.getTotalPrice() >= EVENT_THRESHOLD) {
            discountPolicySet.summarizeDiscountBenefit(benefitDetails, order);
            giftPolicySet.summarizeGiftBenefit(benefitDetails, order);
        }

        return benefitDetails;
    }
}
