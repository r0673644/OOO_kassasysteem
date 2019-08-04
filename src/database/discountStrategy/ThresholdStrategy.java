package database.discountStrategy;

import model.Order;

import java.util.List;

public class ThresholdStrategy implements PriceCalculationStrategy {
    private int threshold;
    private double percentage;

    public ThresholdStrategy(int threshold,double percentage){
        setThreshold(threshold);
        setPercentage(percentage);
    }
    @Override
    public double calculatePrice(List<Order> orders) {
        double total = 0;
        for (Order o : orders) {
            if(o.getAmount()>0) {
                total += o.getPrice()*o.getAmount();
            }
        }
        if(total>threshold){
            total=total-total*(percentage/100);
        }
        return total;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
    public void setThreshold(int threshold){
        this.threshold = threshold;
    }
}
