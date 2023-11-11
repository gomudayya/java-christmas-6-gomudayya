package christmas.domain.discount_policy;

import christmas.constant.Category;
import christmas.domain.Order;
import christmas.domain.Receipt;

public class WeekdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int dessertCount = order.countItemsInCategory(Category.DESSERT);

        if (order.isWeekday() && dessertCount > 0) {
            return dessertCount * 2023;
        }

        return 0;
    }
}
