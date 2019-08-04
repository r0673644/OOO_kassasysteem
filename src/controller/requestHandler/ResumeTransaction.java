package controller.requestHandler;

import controller.OrderController;

public class ResumeTransaction extends RequestHandler {
    OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        controller.resumeOnHold();
        clearInputFields();
        controller.getPane().getStage().close();
    }
    public void clearInputFields() {
        controller.getOrderPane().clearInputFields();
    }
    }
