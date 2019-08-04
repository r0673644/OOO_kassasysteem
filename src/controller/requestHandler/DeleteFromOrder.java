package controller.requestHandler;

import controller.OrderController;
import view.DeleteFromOrderPane;

public class DeleteFromOrder extends RequestHandler {
    private OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        DeleteFromOrderPane deleteOrderPane = new DeleteFromOrderPane();
        deleteOrderPane.setController(controller);
        controller.setDeleteFromOrderPane(deleteOrderPane);
        controller.getDeleteFromOrderPane().getStage().show();
    }
}
