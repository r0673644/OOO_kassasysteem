package view;


import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(Pane articleOverviewPane, Pane optionsPane, Pane OrderPane, Pane logPane){
	    TabPane tabPane = new TabPane(); 	    
        Tab kassaTab = new Tab("Kassa",OrderPane);
        Tab artikelTab = new Tab("Artikelen",articleOverviewPane);
        Tab instellingTab = new Tab("Instellingen",optionsPane);
        Tab logTab = new Tab("Log",logPane);
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}
}
