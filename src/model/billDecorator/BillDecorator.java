package model.billDecorator;

import database.OrderDBLocal;

public abstract class BillDecorator implements Bill{
    protected OrderBill decoratedBill;
    protected OrderDBLocal orders;

    public BillDecorator(OrderBill decoratedBill,OrderDBLocal orders){
        super();
        this.decoratedBill=decoratedBill;
        this.orders=orders;
    }
}
