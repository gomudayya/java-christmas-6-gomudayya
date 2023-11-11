package christmas.domain;

import christmas.constant.Menu;

import java.util.EnumMap;
import java.util.Map;

public class Receipt {
    private int dayOfMonth;
    private final Map<Menu, Integer> order = new EnumMap<>(Menu.class);

    private int totalPrice;

}
