package controller.requestHandler;

import controller.Controller;

public class RequestHandlerFactory {

    public RequestHandler getHandler(String request, Controller controller) { return createHandler(request, controller); }

    private RequestHandler createHandler(String request, Controller controller) {

        RequestHandler handler = null;
        try {
            Class<?> handlerClass = Class.forName("controller.requestHandler." + request);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setController(controller);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return handler;
    }
}
