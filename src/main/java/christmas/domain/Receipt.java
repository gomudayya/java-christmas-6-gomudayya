package christmas.domain;

import christmas.constant.Badge;
import christmas.constant.Benefit;
import christmas.constant.Menu;

import java.util.EnumMap;
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

    public Map<Menu, Integer> getGiftMenus() {
        return benefitDetails.getGiftList();
    }

    public EnumMap<Benefit, Integer> getBenefitList() {
        return benefitDetails.getBenefitList();
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
