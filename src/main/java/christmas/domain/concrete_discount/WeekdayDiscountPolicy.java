package christmas.domain.concrete_discount;

import christmas.constant.Category;
import christmas.domain.DiscountPolicy;
import christmas.domain.Order;

import static christmas.config.DiscountConfig.WEEKDAY_DISCOUNT_AMOUNT;

public class WeekdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int dessertCount = order.countItemsInCategory(Category.DESSERT);

        if (order.isWeekday() && dessertCount > 0) {
            return dessertCount * WEEKDAY_DISCOUNT_AMOUNT;
        }

        return 0;
    }
}
