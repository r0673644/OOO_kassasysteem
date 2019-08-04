package view;

import controller.Controller;
import controller.OrderController;
import controller.requestHandler.DeleteConfirm;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteFromOrderPane extends GridPane {
    private Controller controller;

    //private Label codeField;
    private Button deleteButton;
    private TextField codeField;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    public DeleteFromOrderPane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane();

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();

        root.getChildren().add(this);

        this.setPrefHeight(300);
        this.setPrefWidth(750);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        add(new Label("Code of product to delete: "), 0, 0, 1, 1);
        codeField = new TextField();
        add(codeField, 1, 0, 2, 1);

        deleteButton = new Button("Delete");
        setDeleteAction(new DeleteConfirmHandler());
        deleteButton.setText("Delete");
        add(deleteButton, 0, 11, 1, 1);
    }

    public void setDeleteAction(EventHandler<ActionEvent> deleteAction) {
        deleteButton.setOnAction(deleteAction);
    }

    public int getCodeFieldInt() {
        return Integer.parseInt(codeField.getText());
    }

    public void setController(OrderController orderController) {
        controller = orderController;
    }

    class DeleteConfirmHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("DeleteConfirm");
        }
    }

    public Stage getStage(){
        return stage;
    }

}
