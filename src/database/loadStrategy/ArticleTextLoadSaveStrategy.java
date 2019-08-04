package database.loadStrategy;

import database.ArtikelDB;
import model.Article;
import model.ArticleFactory;

import java.io.*;
import java.util.*;

public class ArticleTextLoadSaveStrategy extends TextLoadSaveStrategy {
    private File file;
    private List<Article> data;

    @Override
    public void setupConnectionToStorage() {
        file = new File("src/Files/artikel.txt");
    }

    @Override
    public void getDataFromStorage() {
        data = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {

            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();

                String[] instances = line.split(",");
                Article article = createArticle(instances);

                data.add(article);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Article createArticle(String[] instances) {
        if (instances!=null) {
            int productCode = Integer.parseInt(instances[0]);
            String productDescription = instances[1];
            String articleGroup = instances[2];
            double price = Double.parseDouble(instances[3]);
            int actualStorage = Integer.parseInt(instances[4]);

            ArticleFactory factory = new ArticleFactory();
            Article createdArticle = factory.createArticle(productCode, productDescription, articleGroup, price, actualStorage);

            return createdArticle;
        }
        return null;
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

    @Override
    public void setDataToStorage() {
        clearFile();
        Map<Integer,Article> articleMap = ArtikelDB.getInstance().getArticleMap();
        for(Map.Entry<Integer,Article> entry : articleMap.entrySet()){
            Article value = entry.getValue();
            saveObjectToStorage(value);
        }
    }

    @Override
    public void saveObjectToStorage(Object object) {
        setupConnectionToStorage();
        if (object instanceof Article) {
            Article article = (Article) object;
            saveArticleToStorage(article);
        }
    }

    private void saveArticleToStorage(Article article) {
        try (FileOutputStream fos = new FileOutputStream(file, true);
             PrintWriter writer = new PrintWriter(fos)) {

            int productCode = article.getProductCode();
            String productDescription = article.getProductDescription();
            String articleGroup = article.getArticleGroup();
            double price = article.getPrice();
            int actualStorage = article.getActualStorage();

            writer.println(productCode + "," + productDescription + "," + articleGroup
                    + "," + price + "," + actualStorage);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write("".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List MapToList() {
        Map<Integer,Article> articleMap = ArtikelDB.getInstance().getArticleMap();
    List list = new ArrayList();
        for(Map.Entry<Integer,Article> entry : articleMap.entrySet()){
        Article value = entry.getValue();
        list.add(value);
    }
        return list;
}
    public void updateData(){
        this.data = MapToList();
    }

}
