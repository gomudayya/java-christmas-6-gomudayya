package christmas.domain.discount_policy;

import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.domain.discount_policy.constant.TestDate.*;

class DiscountPolicySetTest {
    DiscountPolicySet discountPolicySet = new DiscountPolicySet();

    @Test
    @DisplayName("d-day할인과, 주말 할인, 특별 할인을 적용받는 주문이 올바르게 할인 금액을 반환하는지 테스트")
    void getTotalDiscountAmount() {
        
    }
}