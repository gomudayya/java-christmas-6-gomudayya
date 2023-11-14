package christmas.domain.gift_policy;

import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.domain.gift_policy.concrete.ChampagneGiftPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("총 결제 금액이 12만원 이상이면 샴페인 이벤트의 자격이 있다.")
class ChampagneGiftPolicyTest {

    ChampagneGiftPolicy champagneGiftEvent = new ChampagneGiftPolicy();

    @DisplayName("총 결제 금액이 12만원 이상이면 샴페인 수량 1개를 반환한다..")
    @Test
    void hasChampagneEligibility() {
        EnumMap<Menu, Integer> menuMap = new EnumMap<>(Menu.class);
        menuMap.put(Menu.T_BONE_STEAK, 2);                              // 55_000 * 2
        menuMap.put(Menu.CHOCOLATE_CAKE, 1);                            // 15_000 * 1     총 합 : 125_000

        Order order = new Order(23, menuMap);
        assertThat(champagneGiftEvent.getGiftQuantity(order)).isEqualTo(1);

        menuMap.remove(Menu.CHOCOLATE_CAKE);                            //-15_000
        menuMap.put(Menu.ICE_CREAM, 2);                                 // + 5_000 * 2     총 합 : 120_000

        order = new Order(23, menuMap);
        assertThat(champagneGiftEvent.getGiftQuantity(order)).isEqualTo(1);
    }

    @DisplayName("총 결제 금액이 12만원이 안되면 샴페인 수량 0개를 반환한다.")
    @Test
    void dontHaveChampagneEligibility() {
        EnumMap<Menu, Integer> menuMap = new EnumMap<>(Menu.class);
        menuMap.put(Menu.T_BONE_STEAK, 2);                                  // 55_000 * 2
        menuMap.put(Menu.ICE_CREAM, 1);                                     // 5_000 * 1     총 합 : 115_000

        Order order = new Order(23, menuMap);
        assertThat(champagneGiftEvent.getGiftQuantity(order)).isEqualTo(0);
    }
}