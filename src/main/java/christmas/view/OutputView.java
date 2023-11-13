package christmas.view;

import christmas.constant.Benefit;
import christmas.constant.Menu;
import christmas.domain.Receipt;

import java.util.EnumMap;
import java.util.Map;

public class OutputView {

    private final String NONE = "없음";
    private final String MENU_QUANTITY_FORMAT = "%s %d개\n";
    private final String MONEY_FORMAT = "%,d원\n";
    private final String MINUS_MONEY_FORMAT = "-%,d원\n";
    private final String BENEFIT_LIST_FORMAT = "%s: " + MINUS_MONEY_FORMAT;

    public void printEstimatedRecipe(Receipt receipt) {
        System.out.println("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");

        printOrderDetails(receipt);
        printPriceBeforeDiscount(receipt);
        printGiftList(receipt);
        printBenefitList(receipt);
        printTotalBenefitAmount(receipt);
        printPriceAfterDiscount(receipt);
        printEventBadge(receipt);
    }


    private void printOrderDetails(Receipt receipt) {
        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> order = receipt.getOrder();

        order.forEach((menu, quantity) -> System.out.printf(MENU_QUANTITY_FORMAT, menu.getName(), quantity));
        System.out.println();
    }

    private void printPriceBeforeDiscount(Receipt receipt) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(MONEY_FORMAT, receipt.getPriceBeforeDiscount());
        System.out.println();
    }

    private void printGiftList(Receipt receipt) {
        System.out.println("<증정 메뉴>");
        Map<Menu, Integer> giftMenus = receipt.getGiftMenus();

        if (giftMenus.isEmpty()) {
            System.out.println(NONE);
        }
        giftMenus.forEach((menu, quantity) -> System.out.printf(MENU_QUANTITY_FORMAT, menu.getName(), quantity));
        System.out.println();
    }

    private void printBenefitList(Receipt receipt) {
        System.out.println("<혜택 내역>");
        EnumMap<Benefit, Integer> benefitList = receipt.getBenefitList();

        if (benefitList.isEmpty()) {
            System.out.println(NONE);
            return;
        }
        if (!benefitList.isEmpty()) {
            benefitList.forEach((benefit, money) -> System.out.printf(BENEFIT_LIST_FORMAT, benefit.getName(), money));
        }

        System.out.println();
    }

    private void printTotalBenefitAmount(Receipt receipt) {
        System.out.println("<총혜택 금액>");
        int totalBenefit = receipt.getTotalBenefit();

        if (totalBenefit == 0) {
            System.out.printf(MONEY_FORMAT, totalBenefit);
            return;
        }
        if (totalBenefit > 0) {
            System.out.printf(MINUS_MONEY_FORMAT, totalBenefit);
        }

        System.out.println();
    }

    private void printPriceAfterDiscount(Receipt receipt) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.printf(MONEY_FORMAT, receipt.getPriceAfterDiscount());
        System.out.println();
    }

    private void printEventBadge(Receipt receipt) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(receipt.getBadgeName());
        System.out.println();
    }
}
