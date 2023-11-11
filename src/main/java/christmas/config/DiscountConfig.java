package christmas.config;

import java.util.Set;

public class DiscountConfig {
    public static final int DDAY_DISCOUNT_BASE_AMOUNT = 900;
    public static final int DDAY_DISCOUNT_AMOUNT_PER_DAY = 100;
    public static final int DDAY_DISCOUNT_START_DAY = 1;
    public static final int DDAY_DISCOUNT_END_DAY = 25;
    public static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;
    public static final int WEEKEND_DISCOUNT_AMOUNT = 2023;
    public static final int SPECIAL_DISCOUNT_AMOUNT = 1000;
    public static final Set<Integer> SPECIAL_DISCOUNT_DAYS = Set.of(3, 10, 17, 24, 25, 31);
}
