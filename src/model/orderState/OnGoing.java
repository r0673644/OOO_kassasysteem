package model.orderState;

import model.Order;

public class OnGoing extends OrderState {
    public OnGoing(Order order) {
        super(order);
    }
    @Override
    public void onHold() {
        getOrder().setCurrentState(getOrder().getOnholdState());
    }
    @Override
    public void pay() {
        getOrder().setCurrentState(getOrder().getPaidState());
    }
    @Override
    public void cancelOrder() {
        getOrder().setCurrentState(getOrder().getCanceledState());
    }

    @Override
    public void ongoing(){
        getOrder().setCurrentState(getOrder().getOngoingState());
    }
}
