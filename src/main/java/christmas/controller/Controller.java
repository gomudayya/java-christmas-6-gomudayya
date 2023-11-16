package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Receipt;
import christmas.dto.input.DayDto;
import christmas.dto.input.OrderDto;
import christmas.service.PaymentService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final PaymentService paymentService;

    public Controller(InputView inputView, OutputView outputView, PaymentService paymentService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.paymentService = paymentService;
    }

    public void launch() {
        DayDto dayDto = inputView.readDate();
        OrderDto orderDto = inputView.readOrder();

        Order order = orderDto.toOrder(dayDto);
        Receipt receipt = paymentService.getEstimatedReceipt(order);

        outputView.printEstimatedRecipe(receipt);
    }
}
