package christmas.service;

import christmas.constant.Badge;
import christmas.domain.BenefitDetails;
import christmas.domain.BenefitSet;
import christmas.domain.Order;
import christmas.domain.Receipt;

public class PaymentService {

    private final BenefitSet discountPolicySet = new BenefitSet();


    public Receipt getEstimatedReceipt(Order order) {
        int totalBenefit = 0;

        BenefitDetails discountDetails = discountPolicySet.getBenefitDetails(order);

        Badge badge = Badge.getEligibleBadge(totalBenefit);
        return null;
    }
}
