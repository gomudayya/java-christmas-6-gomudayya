package christmas.domain.discount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekdayDiscountPolicyTest {
    private final int weekendDay = 2;
    private final int weekDay = 3;
    WeekdayDiscountPolicy discountPolicy = new WeekdayDiscountPolicy();

    @DisplayName("테스트의 인자값으로 넣을 '일' 데이터가 올바른지 검증한다.")
    @BeforeEach
    void validateDay() {
        if (new Order(weekendDay, null).isWeekday()) {
            throw new IllegalArgumentException("weekendDay는 주말이어야 합니다.");
        }
        if (new Order(weekDay, null).isWeekend()) {
            throw new IllegalArgumentException("weekDay는 평일이여야 합니다.");
        }
    }

    @DisplayName("평일이고, 디저트 메뉴를 시켰을 때는 할인을 받아야한다. 할인은 디저트메뉴 하나당 2023원씩 적용된다.")
    @Test
    void discountForWeekdayWithDessertOrder() {
        EnumMap<Menu, Integer> iceCream2 = new EnumMap<>(Menu.class);
        iceCream2.put(Menu.ICE_CREAM, 2);
        Order order = new Order(3, iceCream2);

        if (order.isWeekend()) {
            throw new IllegalArgumentException("테스트 조건에 맞추기 위해서는 평일주문이어야 합니다.");
        }

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(2023 * 2);
    }

    @DisplayName("평일이고, 디저트 메뉴를 시키지 않았을 때는 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForWeekdayWithoutDessertOrder() {
        EnumMap<Menu, Integer> mushRoomSoup2 = new EnumMap<>(Menu.class);
        mushRoomSoup2.put(Menu.MUSHROOM_SOUP, 2);
        Order order = new Order(3, mushRoomSoup2);

        if (order.isWeekend()) {
            throw new IllegalArgumentException("테스트 조건에 맞추기 위해서는 평일주문이어야 합니다.");
        }

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("평일이 아니면 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForNonWeekdayWithDessertOrder() {
        EnumMap<Menu, Integer> iceCream2 = new EnumMap<>(Menu.class);
        iceCream2.put(Menu.ICE_CREAM, 2);
        Order order = new Order(2, iceCream2); // 주말 주문

        if (order.isWeekday()) {
            throw new IllegalArgumentException("테스트 조건에 맞추기 위해서는 평일이 아니어야 합니다.");
        }

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }
}