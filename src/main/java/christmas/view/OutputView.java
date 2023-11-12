package christmas.view;

import christmas.domain.Receipt;

public class OutputView {

    private final String INFORMATION_MESSAGE = "12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n";
    private final String ORDER_MENU = "<주문 메뉴>";
    private final String PRICE_BEFORE_DISCOUNT = "<할인 전 총주문 금액>";
    private final String GIFT_MENU = "<증정 메뉴>";
    private final String BENEFIT_LIST = "<혜택 내역>";
    private final String TOTAL_BENEFIT_AMOUNT = "<총혜택 금액>";
    private final String PRICE_AFTER_DISCOUNT = "<할인 후 예상 결제 금액>";
    private final String EVENT_BADGE_OF_DECEMBER = "<12월 이벤트 배지>";

    public void printEstimatedRecipe(Receipt receipt) {
        System.out.println(INFORMATION_MESSAGE);

        System.out.println(ORDER_MENU);
    }
}
