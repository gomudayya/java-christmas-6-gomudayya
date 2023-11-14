package christmas.validator;

import christmas.config.OrderConfig;
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
        if (isOverMaxQuantity(menuQuantityMap)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER_ERROR.getMessage());
        }
    }

    private static boolean hasOnlyBeverage(EnumMap<Menu, Integer> menuQuantityMap) {
        return menuQuantityMap.keySet().stream()
                .allMatch(menu -> menu.belongToCategory(Category.BEVERAGE));
    }

    private static boolean isOverMaxQuantity(EnumMap<Menu, Integer> menuQuantityMap) {
        int totalQuantity = menuQuantityMap.values().stream().mapToInt(Integer::intValue).sum();
        return totalQuantity > MAX_ORDER_QUANTITY;
    }
}
