package database.discountStrategy;

import model.Order;

import java.util.List;

public class GroupDiscountStrategy implements PriceCalculationStrategy {
    private String group;
    private double percentage;

    public GroupDiscountStrategy(String group, double percentage){
        setGroup(group);
        setPercentage(percentage);
    }
    @Override
    public double calculatePrice(List<Order> orders) {
        double total = 0;
        for (Order o : orders) {
            if (o.getAmount() > 0) {
                if (o.getArticleGroup().equals(group)){
                    total += (o.getPrice()*o.getAmount())-(o.getPrice()*o.getAmount()*(percentage/100));
                }
                else{
                    total += o.getPrice()*o.getAmount();
                }
            }

        }
        return total;
    }

    public void setGroup(String group){
        this.group = group;
    }
    public void setPercentage(double percentage){
        this.percentage=percentage;
    }
}
