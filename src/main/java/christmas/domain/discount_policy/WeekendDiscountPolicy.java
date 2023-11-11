package christmas.domain.discount_policy;

import christmas.constant.Category;
import christmas.domain.Order;

public class WeekendDiscountPolicy implements DiscountPolicy{
    @Override
    public int getDiscountAmount(Order order) {
        int mainCourseCount = order.countItemsInCategory(Category.MAIN_COURSE);

        if(order.isWeekend() && mainCourseCount > 0) {
            return mainCourseCount * 2023;
        }

        return 0;
    }
}
