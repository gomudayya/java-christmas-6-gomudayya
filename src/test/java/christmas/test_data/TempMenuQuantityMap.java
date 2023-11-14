package christmas.test_data;

import christmas.constant.Menu;

import java.util.EnumMap;

public class TempMenuQuantityMap {
    public static final EnumMap<Menu, Integer> TEMP_MENU_QUANTITY_MAP = new EnumMap<>(Menu.class);

    static {
        TEMP_MENU_QUANTITY_MAP.put(Menu.T_BONE_STEAK, 2);
    }
}
