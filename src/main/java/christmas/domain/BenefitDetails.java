package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.Menu;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class BenefitDetails {
    private final EnumMap<Benefit, Integer> discountInformation = new EnumMap<>(Benefit.class);
    private final EnumMap<Menu, Integer> giftInformation = new EnumMap<>(Menu.class);

    public void addDiscountBenefit(Benefit discountType, int amount) {
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

    public Map<Menu, Integer> getGiftList() {
        return Collections.unmodifiableMap(giftInformation);
    }


    public EnumMap<Benefit, Integer> getBenefitList() {
        EnumMap<Benefit, Integer> benefitMap = new EnumMap(Benefit.class);

        benefitMap.putAll(discountInformation);

        if (!giftInformation.isEmpty()) {
            benefitMap.put(Benefit.GIFT_EVENT, 0);

            for (Map.Entry<Menu, Integer> entry : giftInformation.entrySet()) {
                int money = entry.getKey().getPrice() * entry.getValue();
                benefitMap.merge(Benefit.GIFT_EVENT, money, Integer::sum);
            }
        }

        return benefitMap;
    }
}
