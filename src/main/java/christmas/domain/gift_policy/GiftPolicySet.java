package christmas.domain.gift_policy;

import christmas.domain.BenefitDetails;
import christmas.domain.Order;
import christmas.domain.gift_policy.concrete.ChampagneGiftPolicy;

import java.util.HashSet;
import java.util.Set;

public class GiftPolicySet {
    private final Set<GiftPolicy> giftPolicies = new HashSet<>();

    public GiftPolicySet() {
        giftPolicies.add(new ChampagneGiftPolicy());
    }

    public void summarizeGiftBenefit(BenefitDetails benefitDetails, Order order) {
        for (GiftPolicy giftPolicy : giftPolicies) {
            int giftAmount = giftPolicy.getGiftAmount(order);

            if (giftAmount != 0) {
                benefitDetails.addBenefit(giftPolicy.getBenefitType(), giftAmount);
            }
        }
    }
}
