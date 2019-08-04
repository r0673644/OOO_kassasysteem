package model.billDecorator;

import database.OrderDBLocal;

public class MessageFooterDecorator extends BillDecorator {
    private String message;
    public MessageFooterDecorator(OrderBill decoratedBill, String message) {
        super(decoratedBill, null);
        this.message=message;
    }

    @Override
    public void printBill() {
        decoratedBill.printBill();
        System.out.println("**************************************");
        System.out.println(message);
        System.out.println("**************************************");
    }
}
