package controller;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Discount;
import view.OptionsPane;

public class OptionsController extends Controller {
        private  OptionsPane optionsPane;

        private Group root;
        private Scene scene;
        private BorderPane borderPane;
        private Stage stage;

        private String saveMethod = "Text";
        private Discount discountMethod = Discount.NO_DISCOUNT;
        private String billDecorator = "None";

    public OptionsController(){
        optionsPane = new OptionsPane();
            setOptionsPane();
        }

        @Override
        protected Controller getController() {
            return this;
        }

        @Override
        public void handleRequest(String action) {
            if(action.equals("confirmOptions")){
                saveMethod = optionsPane.getSelectedSaveMethod();
                discountMethod = optionsPane.getSelectedDiscountMethod();
                billDecorator = optionsPane.getSelectedBillDecorator();
                getService().getOptionsDB().setSaveMethod(saveMethod);
                getService().getOptionsDB().setDiscountMethod(discountMethod);
                getService().getOptionsDB().setBillDecorator(billDecorator);
                getService().getOptionsDB().setPercentage(optionsPane.getPercentage());
                getService().getOptionsDB().setThreshold(optionsPane.getThreshold());
                getService().getOptionsDB().setGroup(optionsPane.getGroup());
                getService().getOptionsDB().setBillMessage(optionsPane.getBillMessage());
                getService().UpdateSettings();
                stage.close();
            }
            else{stage.close();}
        }

        private void setOptionsPane(){
            stage = new Stage();
            root = new Group();
            scene = new Scene(root, 750, 400);
            borderPane = new BorderPane(optionsPane);


            root.getChildren().add(borderPane);
            stage.setScene(scene);
            stage.sizeToScene();
        }

        public void setOptionsPane(OptionsPane optionsPane){this.optionsPane = optionsPane;}
}
