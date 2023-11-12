package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.dto.input.DayDto;
import christmas.dto.input.OrderDto;

import java.util.function.Function;

public class InputView {
    private final String INFORMATION_MESSAGE = """
            안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)""";

    private final String ORDER_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public int readDate() {
        System.out.println(INFORMATION_MESSAGE);

        DayDto dayDto = processInput(DayDto::new);

        return dayDto.getDayOfMonth();
    }

    public Order readOrder(int dayOfMonth) {
        System.out.println(ORDER_MESSAGE);

        OrderDto orderDto = processInput(OrderDto::new);

        return orderDto.toOrder();
    }

    private <R> R processInput(Function<String, R> function) {
        while (true) {
            try {
                String input = Console.readLine();
                R dto = function.apply(input);
                return dto;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
