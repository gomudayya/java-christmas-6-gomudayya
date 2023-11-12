package christmas.domain;

import christmas.constant.BenefitType;

import java.util.EnumMap;

public class BenefitDetails {
    private final EnumMap<BenefitType, Integer> discountInformation = new EnumMap<>(BenefitType.class); // 이게<Integer>인게 가독성에 너무 안 좋은거같은데

    public void addBenefit(BenefitType discountType, int amount) {

    }

    public int getTotalBenefit() {
        return 0;
    }
}
