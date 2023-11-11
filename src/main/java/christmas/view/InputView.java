package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;

public class InputView {


    public int inputDate() {
        String day = Console.readLine();
        return Integer.parseInt(day);
    }


    public Order inputOrder() {
        String order = Console.readLine();
        return new Order(order);
    }
}
