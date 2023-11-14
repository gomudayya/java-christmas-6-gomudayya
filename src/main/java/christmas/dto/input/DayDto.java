package christmas.dto.input;

import christmas.constant.ErrorMessage;

public class DayDto {
    int dayOfMonth;

    public DayDto(String input) {
        dayOfMonth = validateDay(input);
    }

    private int validateDay(String input) {
        int day = validateInteger(input);
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }

        return day;
    }

    private int validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
