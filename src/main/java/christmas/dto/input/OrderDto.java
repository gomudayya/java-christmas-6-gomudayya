package christmas.dto.input;

import christmas.constant.Category;
import christmas.constant.ErrorMessage;
import christmas.constant.Menu;
import christmas.domain.Order;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.regex.Pattern;

public class OrderDto {
    private static final Pattern pattern = Pattern.compile("([가-힣]+)-([0-9]+)(,([가-힣]+)-([0-9]+))*");
    private final EnumMap<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class); // 메뉴 수량

    public OrderDto(String input) {
        input = input.replaceAll("\\s", "");

        try {
            validate(input);
            parseMenuOrder(input);
            if (hasOnlyBeverage()) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private void validate(String input) {
        validateFormat(input);
        validateTotalCount(input);
    }

    private void validateFormat(String input) {
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException();
        }
    }

    private void validateTotalCount(String input) {
        String[] menuAndQuantity = input.split(",");

        if (getTotalOrderCount(menuAndQuantity) > 20) {
            throw new IllegalArgumentException();
        }
    }

    private static int getTotalOrderCount(String[] menuAndQuantity) {
        return Arrays.stream(menuAndQuantity).mapToInt(menuQuantity -> {
            String[] split = menuQuantity.split("-");
            return Integer.parseInt(split[1]);
        }).sum();
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

    private boolean hasOnlyBeverage() {
        return menuQuantityMap.keySet().stream()
                .allMatch(menu -> menu.belongToCategory(Category.BEVERAGE));
    }


    public Order toOrder(DayDto dayDto) {
        return new Order(dayDto.getDayOfMonth(), menuQuantityMap);
    }
}
