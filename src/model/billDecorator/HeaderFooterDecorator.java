package model.billDecorator;

public class HeaderFooterDecorator extends BillDecorator {
    public HeaderFooterDecorator(OrderBill decoratedBill) {
        super(decoratedBill,null);
    }

    @Override
    public void printBill() {
        System.out.println("______________________________________\n");
        decoratedBill.printBill();
        System.out.println("______________________________________\n");
}
}
