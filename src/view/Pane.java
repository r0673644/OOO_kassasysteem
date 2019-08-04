package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pane {

    private Group root;
    private Scene scene;
    private BorderPane borderPane;
    private Stage stage;

    public Pane() {
        root = new Group();
        stage = new Stage();
        scene = new Scene(root, 750, 400);
        borderPane = new BorderPane();

        root.getChildren().add(borderPane);
        stage.setScene(scene);
        stage.sizeToScene();
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setBorderPane(BorderPane borderPane) {

        this.borderPane = borderPane;
    }

    public void addGridPaneToBorderPane(GridPane pane) {
        setBorderPane(new BorderPane(pane));
        root.getChildren().add(borderPane);
    }


}
