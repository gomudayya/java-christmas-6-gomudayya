package christmas.domain.discount_policy;

import christmas.domain.Order;
import christmas.domain.Receipt;

public class DdayDiscountPolicy implements DiscountPolicy{
    @Override
    public int getDiscountAmount(Order order) {
        int dayOfMonth = order.getDayOfMonth();
        if(1 <= dayOfMonth && dayOfMonth <= 25) {
            return 900 + (dayOfMonth * 100);
        }

        return 0;
    }
}
