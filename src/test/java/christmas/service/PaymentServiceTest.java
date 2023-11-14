package christmas.service;

import christmas.constant.DiscountType;
import christmas.constant.Menu;
import christmas.domain.Order;
import christmas.domain.Receipt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문에 대해 반환되는 영수증이 올바른지 테스트")
class PaymentServiceTest {
    PaymentService paymentService = new PaymentService();
    Order order;
    Receipt receipt;

    @DisplayName("테스트용 주문과 영수증 생성하기")
    @BeforeEach
    void createTestOrder() {
        EnumMap<Menu, Integer> menuQuantityMap = new EnumMap<>(Menu.class);

        menuQuantityMap.put(Menu.CHOCOLATE_CAKE, 5);        // 15000 * 5
        menuQuantityMap.put(Menu.BBQ_RIB, 5);               // 54000 * 5
        menuQuantityMap.put(Menu.MUSHROOM_SOUP, 2);         // 6000 * 2
        menuQuantityMap.put(Menu.TAPAS, 1);                 // 5500
        menuQuantityMap.put(Menu.SEAFOOD_PASTA, 3);         // 35000 * 3
        menuQuantityMap.put(Menu.CAESAR_SALAD, 2);          // 8000 * 2

        order = new Order(15, menuQuantityMap); //할인 전 금액 : 483,500
        receipt = paymentService.getEstimatedReceipt(order);

        /***
         *          할인내역
         *          15일 -> 주말 할인(메인메뉴 할인), D-day 할인 대상
         *          주말 할인 : BBQ 5개, 해산물 파스타 3개 = 총 2023 * 8 = 16184
         *          D-Day 할인 : 15일 -> 900 + 15 * 100 = 2400
         *          총 할인 금액 : 16184 + 2400 = 18584
         *          
         *          선물(증정)내역
         *          총 결제금액이 12만원 이상이므로 샴페인 1개(25_000원) 선물(증정)
         *
         *          총 혜택 내역
         *          총 할인 금액 (18584)   + 선물(증정) 금액 (25000)  = 43584 -> 
         *          
         *          뱃지
         *          혜택 내역이 20,000원을 넘어가므로 산타 뱃지 대상
         *
         *          할인 후 예상 금액 = 할인 전 금액 (483,500) - 할인 금액 (18,584) = 464,916
         */
    }

    @DisplayName("주문_내역이_올바르게_불러와지는지_테스트")
    @Test
    void orderDetailsTest() {
        Map<Menu, Integer> orderList = receipt.getOrderList();

        assertThat(orderList).containsEntry(Menu.CHOCOLATE_CAKE, 5);
        assertThat(orderList).containsEntry(Menu.BBQ_RIB, 5);
        assertThat(orderList).containsEntry(Menu.MUSHROOM_SOUP, 2);
        assertThat(orderList).containsEntry(Menu.TAPAS, 1);
        assertThat(orderList).containsEntry(Menu.SEAFOOD_PASTA, 3);
        assertThat(orderList).containsEntry(Menu.CAESAR_SALAD, 2);
        assertThat(orderList).doesNotContainKey(Menu.ZERO_COLA);
    }

    @DisplayName("할인 전 총 가격이 올바르게 반환되는지 테스트")
    @Test
    void priceBeforeDiscountTest() {
        int priceBeforeDiscount = receipt.getPriceBeforeDiscount();
        assertThat(priceBeforeDiscount).isEqualTo(483_500);
    }

    @DisplayName("증정 메뉴가 올바르게 반환되는지 테스트")
    @Test
    void giftMenuTest() {
        Map<Menu, Integer> giftList = receipt.getGiftList();
        assertThat(giftList).containsEntry(Menu.CHAMPAGNE, 1);
    }

    @DisplayName("할인 내역이 올바르게 반환되는지 테스트")
    @Test
    void BenefitDetailsTest() {
        Map<DiscountType, Integer> discountList = receipt.getDiscountList();

        assertThat(discountList).containsEntry(DiscountType.WEEKEND_DISCOUNT, 16184);
        assertThat(discountList).containsEntry(DiscountType.D_DAY_DISCOUNT, 2400);
        assertThat(discountList).doesNotContainKey(DiscountType.WEEKDAY_DISCOUNT);
        assertThat(discountList).doesNotContainKey(DiscountType.SPECIAL_DISCOUNT);
    }

    @DisplayName("총 혜택 금액이 올바르게 반환되는지 테스트")
    @Test
    void totalBenefitAmount() {
        int totalBenefit = receipt.getTotalBenefit();
        assertThat(totalBenefit).isEqualTo(43584);
    }

    @DisplayName("할인 후 예상 금액이 올바르게 반환되는지 테스트")
    @Test
    void priceAfterDiscountTest() {
        // 할인 후 예상 금액 = 할인 전 예상 금액 - 할인 적용 금액
        int priceAfterDiscount = receipt.getPriceAfterDiscount();
        assertThat(priceAfterDiscount).isEqualTo(464_916);
    }
    
    @DisplayName("혜택내역에 따라 뱃지를 올바르게 주는지 테스트")
    @Test
    void properBadgeTest() {
        String badgeName = receipt.getBadgeName();
        assertThat(badgeName).isEqualTo("산타");
    }
}