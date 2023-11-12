package christmas.domain.concrete_discount;

import christmas.constant.Category;
import christmas.constant.BenefitType;
import christmas.domain.DiscountPolicy;
import christmas.domain.Order;

import static christmas.config.DiscountConfig.WEEKEND_DISCOUNT_AMOUNT;

public class WeekendDiscountPolicy implements DiscountPolicy {
    @Override
    public int getDiscountAmount(Order order) {
        int mainCourseCount = order.countItemsInCategory(Category.MAIN_COURSE);

        if (order.isWeekend() && mainCourseCount > 0) {
            return mainCourseCount * WEEKEND_DISCOUNT_AMOUNT;
        }

        return 0;
    }

    @Override
    public BenefitType getBenefitType() {
        return BenefitType.WEEKEND_DISCOUNT;
    }
}
