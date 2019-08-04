package model.billDecorator;

import database.OrderDBLocal;

public class NoDiscountOnlyDiscountDecorator extends BillDecorator {
    public NoDiscountOnlyDiscountDecorator(OrderBill decoratedBill,OrderDBLocal orders) {
        super(decoratedBill,orders);
    }

    @Override
    public void printBill() {
        decoratedBill.printBill();
        System.out.println("Total prize(No discount): €" + orders.calculateTotalPrizeNoDiscount());
        System.out.println("Total discount: €" + (orders.calculateTotalPrizeNoDiscount()-orders.calculateTotalPrice()));
    }
}
