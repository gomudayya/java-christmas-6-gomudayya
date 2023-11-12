package christmas.service;

import christmas.constant.Badge;
import christmas.constant.Menu;
import christmas.domain.ChampagneGiftEvent;
import christmas.domain.DiscountPolicySet;
import christmas.domain.Order;
import christmas.domain.Receipt;

public class PaymentService {

    private final DiscountPolicySet discountPolicySet = new DiscountPolicySet();
    private final ChampagneGiftEvent champagneGiftEvent = new ChampagneGiftEvent();
    public Receipt getEstimatedReceipt(Order order) {
        int totalBenefit = 0;

        totalBenefit += discountPolicySet.getTotalDiscountAmount(order);
        if(champagneGiftEvent.hasEligibility(order)) {
            totalBenefit += Menu.CHAMPAGNE.getPrice();
        }

        Badge badge = Badge.getEligibleBadge(totalBenefit);
        return null;
    }
}
