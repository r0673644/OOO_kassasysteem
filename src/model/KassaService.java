package model;

import controller.Controller;
import controller.OrderController;
import database.ArtikelDB;
import database.LogDB;
import database.OptionsDB;
import database.OrderDBLocal;
import database.discountStrategy.*;
import database.loadStrategy.ArticleExelLoadSaveStrategy;
import database.loadStrategy.ArticleTextLoadSaveStrategy;
import database.loadStrategy.LoadSaveStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.billDecorator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KassaService implements Observable{
    private List<Observer> observers;

    private LoadSaveStrategy saveStrategy;
    private PriceCalculationStrategy discountStrategy;

    private Controller articleController;
    private Controller optionsController;
    private Controller orderController;

    private ArtikelDB artikelDB;
    private OptionsDB optionsDB;
    private OrderDBLocal orderDBLocal;
    private LogDB logDB;

    public KassaService(){
        observers = new ArrayList<>();
        artikelDB = ArtikelDB.getInstance();
        optionsDB = OptionsDB.getInstance();
        orderDBLocal = OrderDBLocal.getInstance();
        logDB = LogDB.getInstance();
        setSaveStrategy();
        setDiscountStrategy();
    }

    private void setDiscountStrategy() {
        Discount strategy = optionsDB.getDiscountMethod();
        switch (strategy){
            case MOST_EXPENSIVE: discountStrategy = new MostExpensiveStrategy(optionsDB.getPercentage());
                break;
            case GROUP: discountStrategy = new GroupDiscountStrategy(optionsDB.getGroup(),optionsDB.getPercentage());
                break;
            case THRESHOLD: discountStrategy = new ThresholdStrategy(optionsDB.getThreshold(),optionsDB.getPercentage());
                break;
                default:discountStrategy = new NoDiscountStrategy();
        }
        orderDBLocal.setPriceCalculationStrategy(discountStrategy);
    }

    private void setSaveStrategy() {
        String strategy = optionsDB.getSaveMethod();
        switch (strategy.trim().toLowerCase()) {
            case "text":
                saveStrategy = new ArticleTextLoadSaveStrategy();
                break;
            case "exel":
                saveStrategy = new ArticleExelLoadSaveStrategy();
                break;
        }
    }

    public void loadDataFromStorageIntoLocalMemory() {
        artikelDB.loadDataInLocalMemory();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(Object args) {
        for (Observer o : observers) {
            o.update(this, null);
        }
        artikelDB.notifyObservers(args);
        orderDBLocal.notifyObservers(args);
    }

    public ArtikelDB getArtikelDB(){
        return artikelDB;
    }

    public LogDB getLogDB(){
        return logDB;
    }

    public void setArticleController(Controller articleController) {
        this.articleController = articleController;
    }

    public OptionsDB getOptionsDB() {
        return optionsDB;
    }
    public OrderDBLocal getOrderDBLocal(){return orderDBLocal;}
    public  void setOptionsController(Controller optionsController){
        this.optionsController = optionsController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }
    public void placeOrder(int articleCode){
        orderDBLocal.placeOrder(artikelDB.getArticleFromCode(articleCode));
        notifyObservers(null);
    }
    public void endOrder(){
       logOrder();
       printOrder();
       orderDBLocal.changeState("Paid");
       saveOrder();
       clearOrder ();
       orderDBLocal.changeState("Ready");
    }

    private void printOrder() {
        OrderBill bill = new OrderBill(orderDBLocal);
        BillDecorator decorator;
        switch (optionsDB.getBillDecorator()){
            case"Show Date And Time":
                decorator = new DateTimeHeaderDecorator(bill,getLogDB().getLastDate(), getLogDB().getLastTimestamp());
                decorator.printBill();
                break;
            case"Line As Header And Footer":
                decorator = new HeaderFooterDecorator(bill);
                decorator.printBill();
                break;
            case"Message In Footer":
                decorator= new MessageFooterDecorator(bill,optionsDB.getBillMessage());
                decorator.printBill();
                break;
            case"Message In Header":
                decorator=new MessageHeaderDecorator(bill,optionsDB.getBillMessage());
                decorator.printBill();
                break;
            case ("Show Discount"):
                decorator=new NoDiscountOnlyDiscountDecorator(bill,orderDBLocal);
                decorator.printBill();
                break;
            case"Show Taxes":
                decorator=new NoTaxDecorator(bill,orderDBLocal);
                decorator.printBill();
                break;
                default: bill.printBill();
        }
    }

    public void putOnHold(){
        orderDBLocal.placeOnHold();
        clearOrder();
    }

    private void saveOrder() {
        artikelDB.saveOrder(orderDBLocal.getAllOrders());
    }

    public double calculatePrice(){
        return orderDBLocal.calculateTotalPrice();
    }

    public void clearOrder() {
        orderDBLocal.clearOrder();
        notifyObservers(null);
    }

    public List getOrder() {
        return orderDBLocal.getOrder();
    }

    public void deleteOrder(int code) {
        orderDBLocal.deleteOrder(code);
        notifyObservers(null);
    }

    public void resumeOnHold() {
        orderDBLocal.resumeOnHold();
        notifyObservers(null);
    }

    public void cancelOrder() {
        orderDBLocal.cancelOrder();
        notifyObservers(null);
    }

    public void logOrder() {
        logDB.registerNewSale(orderDBLocal);
    }

    public String getFullLog() {
        return "**start log**\n" + logDB.getFullLog();
    }

    public void UpdateSettings() {
        setSaveStrategy();
        setDiscountStrategy();
    }
}
