package database.loadStrategy;

import database.ArtikelDB;
import excel.ExcelPlugin;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ArticleExelLoadSaveStrategy extends ExelLoadSaveStrategy{
    private ExcelPlugin plugin = new ExcelPlugin();
    private File file;
    private List<Article> data;

    @Override
    public void setupConnectionToStorage() {
        file = new File("src/Files/artikel.xlsx");
    }

    @Override
    public void getDataFromStorage() {
        try {
            plugin.read(file);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataToStorage(){
        try {
            plugin.write(file,dataToStringArray());
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataInLocalMemory() {
        if (data != null) {
            Map map = new LinkedHashMap();
            for (Article a:data) {
                map.put(a.getProductCode(),a);
            }
            Map<Integer,Article> articleMap = map;
            ArtikelDB artikelDB = ArtikelDB.getInstance();
            artikelDB.setArticleMap(articleMap);
        }
    }

    public ArrayList<ArrayList<String>> dataToStringArray(){
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for (Article a:data) {
            ArrayList articleArray = new ArrayList();
            articleArray.add(a.getProductCode());
            articleArray.add(a.getProductDescription());
            articleArray.add(a.getArticleGroup());
            articleArray.add(a.getPrice());
            articleArray.add(a.getActualStorage());
            list.add(articleArray);
        }
        return list;
    }
}
