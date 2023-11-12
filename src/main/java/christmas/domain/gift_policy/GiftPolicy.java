package christmas.domain.gift_policy;

import christmas.constant.BenefitType;
import christmas.domain.Order;

public interface GiftPolicy {
    int getGiftAmount(Order order);
    BenefitType getBenefitType();
}
