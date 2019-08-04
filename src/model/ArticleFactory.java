package model;

public class ArticleFactory {
    public Article createArticle(int productCode,String productDescription,String articleGroup,double price,int actualStorage){
        Article newArticle= null;
        newArticle=new Article(productCode,productDescription,articleGroup,price,actualStorage);
        return newArticle;
    }
}
