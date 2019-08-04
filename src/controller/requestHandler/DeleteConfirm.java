package controller.requestHandler;

import controller.OrderController;
import view.DeleteFromOrderPane;

public class DeleteConfirm extends RequestHandler{
    private OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        DeleteFromOrderPane deletePane = controller.getDeleteFromOrderPane();
        int code = deletePane.getCodeFieldInt();
        controller.getService().deleteOrder(code);
        controller.getDeleteFromOrderPane().getStage().close();
        controller.getPane().getStage().close();
        controller.getOrderPane().createPriceLabel();
    }
}
