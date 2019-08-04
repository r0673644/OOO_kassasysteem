package database;

import database.discountStrategy.MostExpensiveStrategy;
import database.discountStrategy.NoDiscountStrategy;
import database.discountStrategy.PriceCalculationStrategy;
import model.Article;
import model.Observable;
import model.Observer;
import model.Order;
import model.orderState.*;

import java.util.*;

public class OrderDBLocal implements Observable,Observer {
    private static OrderDBLocal uniqueInstance;
    private List<Observer> observerList;
    private List<Order> allOrders = new ArrayList();
    private List<Order> onHold = null;
    private PriceCalculationStrategy priceCalculationStrategy;

    public OrderDBLocal() {
        observerList = new ArrayList<>();
        priceCalculationStrategy = new MostExpensiveStrategy(50);
    }
    public static synchronized OrderDBLocal getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new OrderDBLocal();
        }
        return uniqueInstance;
    }
    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(Object args) {
        for (Observer o : observerList) {
            o.update(this, args);
        }
    }
    public void setPriceCalculationStrategy(PriceCalculationStrategy priceCalculationStrategy){
        this.priceCalculationStrategy=priceCalculationStrategy;
    }
    public PriceCalculationStrategy getPriceCalculationStrategy(){
        return priceCalculationStrategy;
    }
    public List getAllOrders() {
        return allOrders;
    }

    public double calculateTotalPrice() {
       return priceCalculationStrategy.calculatePrice(allOrders);
    }

    public double calculateTotalPrizeNoDiscount(){
        PriceCalculationStrategy strat = new  NoDiscountStrategy();
        return  strat.calculatePrice(allOrders);
    }
    public List getOrder(){
        return allOrders;
    }

    public void placeOrder(Article articleToAdd) {
        boolean added = false;
        if (articleToAdd.getActualStorage() - 1 >= 0) {
            for (Order order : allOrders) {
                if (order.getProductCode() == articleToAdd.getProductCode()) {
                    order.raiseAmount();
                    added = true;
                }
            }
            if (!added) {
                allOrders.add(new Order(articleToAdd, 1));
            }
        }
        changeState("Ongoing");
    }
    public void clearOrder() {
        allOrders = new ArrayList<>();
    }

    public void deleteOrder(int code) {
        for (Order order:allOrders) {
            if (order.getProductCode() == code) {
                order.lowerAmount();
                if(order.getAmount()<0){
                    throw new IllegalArgumentException("No more articles to delete.");
                }
            }
        }
    }

    public void placeOnHold() {
        changeState("On hold");
        onHold=allOrders;
    }
    public void resumeOnHold() {
        changeState("Ongoing");
        allOrders=onHold;
    }

    @Override
    public void update(Observable o, Object args) {
        updateStrategy();
    }

    private void updateStrategy() {

    }
    public String getResultOverview(){
        String result = "Total prize: " + calculateTotalPrizeNoDiscount() + "\nPrize with discount: " + calculateTotalPrice() +"\nDiscount: " + calculateDiscount();
        return result;
    }

    private double calculateDiscount() {
        return calculateTotalPrizeNoDiscount()-calculateTotalPrice();
    }

    public void cancelOrder() {
        changeState("Canceled");
        clearOrder();
        changeState("Ready");
    }
    public void changeState(String state){
        try {
            for (Order o: allOrders){
                switch (state){
                    case "Ready":
                        o.orderready();
                        break;
                    case "Ongoing":
                        o.orderOngoing();
                        break;
                    case"Paid":
                        o.orderPaid();
                        break;
                    case "Canceled":
                        o.cancelOrder();
                        break;
                    case "On hold":
                        o.orderOnHold();
                        break;
                        default:o.orderready();
                }
            }
            //System.out.println(state);
        } catch (IllegalStateOperationException e) {
            System.out.println(e.getMessage());
        }
    }

    public double calculateTotalPriceWithoutTaxes() {
        return calculateTotalPrice()-(calculateTotalPrice()*6/100);
    }

    public double calculateTaxes(){
        return calculateTotalPrice()-calculateTotalPriceWithoutTaxes();
    }
}
