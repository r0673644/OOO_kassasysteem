package controller;

import controller.requestHandler.RequestHandler;
import controller.requestHandler.RequestHandlerFactory;
import javafx.scene.layout.Pane;
import model.KassaService;

import javax.swing.plaf.PanelUI;

public abstract class Controller {

    private KassaService service;
    private RequestHandlerFactory factory;


    public Controller() {
        this.service = new KassaService();
        this.factory = new RequestHandlerFactory();
    }

    public void handleRequest(String request) {
        RequestHandler handler = factory.getHandler(request, getController());
        handler.handleRequest();
    }

    protected abstract Controller getController();

    public KassaService getService() {
        return service;
    }

    public void setService(KassaService service) {
        this.service = service;
    }

}
