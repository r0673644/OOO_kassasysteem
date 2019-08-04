package view;

import controller.Controller;
import database.ArtikelDB;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.Observable;
import model.Observer;

public class OrderPane extends GridPane implements Observer {
    private Controller controller;
    private TextField codeField;
    private Pane pane;
    private Label label = new Label();
    private Button buttonConfirm,buttonEnd,buttonDelete,buttonOnHold,buttonResume;

    public OrderPane() {
        this.setPrefHeight(300);
        this.setPrefWidth(320);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        add(new Label("Product Code: "), 0, 0, 1, 1);
        codeField = new TextField();
        add(codeField, 1, 0, 2, 1);
        try {
            label.setText("Total Price: € " + controller.getService().getOrderDBLocal().calculateTotalPrice());
        }catch (NullPointerException e){
            label.setText("Total Price: € 0.00");
        }catch (IllegalArgumentException e){
            label.setText(" Niet bestaande code.");
        }
        add(label,1,1,1,1);

        buttonEnd = new Button("End order");
        buttonEnd.setText("End order");
        setEndAction(new EndOrderHandler());
        add(buttonEnd, 0, 11, 1, 1);

        buttonConfirm = new Button("Add Article");
        setConfirmAction(new ConfirmOrderHandler());
        buttonConfirm.isDefaultButton();
        buttonConfirm.setText("Add Article");
        setConfirmAction(new ConfirmOrderHandler());
        add(buttonConfirm, 1, 11, 2, 1);

        buttonDelete = new Button("Delete Article");
        buttonDelete.setText("Delete Article");
        setDeleteAction(new DeleteFromOrderHandler());
        add(buttonDelete, 2, 11, 3, 1);

        buttonOnHold = new Button("On Hold");
        buttonOnHold.setText("On Hold");
        setOnHoldAction(new TransActionOnHoldHandler());
        add(buttonOnHold, 0, 13, 1, 1);

        buttonResume = new Button("Resume");
        buttonResume.setText("Resume");
        setResumeAction(new ResumeTransactionHandler());
        add(buttonResume, 1, 13, 2, 1);

    }
    public void setController(Controller controller) {

        setEndAction(new EndOrderHandler());        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public void setConfirmAction(EventHandler<ActionEvent> saveAction) {
        buttonConfirm.setOnAction(saveAction);
    }
    public void setDeleteAction(EventHandler<ActionEvent> delete) {
        buttonDelete.setOnAction(delete);
    }
    public void setOnHoldAction(EventHandler<ActionEvent> onHold) {
        buttonOnHold.setOnAction(onHold);
    }
    public void setEndAction(EventHandler<ActionEvent> cancelAction) {
        buttonEnd.setOnAction(cancelAction);
    }
    public void setResumeAction(EventHandler<ActionEvent> resume) {
        buttonResume.setOnAction(resume);
    }

    public void clearInputFields() {
        codeField.setText("");
    }

    public int getCodeFieldInt() {
        return Integer.parseInt(codeField.getText());
    }

    @Override
    public void update(Observable o, Object args) {

    }

    public void createPriceLabel(){
        try {
            label.setText("Total Price: € " + controller.getService().getOrderDBLocal().calculateTotalPrice());
        } catch (NullPointerException e) {
            label.setText("Total Price: € 0");
        } catch (Exception e) {
            label.setText(" Niet bestaande code.");
        }
    }

    class ConfirmOrderHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ConfirmOrder");
            createPriceLabel();
        }
    }
    class EndOrderHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ShowEndOrderPane");
            createPriceLabel();
        }

    }
    class DeleteFromOrderHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("DeleteFromOrder");

        }
    }
    class  TransActionOnHoldHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("TransactionOnHold");
            createPriceLabel();
        }
    }
    class  ResumeTransactionHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("ResumeTransaction");
            createPriceLabel();
        }
    }
}
