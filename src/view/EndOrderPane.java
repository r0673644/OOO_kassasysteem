package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Observable;
import model.Observer;

import javax.swing.*;

public class EndOrderPane extends GridPane implements Observer {

    private Controller controller;

    private Label overviewField;
    private Button closeButton,paidButton,cancelButton;

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    public EndOrderPane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane();

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();

        root.getChildren().add(this);
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        overviewField = new Label();

        closeButton = new Button("Back");
        setCloseAction(new EndOrderPane.ClosePaneHandler());
        closeButton.setText("Back");
        add(closeButton, 2, 11, 1, 1);

        paidButton = new Button("Paid");
        setEndAction(new EndOrderPane.EndPaneHandler());
        paidButton.setText("Paid");
        add(paidButton, 1, 11, 1, 1);

        cancelButton = new Button("Cancel");
        setCancelAction(new EndOrderPane.CancelOrderHandler());
        cancelButton.setText("Cancel");
        add(cancelButton, 3, 11, 1, 1);

    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setOverviewField(String overview){
        overviewField=new Label(overview);
        add(overviewField, 0, 0, 1, 1);
    }

    public void setCloseAction(EventHandler<ActionEvent> closeAction) {
        closeButton.setOnAction(closeAction);
    }
    public void setEndAction(EventHandler<ActionEvent> paidAction) {
        paidButton.setOnAction(paidAction);
    }
    public void setCancelAction(EventHandler<ActionEvent> cancelAction) {
        cancelButton.setOnAction(cancelAction);
    }


    class ClosePaneHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("CloseOverview");
        }
    }

    class EndPaneHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("CustomerPaid");
        }
    }
    class CancelOrderHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("CancelOrder");
        }
    }

    @Override
    public void update(Observable o, Object args) {

    }
    public Stage getStage(){
        return stage;
    }

}
