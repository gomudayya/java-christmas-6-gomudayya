package christmas.test_data;

import christmas.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static christmas.test_data.TempMenuQuantityMap.TEMP_MENU_QUANTITY_MAP;

public enum TestDate {
    WEEKEND_DAY(2),
    WEEKDAY(3);
    private final int dayOfMonth;

    TestDate(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getValue() {
        return dayOfMonth;
    }

    static class TestDateTest {
        @DisplayName("테스트의 인자값으로 넣을 '일' 데이터가 올바른지 검증한다.")
        @Test
        void validateWeekendDayAndWeekDay() {
            Order weekendDayOrder = new Order(WEEKEND_DAY.getValue(), TEMP_MENU_QUANTITY_MAP);
            Order weekDayOrder = new Order(WEEKDAY.getValue(), TEMP_MENU_QUANTITY_MAP);

            if (weekendDayOrder.isWeekdayOrder()) {
                throw new IllegalArgumentException("WEEKEND_Day는 주말이어야 합니다");
            }
            if (weekDayOrder.isWeekendOrder()) {
                throw new IllegalArgumentException("WEEKDAY는 평일이어야 합니다.");
            }
        }
    }
}

