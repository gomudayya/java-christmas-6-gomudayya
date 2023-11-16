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

    public DayDto readDate() {
        System.out.println(INFORMATION_MESSAGE);
        return processInput(DayDto::new);
    }

    public OrderDto readOrder() {
        System.out.println(ORDER_MESSAGE);
        return processInput(OrderDto::new);
    }

    private <R> R processInput(Function<String, R> inputDtoConstructor) {
        while (true) {
            try {
                String input = Console.readLine();
                return inputDtoConstructor.apply(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
