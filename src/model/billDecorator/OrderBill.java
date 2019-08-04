package model.billDecorator;

import database.OrderDBLocal;
import model.Order;

import java.util.List;

public class OrderBill implements Bill {
    protected OrderDBLocal orders;
    public OrderBill(OrderDBLocal orders){
        this.orders=orders;
    }
    @Override
    public void printBill() {
        System.out.println("Omschrijving\t\tAantal\t\tPrijs");
        System.out.println("**************************************");
        for (Object o: orders.getAllOrders()) {
            System.out.println(((Order)o).getProductDescription() + "\t\t\t" + ((Order)o).getAmount() + "\t\t\t" + ((Order)o).getPrice());
        }
        System.out.println("**************************************\n");
        System.out.println("Betaald(inclusief korting):" + orders.calculateTotalPrice() + "€.");
    }

}
