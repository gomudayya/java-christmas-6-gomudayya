package christmas.domain.dicount_policy;

import christmas.domain.Order;
import christmas.domain.dicount_policy.concrete.SpecialDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.test_data.TempMenuQuantityMap.TEMP_MENU_QUANTITY_MAP;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountPolicyTest {

    SpecialDiscountPolicy discountPolicy = new SpecialDiscountPolicy();

    @DisplayName("특별 할인 날짜에는 특별 할인을 제대로 발아야 합니다.")
    @Test
    void DiscountAmountForSpecialDays() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(3, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(1000);

        discountAmount = discountPolicy.getDiscountAmount(new Order(10, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(1000);
    }

    @DisplayName("특별 할인 날짜가 아닌날에는 특별 할인을 받지 않야 합니다.")
    @Test
    void DiscountAmountForNonSpecialDays() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(4, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(0);

        discountAmount = discountPolicy.getDiscountAmount(new Order(11, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(0);
    }
}