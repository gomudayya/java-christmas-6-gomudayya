package christmas.domain.gift_policy;

import christmas.constant.Menu;
import christmas.domain.BenefitDetails;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GiftPolicySetTest {
    GiftPolicySet giftPolicySet = new GiftPolicySet();
    @DisplayName("12만원 이상 결제를 했으면 샴페인 선물의 대상이다.")
    @Test
    void summarizeGiftBenefit() {
        EnumMap<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class);
        menuQuantityMap.put(Menu.T_BONE_STEAK, 2);
        menuQuantityMap.put(Menu.ICE_CREAM, 2);

        Order order = new Order(13, menuQuantityMap); // 총 12만원 -> 샴페인 선물 대상

        BenefitDetails benefitDetails = new BenefitDetails();
        giftPolicySet.summarizeGiftBenefit(benefitDetails, order);

        Map<Menu, Integer> giftList = benefitDetails.getGiftList();
        assertThat(giftList).containsKey(Menu.CHAMPAGNE);
    }
}