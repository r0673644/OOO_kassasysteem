package controller.requestHandler;

import controller.OrderController;

public class EndOrder extends RequestHandler {

    OrderController controller;

    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        controller.getService().endOrder();
        clearInputFields();
        controller.getPane().getStage().close();
    }

    public void clearInputFields() {
        controller.getOrderPane().clearInputFields();
    }
}
