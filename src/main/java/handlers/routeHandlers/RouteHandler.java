package handlers.routeHandlers;

import models.menu.Menu;
import utils.MenuHandlerFactory;

public class RouteHandler {

    private RouteHandler() {
    }

    private final MenuHandlerFactory menuFactory = new MenuHandlerFactory();

    public static RouteHandler shared = new RouteHandler();

    public void handleSelectedMenu(Menu menu) {
        var factory = menuFactory.getFactory(menu);
        if (factory == null) {
            System.out.println("Could not get the required menu factory");
            System.exit(1);
        }
        factory.handleMenu(menu);
    }
}
