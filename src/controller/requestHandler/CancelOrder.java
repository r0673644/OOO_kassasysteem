package controller.requestHandler;

import controller.Controller;
import controller.OrderController;

public class CancelOrder extends RequestHandler{
    Controller controller;
    @Override
    public void handleRequest() {
        controller=getController();
        controller.getService().cancelOrder();
        ((OrderController)controller).getOrderPane().createPriceLabel();
        controller.handleRequest("CloseOverview");
    }
}
