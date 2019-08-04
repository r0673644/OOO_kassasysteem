package controller.requestHandler;

import controller.Controller;
import controller.OrderController;

public class CustomerPaid extends RequestHandler {
    private OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        controller.handleRequest("EndOrder");
        controller.handleRequest("CloseOverview");
        controller.getOrderPane().createPriceLabel();

    }
}
