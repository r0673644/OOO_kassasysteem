package view;

import controller.Controller;
import database.OrderDBLocal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Observable;
import model.Observer;
import model.Order;

public class OrderOverviewPane extends GridPane implements Observer {

    private Controller orderController;

    private TableView table;

    public OrderOverviewPane() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("Orders in basket:"), 0, 0, 1, 1);

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

        TableColumn amountCol = new TableColumn<>("Amount In Basket");
        amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        table.getColumns().add(amountCol);

        this.add(table, 0, 1, 5, 6);
    }


    public void setController(Controller controller) {
        this.orderController = controller;
    }

    private void updateOrderOverviewTable(Observable o) {
        OrderDBLocal orderDBLocal = (OrderDBLocal) o;
        ObservableList<Order> observableListOrders = FXCollections.observableArrayList(orderDBLocal.getAllOrders());
        table.setItems(observableListOrders);
        table.refresh();
    }

    @Override
    public void update(Observable o, Object args) {
        if (o instanceof OrderDBLocal) {
            updateOrderOverviewTable(o);
        }
    }
}
