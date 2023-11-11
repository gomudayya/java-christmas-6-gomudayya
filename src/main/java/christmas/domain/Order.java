package christmas.domain;

import christmas.constant.Category;
import christmas.constant.Menu;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

public class Order {

    private LocalDate localDate = LocalDate.of(2023, 12, 1);
    private Map<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class); // 메뉴 수량


    public Order(String order) {
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
                .filter(entry -> entry.getKey().isInCategory(category))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }
    public int getDayOfMonth() {
        return localDate.getDayOfMonth();
    }
}
