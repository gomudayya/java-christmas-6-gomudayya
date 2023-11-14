package christmas.domain;

import christmas.constant.DiscountType;
import christmas.constant.Menu;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("이 클래스는 할인(Discount) 혜택 내용과 선물(Gift) 혜택 내용이 담긴 BenefitDetails를 넘겨주는 클래스이다.")
class ChristmasBenefitSetTest {
    ChristmasBenefitSet christmasBenefitSet = new ChristmasBenefitSet();
    Order order;

    @BeforeEach
    @DisplayName("테스트용 order 만들기")
    void createOrder() {
        EnumMap<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class);

        menuQuantityMap.put(Menu.TAPAS, 3);                 // 5500 * 3
        menuQuantityMap.put(Menu.CAESAR_SALAD, 5);          // 8000 * 5
        menuQuantityMap.put(Menu.ZERO_COLA, 2);             // 3000 * 2
        menuQuantityMap.put(Menu.BBQ_RIB, 1);               // 54000
        menuQuantityMap.put(Menu.MUSHROOM_SOUP, 3);         // 6000 * 3

        order = new Order(17, menuQuantityMap);  // total : 134_500원

        /***
         *         할인 내역 : 17일 -> 평일 할인, 특별 할인, D-Day 할인 대상
         *         하지만 디저트메뉴가 없어서 평일할인은 X
         *
         *         총 할인 금액: 특별 할인 ( 1000 ) + D-Day 할인 ( 2600 ) = 3600
         *         선물 내역 : 총 결제액이 12만원이 넘으므로 샴페인 선물 대상
         *         총 혜택 : 총 할인 (3600) + 샴페인 1개선물(25_000) = 28600
         */
    }


    @Test
    void getBenefitDetails() {
        BenefitDetails benefitDetails = christmasBenefitSet.getBenefitDetails(order);

        Map<DiscountType, Integer> discountList = benefitDetails.getDiscountList();
        Map<Menu, Integer> giftList = benefitDetails.getGiftList();
        int totalDiscountAmount = benefitDetails.getTotalDiscountAmount();
        int totalBenefit = benefitDetails.getTotalBenefit();

        assertThat(discountList).containsEntry(DiscountType.D_DAY_DISCOUNT, 2600);
        assertThat(discountList).containsEntry(DiscountType.SPECIAL_DISCOUNT, 1000);
        assertThat(discountList).doesNotContainKey(DiscountType.WEEKDAY_DISCOUNT);
        assertThat(giftList).containsEntry(Menu.CHAMPAGNE, 1);
        assertThat(totalDiscountAmount).isEqualTo(3600);
        assertThat(totalBenefit).isEqualTo(28600);
    }
}