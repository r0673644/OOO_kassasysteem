package application;
	
import controller.ArticleController;
import controller.OptionsController;
import controller.OrderController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.KassaService;
import view.*;

public class Main extends Application {

	private ArticleOverviewPane articleOverviewPane;
	private OptionsPane optionsPane;
	private OrderPane orderPane;
	private OrderOverviewPane orderOverviewPane;
	private EndOrderPane endOrderPane;
	private LogPane logPane;

	private Group root;
	private Scene scene;
	private BorderPane borderPane;
	private DeleteFromOrderPane deleteFromOrderPane;

	private KassaView kassaView;
	private KlantView klantView;

	private ArticleController articleController;
	private OptionsController optionsController;
	private OrderController orderController;

	private KassaService kassaService;

	@Override
	public void start(Stage primaryStage) {
		try {
			initializeModelObjects();
			initializeControllerObjects();
			initializeViewPaneObjects();

			setModelViewControllerReferences();
			registerObserversInMVC();

			loadDataFromStorageIntoLocalMemory();
			firstNotificationOfObservers();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadDataFromStorageIntoLocalMemory() {
		kassaService.loadDataFromStorageIntoLocalMemory();
	}

	private void registerObserversInMVC() {
		registerObserversInView();
	}

	private void registerObserversInView() {
		kassaService.getArtikelDB().registerObserver(articleOverviewPane);
		kassaService.getOrderDBLocal().registerObserver(orderOverviewPane);
		kassaService.getLogDB().registerObserver(logPane);
	}

	private void setModelViewControllerReferences() {
		setMVCReferencesForViewObject();
		setMVCReferencesForControllerObjects();
		setMVCReferencesForModelObjects();
	}

	private void initializeControllerObjects() {
		articleController = new ArticleController();
		optionsController = new OptionsController();
		orderController = new OrderController();
	}

	private void initializeModelObjects() {
		kassaService = new KassaService();
		kassaService.loadDataFromStorageIntoLocalMemory();
	}

	private void initializeViewPaneObjects() {
		articleOverviewPane = new ArticleOverviewPane();
		optionsPane = new OptionsPane();
		orderPane = new OrderPane();
		orderOverviewPane = new OrderOverviewPane();
		deleteFromOrderPane = new DeleteFromOrderPane();
		endOrderPane = new EndOrderPane();
		logPane = new LogPane();


		kassaView = new KassaView(articleOverviewPane,optionsPane,orderPane,logPane);
		klantView = new KlantView(orderOverviewPane);



	}
	private void setMVCReferencesForViewObject(){
		articleOverviewPane.setController(articleController);
		optionsPane.setController(optionsController);
		deleteFromOrderPane.setController(orderController);
		orderPane.setController(orderController);
		orderOverviewPane.setController(orderController);
		endOrderPane.setController(orderController);
		logPane.setController(orderController);
	}
	private void setMVCReferencesForControllerObjects(){
		articleController.setService(kassaService);
		articleController.setArticleOverviewPane(articleOverviewPane);

		optionsController.setService(kassaService);
		optionsController.setOptionsPane(optionsPane);

		orderController.setService(kassaService);
		orderController.setOrderPane(orderPane);
		orderController.setEndOrderPane(endOrderPane);
}
	private void setMVCReferencesForModelObjects(){
		kassaService.setArticleController(articleController);
		kassaService.setOptionsController(optionsController);
		kassaService.setOrderController(orderController);
	}
	private void firstNotificationOfObservers() {
		kassaService.notifyObservers(null);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
