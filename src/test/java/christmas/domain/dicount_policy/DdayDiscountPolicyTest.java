package christmas.domain.dicount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.domain.dicount_policy.concrete.DdayDiscountPolicy;
import christmas.test_data.TempMenuQuantityMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.test_data.TempMenuQuantityMap.TEMP_MENU_QUANTITY_MAP;
import static org.assertj.core.api.Assertions.assertThat;

class DdayDiscountPolicyTest {
    DdayDiscountPolicy discountPolicy = new DdayDiscountPolicy();

    @DisplayName("1일부터 25일까지는 D-Day 할인이 적용되어야 한다.")
    @Test
    void dDayDiscountTestForDay1_25() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(1, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(1000);

        discountAmount = discountPolicy.getDiscountAmount(new Order(25, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(3400);
    }

    @DisplayName("26일 부터는 D-Day 할인이 적용되지 말아야 한다.")
    @Test
    void dDayDiscountTestForOverDay25() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(26, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(0);

        discountAmount = discountPolicy.getDiscountAmount(new Order(27, TEMP_MENU_QUANTITY_MAP));
        assertThat(discountAmount).isEqualTo(0);
    }
}