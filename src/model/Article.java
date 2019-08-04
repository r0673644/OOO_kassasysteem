package model;

public class Article {

    private int productCode;
    private String productDescription;
    private String articleGroup;
    private double price;
    private int actualStorage;
    private double priceWithTaxes;


    //public Article(){}

    public Article(int productCode, String productDescription, String articleGroup, double price, int actualStorage){
        setProductCode(productCode);
        setProductDescription(productDescription);
        setArticleGroup(articleGroup);
        setPrice(price);
        setPriceWithTaxes(price);
        setActualStorage(actualStorage);
    }

    public void setProductDescription(String productDescription){
        this.productDescription = productDescription;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceWithTaxes(double priceWithoutTaxes){
        this.priceWithTaxes = priceWithoutTaxes + (priceWithoutTaxes * 6 / 100);
    }

    public double getPriceWithTaxes(){
        return priceWithTaxes;
    }

    public void setActualStorage(int actualStorage) {
        this.actualStorage = actualStorage;
    }

    public int getActualStorage(){
        return actualStorage;
    }

    public void setArticleGroup(String articleGroup){
        this.articleGroup = articleGroup;
    }

    public String getArticleGroup(){
        return articleGroup;
    }

    public String getPriceStr() {
        return String.valueOf(price);
    }

    public void updateStorage(int amount) {
        actualStorage = actualStorage-amount;
    }
}
