package christmas.dto.input;

public class DayDto {
    int dayOfMonth;

    public DayDto(String input) {
        try {
            int day = Integer.parseInt(input);
            validateDay(day);

            dayOfMonth = day;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
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
