package christmas.domain.dicount_policy.concrete;

import christmas.constant.Category;
import christmas.constant.DiscountType;
import christmas.domain.Order;
import christmas.domain.dicount_policy.DiscountPolicy;

import static christmas.config.DiscountConfig.WEEKDAY_DISCOUNT_AMOUNT;

public class WeekdayDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int dessertCount = order.countItemsInCategory(Category.DESSERT);

        if (order.isWeekdayOrder() && dessertCount > 0) {
            return dessertCount * WEEKDAY_DISCOUNT_AMOUNT;
        }

        return 0;
    }

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.WEEKDAY_DISCOUNT;
    }
}
