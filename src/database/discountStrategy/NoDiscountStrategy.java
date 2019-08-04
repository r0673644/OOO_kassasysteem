package database.discountStrategy;

import model.Order;

import java.util.List;

public class NoDiscountStrategy implements PriceCalculationStrategy{
    @Override
    public double calculatePrice(List<Order> orders) {
        double total = 0;
        for (Order o : orders) {
            if(o.getAmount()>0) {
                total += o.getPrice()*o.getAmount();
            }
        }
        return total;
    }
}
