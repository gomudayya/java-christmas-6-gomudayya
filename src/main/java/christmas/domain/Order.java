package christmas.domain;

import christmas.constant.Category;
import christmas.constant.Menu;
import christmas.validator.OrderValidator;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static christmas.config.EventConfig.EVENT_MONTH;
import static christmas.config.EventConfig.EVENT_YEAR;

public class Order {
    private final LocalDate localDate;
    private final EnumMap<Menu, Integer> menuQuantityMap;

    public Order(int dayOfMonth, EnumMap<Menu, Integer> menuQuantityMap) {
        validate(dayOfMonth, menuQuantityMap);
        this.localDate = LocalDate.of(EVENT_YEAR, EVENT_MONTH, dayOfMonth);
        this.menuQuantityMap = menuQuantityMap;

    }

    private void validate(int dayOfMonth, EnumMap<Menu, Integer> menuQuantityMap) {
        validateDayOfMonth(dayOfMonth);
        OrderValidator.validateMenuQuantity(menuQuantityMap);
    }

    private void validateDayOfMonth(int dayOfMonth) {
        if (dayOfMonth < 1 || dayOfMonth > 31) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isWeekendOrder() {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY || localDate.getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isWeekdayOrder() {
        return !isWeekendOrder();
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
