package model.orderState;

import model.Order;

public abstract class OrderState {
    private final Order order;

    public OrderState(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void onHold()throws IllegalStateOperationException { throw new IllegalStateOperationException("An order with the status \"" + order.getCurrentStateName() + "\" cannot be placed on hold");}
    public void cancelOrder()throws IllegalStateOperationException { throw new IllegalStateOperationException("An order with the status \"" + order.getCurrentStateName() + "\" cannot be canceled");}
    public void pay()throws IllegalStateOperationException { throw new IllegalStateOperationException("An order with the status \"" + order.getCurrentStateName() + "\" cannot be paid by a customer.");}
    public void ongoing()throws IllegalStateOperationException { throw new IllegalStateOperationException("An order with the status \"" + order.getCurrentStateName() + "\" cannot be ongoing.");}
    public void ready()throws IllegalStateOperationException { throw new IllegalStateOperationException("An order with the status \"" + order.getCurrentStateName() + "\" cannot be ready.");}



}
