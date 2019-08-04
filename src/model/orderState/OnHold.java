package model.orderState;

import model.Order;

public class OnHold extends OrderState {
    public OnHold(Order order) {
        super(order);
    }

    @Override
    public void ongoing() {
        getOrder().setCurrentState(getOrder().getOngoingState());
    }
}
