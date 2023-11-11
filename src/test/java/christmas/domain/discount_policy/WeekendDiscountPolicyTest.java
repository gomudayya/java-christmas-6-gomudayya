package christmas.domain.discount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.domain.discount_policy.constant.TestDate.*;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountPolicyTest {
    WeekendDiscountPolicy discountPolicy = new WeekendDiscountPolicy();

    @DisplayName("주말이고, 메인 메뉴를 시켰을 때는 할인을 받아야한다. 할인은 메인 메뉴 하나당 2023원씩 적용된다.")
    @Test
    void discountAppliedForWeekendWithMainCourse() {
        int count = 2;

        EnumMap<Menu, Integer> steakMap = new EnumMap<>(Menu.class);
        steakMap.put(Menu.T_BONE_STEAK, count);
        Order order = new Order(WEEKEND_DAY.getValue(), steakMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(2023 * count);
    }

    @DisplayName("주말이고, 메인 메뉴를 시키지 않았을 때는 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForWeekendWithoutMainCourse() {
        EnumMap<Menu, Integer> mushRoomSoupMap = new EnumMap<>(Menu.class);
        mushRoomSoupMap.put(Menu.MUSHROOM_SOUP, 11);
        Order order = new Order(WEEKEND_DAY.getValue(), mushRoomSoupMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("주말이 아니면 할인을 받지 말아야 한다")
    @Test
    void noDiscountForWeekday() {
        EnumMap<Menu, Integer> steakMap = new EnumMap<>(Menu.class);
        steakMap.put(Menu.T_BONE_STEAK, 11);
        Order order = new Order(WEEKDAY.getValue(), steakMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }
}