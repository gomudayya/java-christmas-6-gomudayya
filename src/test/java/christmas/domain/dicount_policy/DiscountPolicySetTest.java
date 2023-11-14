package christmas.domain.dicount_policy;

import christmas.constant.Menu;
import christmas.domain.BenefitDetails;
import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountPolicySetTest {
    DiscountPolicySet discountPolicySet = new DiscountPolicySet();

    @DisplayName("d-day할인과, 주말 할인(메인 메뉴 필수)을 적용받는 주문이 올바르게 할인 금액을 반환하는지 테스트")
    @Test
    void getTotalDiscountAmountWithDdayAndWeekend() {
        BenefitDetails benefitDetails = new BenefitDetails();

        EnumMap<Menu, Integer> orderMap = new EnumMap<>(Menu.class);
        orderMap.put(Menu.T_BONE_STEAK, 6);

        Order order = new Order(16, orderMap);

        discountPolicySet.summarizeDiscountBenefit(benefitDetails, order);

        // 할인 금액은 d-Day할인(900 + 16 * 100) + 주말 할인(6 * 2023) = 14638
        assertThat(benefitDetails.getTotalDiscountAmount()).isEqualTo(14638);
    }

    @DisplayName("d-day할인과, 평일 할인(디저트메뉴 필수), 특별 할인을 적용받는 주문이 올바르게 할인 금액을 반환하는지 테스트")
    @Test
    void getTotalDiscountAmountWithDdayAndWeekday() {
        BenefitDetails benefitDetails = new BenefitDetails();

        EnumMap<Menu, Integer> orderMap = new EnumMap<>(Menu.class);
        orderMap.put(Menu.T_BONE_STEAK, 5);
        orderMap.put(Menu.CHOCOLATE_CAKE, 4);

        Order order = new Order(17, orderMap);

        discountPolicySet.summarizeDiscountBenefit(benefitDetails, order);

        //할인 금액은 d-day할인(900 + 17 * 100) + 평일 할인(4 * 2023) + 특별할인(1000) = 11692
        assertThat(benefitDetails.getTotalDiscountAmount()).isEqualTo(11692);
    }

    @DisplayName("평일 할인(디저트 메뉴 필수)만 받는 주문이 올바르게 할인 금액을 반환하는지 테스트")
    @Test
    void getTotalDiscountAmountWithOnlyWeekday() {
        BenefitDetails benefitDetails = new BenefitDetails();

        EnumMap<Menu, Integer> orderMap = new EnumMap<>(Menu.class);
        orderMap.put(Menu.T_BONE_STEAK, 5);
        orderMap.put(Menu.CHOCOLATE_CAKE, 4);

        Order order = new Order(27, orderMap);

        discountPolicySet.summarizeDiscountBenefit(benefitDetails, order);

        //할인 금액은 평일 할인(2023 * 4 = 8092)
        assertThat(benefitDetails.getTotalDiscountAmount()).isEqualTo(8092);
    }
}