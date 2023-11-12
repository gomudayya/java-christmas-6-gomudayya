package christmas.domain;


import christmas.domain.dicount_policy.DiscountPolicySet;
import christmas.domain.gift_policy.GiftPolicySet;

public class BenefitSet {
    private final DiscountPolicySet discountPolicySet = new DiscountPolicySet();
    private final GiftPolicySet giftPolicySet = new GiftPolicySet();

    public BenefitDetails getBenefitDetails(Order order) {
        BenefitDetails benefitDetails = new BenefitDetails();

        discountPolicySet.summarizeDiscountBenefit(benefitDetails, order);
        giftPolicySet.summarizeGiftBenefit(benefitDetails, order);

        return benefitDetails;
    }
}
