package database;

import model.Discount;
import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;

// SINGLETON
public class OptionsDB implements Observable {

    private static database.OptionsDB uniqueInstance;
    private String saveMethod;
    private Discount discountMethod;
    private String billDecorator;
    private int threshold = 0;
    private double percentage = 0;
    private String group;
    private String billMessage;

    private List<Observer> observerList;

    private OptionsDB(){
        saveMethod = "Text";
        discountMethod = Discount.NO_DISCOUNT;
        billDecorator = "none";

        observerList = new ArrayList<>();
    }


    public static synchronized OptionsDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new OptionsDB();
        }
        return uniqueInstance;
    }

    public void setSaveMethod(String method){
        this.saveMethod=method;
    }

    public String getSaveMethod(){
        return saveMethod;
    }

    public void setDiscountMethod(Discount method){
        this.discountMethod=method;
    }

    public Discount getDiscountMethod(){
        return discountMethod;
    }

    public String getBillDecorator() {
        return billDecorator;
    }

    public void setBillDecorator(String billDecorator) {
        this.billDecorator = billDecorator;
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

    public void setPercentage(double percentage) {
        this.percentage=percentage;
    }
    public void setThreshold(int threshold){
        this.threshold = threshold;
    }

    public double getPercentage() {
        return percentage;
    }
    public int getThreshold(){
        return threshold;
    }
    public void setGroup(String group){
        this.group = group;
    }
    public String getGroup() {
        return group;
    }
    public void setBillMessage(String message){this.billMessage=message;}
    public String getBillMessage() {
        return billMessage;
    }
}
