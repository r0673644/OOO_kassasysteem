package view;

import controller.Controller;
import controller.OptionsController;
import database.discountStrategy.GroupDiscountStrategy;
import database.discountStrategy.MostExpensiveStrategy;
import database.discountStrategy.NoDiscountStrategy;
import database.discountStrategy.ThresholdStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.Discount;
import model.Observable;
import model.Observer;

public class OptionsPane extends GridPane implements Observer {
    private Controller controller;

    private Label saveOptionField,discountOptionField,billOptionField;
    private Button saveButton;
    private TextField percentageField,thresholdField,groupField,billMessageField;
    private ToggleGroup saveOptionsGroup,discountOptionsGroup,billOptionsGroup;
    private VBox vbox = new VBox();
    private VBox vboxDisc = new VBox();
    private VBox vboxBill = new VBox();

    public OptionsPane() {
        this.setPrefHeight(300);
        this.setPrefWidth(750);

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        saveOptionField = new Label("How would you like to save your articles?");
        add(saveOptionField,0,0,1,1);
        saveOptionsGroup = new ToggleGroup();

        ToggleButton textButton = new RadioButton("Text File");
        textButton.setToggleGroup(saveOptionsGroup);

        ToggleButton excelButton = new RadioButton("Excel File");
        excelButton.setToggleGroup(saveOptionsGroup);

        vbox.getChildren().add(textButton);
        vbox.getChildren().add(excelButton);

        add(vbox,0,5,1,1);

        discountOptionField = new Label("How would you like to calculate prizes?");
        add(discountOptionField,5,0,1,1);

        discountOptionsGroup = new ToggleGroup();

        ToggleButton noDiscButton = new RadioButton(Discount.NO_DISCOUNT.toString());
        noDiscButton.setToggleGroup(discountOptionsGroup);

        ToggleButton groupDiscButton = new RadioButton(Discount.GROUP.toString());
        groupDiscButton.setToggleGroup(discountOptionsGroup);

        ToggleButton mostExpDiscButton = new RadioButton(Discount.MOST_EXPENSIVE.toString());
        mostExpDiscButton.setToggleGroup(discountOptionsGroup);

        ToggleButton thresholdButton = new RadioButton(Discount.THRESHOLD.toString());
        thresholdButton.setToggleGroup(discountOptionsGroup);

        vboxDisc.getChildren().add(noDiscButton);
        vboxDisc.getChildren().add(groupDiscButton);
        vboxDisc.getChildren().add(mostExpDiscButton);
        vboxDisc.getChildren().add(thresholdButton);

        add(vboxDisc,5,5,1,1);

        add(new Label("Percentage: "), 5, 7, 1, 1);
        percentageField = new TextField();
        add(percentageField, 5, 8, 2, 1);

        add(new Label("Threshold: "), 5, 9, 1, 1);
        thresholdField = new TextField();
        add(thresholdField, 5, 10, 2, 1);

        add(new Label("Group: "), 5, 11, 1, 1);
        groupField = new TextField();
        add(groupField, 5, 12, 2, 1);

        billOptionField = new Label("How would you like to customize the bills?");
        add(billOptionField,10,0,1,1);

        billOptionsGroup = new ToggleGroup();

        ToggleButton noCustomizationButton = new RadioButton("No Customization");
        noCustomizationButton.setToggleGroup(billOptionsGroup);

        ToggleButton dateTimeButton = new RadioButton("Show Date And Time");
        dateTimeButton.setToggleGroup(billOptionsGroup);

        ToggleButton headerFooterButton = new RadioButton("Line As Header And Footer");
        headerFooterButton.setToggleGroup(billOptionsGroup);

        ToggleButton messageFooterButton = new RadioButton("Message In Footer");
        messageFooterButton.setToggleGroup(billOptionsGroup);

        ToggleButton messageHeaderButton = new RadioButton("Message In Header");
        messageHeaderButton.setToggleGroup(billOptionsGroup);

        ToggleButton discountButton = new RadioButton("Show Discount");
        discountButton.setToggleGroup(billOptionsGroup);

        ToggleButton taxButton = new RadioButton("Show Taxes");
        taxButton.setToggleGroup(billOptionsGroup);

        vboxBill.getChildren().add(noCustomizationButton);
        vboxBill.getChildren().add(dateTimeButton);
        vboxBill.getChildren().add(headerFooterButton);
        vboxBill.getChildren().add(messageFooterButton);
        vboxBill.getChildren().add(messageHeaderButton);
        vboxBill.getChildren().add(discountButton);
        vboxBill.getChildren().add(taxButton);

        add(vboxBill,10,5,1,1);

        add(new Label("Message: "), 10, 7, 1, 1);
        billMessageField = new TextField();
        add(billMessageField, 10, 8, 2, 1);
        saveButton = new Button("Save");
        setAction(new OptionsPane.ConfirmOptionsHandler());
        saveButton.setText("Save");
        add(saveButton, 0, 11, 1, 1);
    }

    public void setAction(EventHandler<ActionEvent> confirmOptionAction) {
        saveButton.setOnAction(confirmOptionAction);
    }

    private void displayAlert() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Preference saved!");
        infoAlert.setHeaderText("You have set your save preference to " + getSelectedSaveMethod() + ".\nYou have set your discount preference to " + getSelectedDiscountMethod()+ ".\nYou have set your bill header/footer preference to " + getSelectedBillDecorator() + ".");
        infoAlert.setContentText("Saved succesfully!");
        infoAlert.showAndWait();
    }

    public String getSelectedSaveMethod(){
        try {
            return((RadioButton)saveOptionsGroup.getSelectedToggle()).getText();
        } catch (Exception e) {
            return "Text";
        }
    } public Discount getSelectedDiscountMethod(){
        try {
            return(stringToDiscount(((RadioButton)discountOptionsGroup.getSelectedToggle()).getText()));
        } catch (Exception e) {
            return Discount.NO_DISCOUNT;
        }
    }

public Discount stringToDiscount(String s){
    switch (s){
        case "MOST_EXPENSIVE": return Discount.MOST_EXPENSIVE;
        case "GROUP": return Discount.GROUP;
        case "THRESHOLD": return Discount.THRESHOLD;
        default: return Discount.NO_DISCOUNT;
    }
}

    public void setController(OptionsController optionsController) {
        this.controller=optionsController;
    }

    public double getPercentage() {
        try {
            return Double.parseDouble(percentageField.getCharacters().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public int getThreshold() {
        try {
            return Integer.parseInt(thresholdField.getCharacters().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    public String getGroup() {
        try {
            return groupField.getCharacters().toString();
        } catch (Exception e) {
            return "no group";
        }
    }

    public String getSelectedBillDecorator() {
        try {
            return((RadioButton)billOptionsGroup.getSelectedToggle()).getText();
        } catch (Exception e) {
            return "none";
        }
    }

    public String getBillMessage() {
        try {
            return billMessageField.getCharacters().toString();
        } catch (Exception e) {
            return "no message";
        }
    }

    class ConfirmOptionsHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            controller.handleRequest("confirmOptions");
            displayAlert();
        }
    }

    @Override
    public void update(Observable o, Object args) {


    }
}
