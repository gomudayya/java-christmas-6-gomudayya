package christmas.domain.concrete_discount;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountPolicyTest {

    SpecialDiscountPolicy discountPolicy = new SpecialDiscountPolicy();
    EnumMap<Menu, Integer> empty = new EnumMap<>(Menu.class);

    @DisplayName("특별 할인 날짜에는 특별 할인을 제대로 발아야 합니다.")
    @Test
    void DiscountAmountForSpecialDays() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(3, empty));
        assertThat(discountAmount).isEqualTo(1000);

        discountAmount = discountPolicy.getDiscountAmount(new Order(10, empty));
        assertThat(discountAmount).isEqualTo(1000);
    }

    @DisplayName("특별 할인 날짜가 아닌날에는 특별 할인을 받지 않야 합니다.")
    @Test
    void DiscountAmountForNonSpecialDays() {
        int discountAmount = discountPolicy.getDiscountAmount(new Order(4, empty));
        assertThat(discountAmount).isEqualTo(0);

        discountAmount = discountPolicy.getDiscountAmount(new Order(11, empty));
        assertThat(discountAmount).isEqualTo(0);
    }
}