package christmas.domain;

import christmas.constant.Category;
import christmas.constant.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Order {

    private final LocalDate localDate;
    private final EnumMap<Menu, Integer> menuQuantityMap;

    public Order(LocalDate localDate, EnumMap<Menu, Integer> menuQuantityMap) {
        this.localDate = localDate;
        this.menuQuantityMap = menuQuantityMap;
    }

    public Order(int dayOfMonth, EnumMap<Menu, Integer> menuQuantityMap) {
        this.localDate = LocalDate.of(2023, 12, dayOfMonth);
        this.menuQuantityMap = menuQuantityMap;
    }

    public boolean isWeekend() {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }

    public int countItemsInCategory(Category category) {
        return menuQuantityMap.entrySet().stream()
                .filter(entry -> entry.getKey().belongToCategory(category))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int getDayOfMonth() {
        return localDate.getDayOfMonth();
    }

    public int getTotalPrice() {
        return menuQuantityMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public Map<Menu, Integer> getMenuQuantityMap() {
        return Collections.unmodifiableMap(menuQuantityMap);
    }
}
