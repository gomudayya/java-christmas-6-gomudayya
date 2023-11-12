package christmas.domain.dicount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.domain.dicount_policy.TestDate.WEEKDAY;
import static christmas.domain.dicount_policy.TestDate.WEEKEND_DAY;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountPolicyTest {
    WeekdayDiscountPolicy discountPolicy = new WeekdayDiscountPolicy();

    @DisplayName("평일이고, 디저트 메뉴를 시켰을 때는 할인을 받아야한다. 할인은 디저트메뉴 하나당 2023원씩 적용된다.")
    @Test
    void discountForWeekdayWithDessertOrder() {
        int count = 2;

        EnumMap<Menu, Integer> icecreamMap = new EnumMap<>(Menu.class);
        icecreamMap.put(Menu.ICE_CREAM, count);
        Order order = new Order(WEEKDAY.getValue(), icecreamMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(2023 * count);
    }

    @DisplayName("평일이고, 디저트 메뉴를 시키지 않았을 때는 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForWeekdayWithoutDessertOrder() {
        EnumMap<Menu, Integer> mushRoomSoup2 = new EnumMap<>(Menu.class);
        mushRoomSoup2.put(Menu.MUSHROOM_SOUP, 11);
        Order order = new Order(WEEKDAY.getValue(), mushRoomSoup2);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("평일이 아니면 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForNonWeekdayWithDessertOrder() {
        EnumMap<Menu, Integer> iceCream2 = new EnumMap<>(Menu.class);
        iceCream2.put(Menu.ICE_CREAM, 11);
        Order order = new Order(WEEKEND_DAY.getValue(), iceCream2); // 주말 주문

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }
}