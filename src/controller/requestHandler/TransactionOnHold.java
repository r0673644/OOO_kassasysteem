package controller.requestHandler;

import controller.OrderController;

public class TransactionOnHold extends RequestHandler{
    private OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        controller.setOnHold();
        clearInputFields();
        controller.getPane().getStage().close();
        }
    public void clearInputFields() {
        controller.getOrderPane().clearInputFields();
    }
}
