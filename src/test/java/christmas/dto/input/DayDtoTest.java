package christmas.dto.input;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("DayDto의 검증로직 테스트")
class DayDtoTest {

    @DisplayName("유효하지 않은 날짜 테스트")
    @Test
    void invalidDayTest() {
        assertThatThrownBy(() -> new DayDto("32"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE_ERROR.getMessage());

        assertThatThrownBy(() -> new DayDto("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_DATE_ERROR.getMessage());

        new DayDto("1");
        new DayDto("31");
    }

}