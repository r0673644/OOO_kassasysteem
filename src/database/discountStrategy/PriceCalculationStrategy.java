package database.discountStrategy;

import model.Order;

import java.util.List;

public interface PriceCalculationStrategy {
    public double calculatePrice(List<Order> orders);
}
