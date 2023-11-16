package christmas.dto.input;

import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.validator.OrderValidator;

import java.util.EnumMap;
import java.util.regex.Pattern;

public class OrderDto {
    private static final Pattern pattern = Pattern.compile("([가-힣]+)-([0-9]+)(,([가-힣]+)-([0-9]+))*");
    private final EnumMap<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class); // 메뉴 수량

    public OrderDto(String input) {
        input = input.replaceAll("\\s", "");

        validateFormat(input);
        parseMenuOrder(input);
        OrderValidator.validateMenuQuantity(menuQuantityMap);
    }

    private void validateFormat(String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private void parseMenuOrder(String input) {
        String[] menuQuantity = input.split(",");

        for (String order : menuQuantity) {
            String[] split = order.split("-");

            String menuName = split[0];
            String quantity = split[1];

            menuQuantityMap.merge(Menu.getMenuByName(menuName), Integer.parseInt(quantity), Integer::sum);
        }
    }

    public Order toOrder(DayDto dayDto) {
        return new Order(dayDto.getDayOfMonth(), menuQuantityMap);
    }
}
