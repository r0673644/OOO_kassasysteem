package controller;

import view.EndOrderPane;
import view.DeleteFromOrderPane;
import view.Pane;
import view.OrderPane;

public class OrderController extends Controller {
    private OrderPane orderPane;
    private DeleteFromOrderPane deleteFromOrderPane;
    private EndOrderPane endOrderPane;
    private Pane pane;

    public OrderController(){
        pane = new Pane();
    }

    public Pane getPane() {
        return pane;
    }

    @Override
    protected Controller getController() {
        return this;
    }

    public void setOrderPane(OrderPane pane){
        orderPane=pane;
    }

    public OrderPane getOrderPane(){
    return orderPane;
    }

    public void setEndOrderPane(EndOrderPane pane){
        endOrderPane=pane;
    }

    public EndOrderPane getEndOrderPane(){
        return endOrderPane;
    }


    public void setDeleteFromOrderPane(DeleteFromOrderPane deleteOrderPane) {
        this.deleteFromOrderPane = deleteOrderPane;
    }
    public DeleteFromOrderPane getDeleteFromOrderPane(){
        return deleteFromOrderPane;
    }

    public void setOnHold() {
        getService().putOnHold();
    }
    public void resumeOnHold(){
        getService().resumeOnHold();    }
}
