package controller.requestHandler;

import controller.Controller;
import controller.OrderController;

public class CloseOverview extends RequestHandler {
    Controller controller;
    @Override
    public void handleRequest() {
        controller = getController();
        ((OrderController)controller).getEndOrderPane().getStage().close();
    }
}
