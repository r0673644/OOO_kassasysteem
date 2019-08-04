package database;

import database.loadStrategy.ArticleTextLoadSaveStrategy;
import database.loadStrategy.LoadSaveStrategy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Article;
import model.Observable;
import model.Observer;
import model.Order;

import java.util.*;


public class ArtikelDB implements Observable {

    private static ArtikelDB uniqueInstance;
    private HashMap articleMap;
    private LoadSaveStrategy dbStrategy;

    private List<Observer> observerList;

    private ArtikelDB() {
        setArticleMap(new LinkedHashMap<>());
        setDbStrategy(new ArticleTextLoadSaveStrategy());
        observerList = new ArrayList<>();

    }

    public void setArticleMap(Map<Integer, Article> objects) {
        this.articleMap = (HashMap) objects;
    }

    private void setDbStrategy(LoadSaveStrategy strategy){
        this.dbStrategy = strategy;
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

    public static synchronized ArtikelDB getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ArtikelDB();
        }
        return uniqueInstance;
    }

    public Map<Integer, Article> getArticleMap() {
        return articleMap;
    }

    public ObservableList<Article> getObservableListOfArticles() {
        List list = dbStrategy.MapToList();
            return FXCollections.observableArrayList(list);
    }
    public void loadDataInLocalMemory() {
        dbStrategy.loadFromStorage();
    }


    public Article getArticleFromCode(int articleCode) {
        return (Article)articleMap.get(articleCode);
    }

    public void saveOrder(List<Order> allOrders) {
        for (Order o:allOrders) {
            updateStorage(o.getProductCode(),o.getAmount());
        }
        save();
    }

    public void updateStorage(int productCode, int amount) {
        ((Article)articleMap.get(productCode)).updateStorage(amount);
    }

    public void save(){
        dbStrategy.saveToStorage();
    }
}
