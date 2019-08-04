package controller.requestHandler;

import controller.OrderController;
import view.EndOrderPane;

public class ShowEndOrderPane extends RequestHandler {
    private OrderController controller;
    @Override
    public void handleRequest() {
        controller = (OrderController) getController();
        EndOrderPane endOrderPane = new EndOrderPane();
        endOrderPane.setController(controller);
        endOrderPane.setOverviewField(controller.getService().getOrderDBLocal().getResultOverview());
        controller.setEndOrderPane(endOrderPane);
        controller.getEndOrderPane().getStage().show();
    }
}
