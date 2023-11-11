package christmas.domain.discount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class DdayDiscountPolicyTest {
    DdayDiscountPolicy discountPolicy = new DdayDiscountPolicy();
    EnumMap<Menu, Integer> empty = new EnumMap<>(Menu.class);

    @DisplayName("1일부터 25일까지는 dDay할인이 적용되어야 한다.")
    @Test
    void dDayDiscountTestForDay1_25() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(1, empty));
        assertThat(discountAmount).isEqualTo(1000);

        discountAmount = discountPolicy.getDiscountAmount(new Order(25, empty));
        assertThat(discountAmount).isEqualTo(3400);
    }

    @DisplayName("26일 부터는 dDay할인이 적용되지 말아야 한다.")
    @Test
    void dDayDiscountTestForOverDay25() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(26, empty));
        assertThat(discountAmount).isEqualTo(0);

        discountAmount = discountPolicy.getDiscountAmount(new Order(27, empty));
        assertThat(discountAmount).isEqualTo(0);
    }
}