package christmas.dto.input;

import christmas.domain.Order;

public class OrderDto {

    private int dayOfMonth;


    public OrderDto(String input) {

    }

    public Order toOrder() {
        return new Order("aa");
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
