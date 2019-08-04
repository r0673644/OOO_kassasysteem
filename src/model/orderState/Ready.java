package model.orderState;

import model.Order;

public class Ready extends OrderState{
    private Order order;

    public Ready(Order order){
        super(order);
    }
    @Override
    public void ongoing() {
        getOrder().setCurrentState(getOrder().getOngoingState());
    }
}
