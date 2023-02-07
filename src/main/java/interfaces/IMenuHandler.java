package interfaces;

import models.menu.Menu;
import utils.MenuNavigationHandler;

public interface IMenuHandler {

    MenuNavigationHandler MENU_NAVIGATION_HANDLER = MenuNavigationHandler.instance;

    void handleMenu(Menu menu);

    void goBack(Menu menu);
}
