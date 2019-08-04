package model.billDecorator;

import database.OrderDBLocal;

public class NoTaxDecorator extends BillDecorator {
    public NoTaxDecorator(OrderBill decoratedBill, OrderDBLocal orders) {
        super(decoratedBill, orders);
    }

    @Override
    public void printBill() {
        decoratedBill.printBill();
        System.out.println("Prize without taxes: €" + orders.calculateTotalPriceWithoutTaxes());
        System.out.println("Total prize of taxes: €" + orders.calculateTaxes());
    }
}
