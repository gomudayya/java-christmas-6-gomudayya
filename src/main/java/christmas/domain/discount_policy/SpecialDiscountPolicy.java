package christmas.domain.discount_policy;

import christmas.domain.Order;
import christmas.domain.Receipt;

import java.util.Set;

public class SpecialDiscountPolicy implements DiscountPolicy{
    private final Set<Integer> specialDiscountDays = Set.of(3, 10, 17, 24, 25, 31);
    @Override
    public int getDiscountAmount(Order order) {
        if(specialDiscountDays.contains(order.getDayOfMonth())) {
            return 1000;
        }

        return 0;
    }

}
