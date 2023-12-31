package christmas.domain;

import christmas.constant.Category;
import christmas.constant.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static christmas.config.OrderConfig.MAX_ORDER_QUANTITY;
import static christmas.test_data.TempMenuQuantityMap.TEMP_MENU_QUANTITY_MAP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Order의 각 비즈니스 메서드 테스트")
class OrderTest {
    static Order order;

    @BeforeAll
    @DisplayName("테스트용 order 만들기")
    static void createTestingOrder() {
        EnumMap<Menu, Integer> menuMap = new EnumMap<>(Menu.class); //<메뉴,수량>

        menuMap.put(Menu.T_BONE_STEAK, 3); // 메인메뉴   // 55_000 * 3
        menuMap.put(Menu.ICE_CREAM, 5); // 디저트       // 5_000 * 5
        menuMap.put(Menu.CHOCOLATE_CAKE, 2); // 디저트  // 15_000 * 2
        menuMap.put(Menu.TAPAS, 8); // 에피타이저       // 5_500 * 8
        menuMap.put(Menu.CHAMPAGNE, 2); // 음료        // 25_000 * 2  // 총 합 = 314_000

        order = new Order(27, menuMap);
    }

    @Test
    @DisplayName("유효하지 않은 Order 테스트")
    void invalidOrderTest() {
        EnumMap<Menu, Integer> onlyBeverage = new EnumMap<>(Menu.class);
        onlyBeverage.put(Menu.ZERO_COLA, 3);

        EnumMap<Menu, Integer> overMaxQuantity = new EnumMap<>(Menu.class);
        overMaxQuantity.put(Menu.TAPAS, MAX_ORDER_QUANTITY + 1);

        assertThatThrownBy(() -> new Order(22, onlyBeverage))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Order(11, overMaxQuantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void countItemsInCategory() {
        assertThat(order.countItemsInCategory(Category.MAIN_COURSE)).isEqualTo(3);
        assertThat(order.countItemsInCategory(Category.DESSERT)).isEqualTo(7);
        assertThat(order.countItemsInCategory(Category.APPETIZER)).isEqualTo(8);
        assertThat(order.countItemsInCategory(Category.BEVERAGE)).isEqualTo(2);
    }

    @Test
    void getTotalPrice() {
        assertThat(order.getTotalPrice()).isEqualTo(314_000);
    }

    @Test
    void isWeekend() {
        Order order = new Order(22, TEMP_MENU_QUANTITY_MAP);
        assertThat(order.isWeekendOrder()).isTrue();
    }

    @Test
    void isWeekday() {
        Order order = new Order(27, TEMP_MENU_QUANTITY_MAP);
        assertThat(order.isWeekdayOrder()).isTrue();
    }

}