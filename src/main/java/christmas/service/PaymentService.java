package christmas.service;

import christmas.constant.Badge;
import christmas.domain.BenefitDetails;
import christmas.domain.ChristmasBenefitSet;
import christmas.domain.Order;
import christmas.domain.Receipt;

public class PaymentService {
    private final ChristmasBenefitSet christmasBenefitSet = new ChristmasBenefitSet();

    public Receipt getEstimatedReceipt(Order order) {
        BenefitDetails benefitDetails = christmasBenefitSet.getBenefitDetails(order);

        int totalBenefit = benefitDetails.getTotalBenefit();

        Badge badge = Badge.getEligibleBadge(totalBenefit);

        return new Receipt(order, benefitDetails, badge);
    }
}
