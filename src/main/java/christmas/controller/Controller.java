package christmas.controller;

import christmas.domain.Order;
import christmas.domain.Receipt;
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
        int dayOfMonth = inputView.readDate();

        Order order = inputView.readOrder(dayOfMonth);

        Receipt receipt = paymentService.getEstimatedReceipt(order);

        outputView.printEstimatedRecipe(receipt);
    }
}
