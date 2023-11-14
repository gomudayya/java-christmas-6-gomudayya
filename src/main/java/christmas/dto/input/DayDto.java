package christmas.dto.input;

import christmas.constant.ErrorMessage;

public class DayDto {
    int dayOfMonth;

    public DayDto(String input) {
        try {
            int day = Integer.parseInt(input);
            validateDay(day);

            dayOfMonth = day;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

    private void validateDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException();
        }
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
