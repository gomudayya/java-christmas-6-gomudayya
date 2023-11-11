package christmas.domain.discount_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WeekendDiscountPolicyTest {

    private final int weekendDay = 2;
    private final int weekDay = 3;
    WeekendDiscountPolicy discountPolicy = new WeekendDiscountPolicy();

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

    @DisplayName("주말이고, 메인 메뉴를 시켰을 때는 할인을 받아야한다. 할인은 메인 메뉴 하나당 2023원씩 적용된다.")
    @Test
    void discountAppliedForWeekendWithMainCourse() {
        int count = 2;

        EnumMap<Menu, Integer> steakMap = new EnumMap<>(Menu.class);
        steakMap.put(Menu.T_BONE_STEAK, count);
        Order order = new Order(weekendDay, steakMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(2023 * count);
    }

    @DisplayName("주말이고, 메인 메뉴를 시키지 않았을 때는 할인을 받지 말아야 한다.")
    @Test
    void noDiscountForWeekendWithoutMainCourse() {
        EnumMap<Menu, Integer> mushRoomSoupMap = new EnumMap<>(Menu.class);
        mushRoomSoupMap.put(Menu.MUSHROOM_SOUP, 11);
        Order order = new Order(weekendDay, mushRoomSoupMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }

    @DisplayName("주말이 아니면 할인을 받지 말아야 한다")
    @Test
    void noDiscountForWeekday() {
        EnumMap<Menu, Integer> steakMap = new EnumMap<>(Menu.class);
        steakMap.put(Menu.T_BONE_STEAK, 11);
        Order order = new Order(weekDay, steakMap);

        int discountAmount = discountPolicy.getDiscountAmount(order);
        assertThat(discountAmount).isEqualTo(0);
    }
}