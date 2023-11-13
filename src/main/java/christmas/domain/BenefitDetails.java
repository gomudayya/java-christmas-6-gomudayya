package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Menu;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BenefitDetails {
    private final EnumMap<DiscountType, Integer> discountInformation = new EnumMap<>(DiscountType.class);
    private final EnumMap<Menu, Integer> giftInformation = new EnumMap<>(Menu.class);

    public void addDiscountBenefit(DiscountType discountType, int amount) {
        discountInformation.put(discountType, amount);
    }

    public void addGiftBenefit(Menu menu, int count) {
        giftInformation.put(menu, count);
    }

    public int getTotalBenefit() {
        int totalDiscountAmount = getTotalDiscountAmount();

        int totalGiftAmountByMoney = giftInformation.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();

        return totalDiscountAmount + totalGiftAmountByMoney;
    }

    public int getTotalDiscountAmount() {
        return discountInformation.values().stream()
                .mapToInt(value -> value)
                .sum();
    }

    public Map<DiscountType, Integer> getDiscountList() {
        return Collections.unmodifiableMap(discountInformation);
    }

    public Map<Menu, Integer> getGiftList() {
        return Collections.unmodifiableMap(giftInformation);
    }
}
