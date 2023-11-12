package christmas.service;

import christmas.constant.Badge;
import christmas.domain.BenefitDetails;
import christmas.domain.BenefitSet;
import christmas.domain.Order;
import christmas.domain.Receipt;

public class PaymentService {
    private final BenefitSet benefitSet = new BenefitSet();

    public Receipt getEstimatedReceipt(Order order) {
        BenefitDetails benefitDetails = benefitSet.getBenefitDetails(order);

        int totalBenefit = benefitDetails.getTotalBenefit();

        Badge badge = Badge.getEligibleBadge(totalBenefit);

        return new Receipt(order, benefitDetails, badge);
    }
}
