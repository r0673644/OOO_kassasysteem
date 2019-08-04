package model;


import model.orderState.*;

public class Order {
    private Article article;
   private int amount;
   //states
    private OrderState currentState;
    private Canceled canceledState;
    private OnGoing ongoingState;
    private OnHold onholdState;
    private Paid paidState;
    private Ready readyState;

   public Order(Article article, int amount){
        this.amount=amount;
        this.article=article;
        this.currentState = new Canceled(this);
        this.ongoingState = new OnGoing(this);
       this.onholdState = new OnHold(this);
       this.paidState=new Paid(this);
       this.readyState=new Ready(this);
       this.currentState=readyState;
   }
    public double calculatePrice(){
        return article.getPrice() * amount;
    }
    public int getProductCode() {
       return article.getProductCode();
    }
    public String getProductDescription() {
        return article.getProductDescription();
    }

    public double getPrice() {
        return article.getPrice();
    }

    public String getArticleGroup(){
        return article.getArticleGroup();
   }

    public int getAmount(){return amount;}

    public void raiseAmount() {
       amount++;
    }

    public void lowerAmount() {
       amount--;
    }

    public Article getArticle() {
       return article;
    }

    //States

    public String getCurrentStateName() {
        if (currentState instanceof Ready) {
            return "Ready";
        } else if (currentState instanceof OnGoing) {
            return "Ongoing";
        } else if (currentState instanceof Paid) {
            return "Paid";
        } else if (currentState instanceof Canceled) {
            return "Canceled";
        } else if (currentState instanceof OnHold) {
            return "On hold";
        } else{
            return "Unknown state";
    }
    }
    public void setCurrentState(OrderState state){
        this.currentState = state;
    }
    public OrderState getCurrentState() {
        return currentState;
    }

    public Canceled getCanceledState() {
        return canceledState;
    }

    public OnGoing getOngoingState() {
        return ongoingState;
    }

    public OnHold getOnholdState() {
        return onholdState;
    }

    public Paid getPaidState() {
        return paidState;
    }

    public Ready getReadyState() {
        return readyState;
    }
    public void cancelOrder() throws IllegalStateOperationException{
       this.currentState.cancelOrder();
    }
    public void orderOngoing() throws IllegalStateOperationException{
        this.currentState.ongoing();
    }
    public void orderOnHold() throws IllegalStateOperationException{
        this.currentState.onHold();
    }
    public void orderPaid() throws IllegalStateOperationException{
        this.currentState.pay();
    }
    public void orderready() throws IllegalStateOperationException{
        this.currentState.ready();
    }


    public void setCurrentStateTo(String state) {
    }
}
