package database;

import model.Log;
import model.Observable;
import model.Observer;

import java.util.ArrayList;
import java.util.List;

public class LogDB implements Observable {
    private static LogDB uniqueInstance;
    private List<Log> logs;
    private int[] test = new int[]{1,2};

    private List<Observer> observerList;

    public LogDB(){
        logs=new ArrayList<Log>();
        logs.add(new Log("1-8-2019",new int[]{13,00},0,0,0));
        observerList = new ArrayList<>();
    }
    public void registerNewSale(OrderDBLocal orders){
        Log log = new Log("2-8-2019",createTijdstip(),orders.calculateTotalPrizeNoDiscount(),orders.calculateTotalPrizeNoDiscount()-orders.calculateTotalPrice(),orders.calculateTotalPrice());
        logs.add(log);
        notifyObservers(null);
    }

    public int[] createTijdstip() {
        int[] vorige = logs.get(logs.size() - 1).getTijdstip();
        int[] nieuw = new int[2];
        if (vorige[1] + 5 < 60) {
            nieuw[0] = vorige[0];
            nieuw[1] = vorige[1] + 5;
        }
        else{
            nieuw[0]=vorige[0]+1;
            nieuw[1]=0;
        }
        return nieuw;
    }
    public static synchronized LogDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LogDB();
        }
        return uniqueInstance;
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers(Object args) {
        for (Observer o : observerList) {
            o.update(this, args);
        }
    }

    public String getFullLog() {
        String log = "";
        for (Log l:logs) {
    log+=l.getDatum() + " | " + l.getTijdstipString() + " | " + l.getTotaalBedrag() + " | " + l.getKorting() + " | " + l.getTeBetalen() + "\n";
        }
        return log;
    }

    public String getLastDate() {
        return logs.get(logs.size()-1).getDatum();
    }

    public String getLastTimestamp() {
        return logs.get(logs.size()-1).getTijdstipString();
    }
}
