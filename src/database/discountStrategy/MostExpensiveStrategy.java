package database.discountStrategy;

import model.Order;

import java.util.List;

public class MostExpensiveStrategy implements PriceCalculationStrategy{
    private double percentage;

    public MostExpensiveStrategy(double percentage){
        setPercentage(percentage);
    }
    @Override
    public double calculatePrice(List<Order> orders) {
        double total = 0;
        Order mostExp = orders.get(0);
        for (Order o : orders) {
            if(o.getPrice()>mostExp.getPrice()){
                mostExp=o;
            }
        }
        for (Order o : orders) {
            if (o.getAmount() > 0) {
                if (o.getProductCode() != mostExp.getProductCode()) {
                    total += o.getPrice() * o.getAmount();
                } else {
                    total += o.getPrice() * o.getAmount() - ((o.getPrice() * o.getAmount() )*(percentage/100));
                }
            }
        }
        return total;
    }
    public void setPercentage(double percentage){
        this.percentage=percentage;
    }
}
