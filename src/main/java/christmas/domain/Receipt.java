package christmas.domain;

import christmas.constant.Badge;

public class Receipt {
    private final Order order;
    private final BenefitDetails discountDetails;
    private Badge badge;
    public Receipt(Order order, BenefitDetails discountDetails, Badge badge) {
        this.order = order;
        this.discountDetails = discountDetails;
        this.badge = badge;
    }
}
