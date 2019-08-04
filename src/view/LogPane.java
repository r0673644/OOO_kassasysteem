package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.Observable;
import model.Observer;

public class LogPane extends GridPane implements Observer {
    private Controller controller;
    private Label log;
    public LogPane(){

        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.add(new Label("log:\n"), 0, 0, 1, 1);

        log = new Label(createLog());
        this.add((log),0,1,1,1);
    }

    private String createLog() {
        try {
            return controller.getService().getFullLog();
        } catch (Exception e) {
            return " ";
        }
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object args) {
        log.setText(createLog());
}
}
