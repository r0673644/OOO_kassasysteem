package controller.requestHandler;

import controller.OrderController;
import view.OrderPane;

public class ConfirmOrder extends RequestHandler {
    private OrderController controller;

    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        OrderPane orderPane = controller.getOrderPane();
        int code = orderPane.getCodeFieldInt();
        controller.getService().placeOrder(code);
        clearInputFields();
        controller.getPane().getStage().close();
    }

    public void clearInputFields() {
        controller.getOrderPane().clearInputFields();
    }
}
