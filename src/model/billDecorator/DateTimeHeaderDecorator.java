package model.billDecorator;

public class DateTimeHeaderDecorator extends BillDecorator {
    private String date;
    private String time;

    public DateTimeHeaderDecorator(OrderBill decoratedBill,String date, String time) {
        super(decoratedBill,null);
        this.date=date;
        this.time=time;
    }

    @Override
    public void printBill() {
        System.out.println("**************************************");
        System.out.println(date + " " + time);
        System.out.println("**************************************");
        decoratedBill.printBill();
    }
}
