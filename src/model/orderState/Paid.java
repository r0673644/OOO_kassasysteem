package model.orderState;

import model.Order;

public class Paid extends OrderState {
    public Paid(Order order) {
        super(order);
    }


    @Override
    public void ready() {
        getOrder().setCurrentState(getOrder().getReadyState());
    }
}
