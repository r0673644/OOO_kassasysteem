package view;

import controller.Controller;
import database.ArtikelDB;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Article;
import model.Observable;
import model.Observer;

import java.util.Map;

public class ArticleOverviewPane extends GridPane implements Observer {

    private Controller articleController;

    private TableView table;
    private Map<Integer,Article> articleMap;

    public ArticleOverviewPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Articles:"), 0, 0, 1, 1);

        table = new TableView<>();
        table.setPrefWidth(REMAINING);

        TableColumn productCodeCol = new TableColumn<>("Code");
        productCodeCol.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        table.getColumns().add(productCodeCol);

        TableColumn descriptionCol = new TableColumn<>("Description");
        table.getSortOrder().add(descriptionCol);
        descriptionCol.setSortType(TableColumn.SortType.DESCENDING);
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("productDescription"));
        table.getColumns().add(descriptionCol);

        TableColumn articleGroupCol = new TableColumn<>("Article Group");
        articleGroupCol.setCellValueFactory(new PropertyValueFactory<>("articleGroup"));
        table.getColumns().add(articleGroupCol);

        TableColumn priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getColumns().add(priceCol);

        TableColumn actualStorageCol = new TableColumn<>("Actual Storage");
        actualStorageCol.setCellValueFactory(new PropertyValueFactory<>("actualStorage"));
        table.getColumns().add(actualStorageCol);



        this.add(table, 0, 1, 5, 6);
    }


    public void setController(Controller controller) {
        this.articleController = controller;
    }

    private void updateArticleOverviewTable(Observable o) {
        ArtikelDB artikelDB = (ArtikelDB) o;
        ObservableList<Article> ObservableListArticles = artikelDB.getObservableListOfArticles();
        table.setItems(ObservableListArticles);
        table.refresh();
    }

    public Article getSelectedArticle() {
        return (Article) table.getSelectionModel().getSelectedItem();
    }

    @Override
    public void update(Observable o, Object args) {
        if (o instanceof ArtikelDB) {
            updateArticleOverviewTable(o);
        }
    }

}
