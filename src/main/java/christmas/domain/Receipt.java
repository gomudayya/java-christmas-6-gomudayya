package christmas.domain;

import christmas.constant.Badge;
import christmas.constant.DiscountType;
import christmas.constant.Menu;

import java.util.Map;

public class Receipt {
    private final Order order;
    private final BenefitDetails benefitDetails;
    private Badge badge;

    public Receipt(Order order, BenefitDetails discountDetails, Badge badge) {
        this.order = order;
        this.benefitDetails = discountDetails;
        this.badge = badge;
    }

    public Map<Menu, Integer> getOrder() {
        return order.getMenuQuantityMap();
    }

    public int getPriceBeforeDiscount() {
        return order.getTotalPrice();
    }

    public Map<DiscountType, Integer> getDiscountList() {
        return benefitDetails.getDiscountList();
    }

    public Map<Menu, Integer> getGiftList() {
        return benefitDetails.getGiftList();
    }

    public int getTotalBenefit() {
        return benefitDetails.getTotalBenefit();
    }

    public int getPriceAfterDiscount() {
        return getPriceBeforeDiscount() - benefitDetails.getTotalDiscountAmount();
    }

    public String getBadgeName() {
        return badge.getName();
    }
}
