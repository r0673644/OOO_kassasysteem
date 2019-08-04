package model.billDecorator;

public class MessageHeaderDecorator extends BillDecorator {
    String message;
    public MessageHeaderDecorator(OrderBill decoratedBill, String message) {
        super(decoratedBill,null);
        this.message=message;
    }

    @Override
    public void printBill() {
        System.out.println("**************************************");
        System.out.println(message);
        System.out.println("**************************************");
        decoratedBill.printBill();
    }
}
