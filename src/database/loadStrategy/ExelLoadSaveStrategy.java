package database.loadStrategy;

import database.ArtikelDB;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Article;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ExelLoadSaveStrategy implements LoadSaveStrategy {

    // TEMPLATE METHOD
    @Override
    public void loadFromStorage() {
        setupConnectionToStorage();
        try {
            getDataFromStorage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
        setDataInLocalMemory();
    }


    // TEMPLATE METHOD
    @Override
    public void saveToStorage() {
        setupConnectionToStorage();
        try {
            setDataToStorage();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }



    public abstract void setupConnectionToStorage();

    public abstract void getDataFromStorage() throws IOException, BiffException;

    public abstract void setDataToStorage() throws WriteException, IOException, BiffException;

    public abstract void setDataInLocalMemory();

    @Override
    public void saveObjectToStorage(Object object) {

    }

    public List MapToList() {
        Map<Integer, Article> articleMap = ArtikelDB.getInstance().getArticleMap();
        List list = new ArrayList();
        for(Map.Entry<Integer,Article> entry : articleMap.entrySet()){
            Article value = entry.getValue();
            list.add(value);
        }
        return list;
    }
}
