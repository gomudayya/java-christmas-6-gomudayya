package christmas.validator;

import christmas.constant.Category;
import christmas.constant.ErrorMessage;
import christmas.constant.Menu;

import java.util.EnumMap;

import static christmas.config.OrderConfig.MAX_ORDER_QUANTITY;

public class OrderValidator {

    public static void validateMenuQuantity(EnumMap<Menu, Integer> menuQuantityMap) {
        if (menuQuantityMap == null) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
        if (hasOnlyBeverage(menuQuantityMap)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
        if (isNotInRangeOfQuantity(menuQuantityMap)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static boolean hasOnlyBeverage(EnumMap<Menu, Integer> menuQuantityMap) {
        return menuQuantityMap.keySet().stream()
                .allMatch(menu -> menu.belongToCategory(Category.BEVERAGE));
    }

    private static boolean isNotInRangeOfQuantity(EnumMap<Menu, Integer> menuQuantityMap) {
        int totalQuantity = menuQuantityMap.values().stream().mapToInt(Integer::intValue).sum();
        return totalQuantity > MAX_ORDER_QUANTITY || totalQuantity <= 0;
    }
}
