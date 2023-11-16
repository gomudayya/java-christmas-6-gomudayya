package christmas.dto.input;

import christmas.constant.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("OrderDto의 검증 로직 테스트")
class OrderDtoTest {


    @DisplayName("유효하지 않은 형식 테스트")
    @Test
    void invalidFormat() {
        assertThatThrownBy(() -> new OrderDto("티-본-스테이크-1,제로콜라-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        assertThatThrownBy(() -> new OrderDto("티본스테이크:1,제로콜라-4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
    }

    @DisplayName("메뉴판에 없는 메뉴 주문")
    @Test
    void invalidMenu() {
        assertThatThrownBy(() -> new OrderDto("김치찌개-1,미역국-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
    }

    @DisplayName("음료수만 주문하면 유효하지 않은 주문이다.")
    @Test
    void onlyBeverage() {
        assertThatThrownBy(() -> new OrderDto("제로콜라-5,샴페인-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
    }

    @DisplayName("주문 수량이 최대 주문 수량을 넘어가면 유효하지 않은 주문이다.")
    @Test
    void overMaxQuantity() {
        assertThatThrownBy(() -> new OrderDto("티본스테이크-21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
    }
}