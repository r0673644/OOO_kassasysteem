package model.orderState;

import model.Order;

public class Canceled extends OrderState {
    private Order order;

    public Canceled(Order order) {
        super(order);
    }


    @Override
    public void ready() {
        getOrder().setCurrentState(getOrder().getReadyState());
    }
}
