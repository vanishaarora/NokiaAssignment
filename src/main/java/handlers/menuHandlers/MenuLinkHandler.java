package handlers.menuHandlers;

import interfaces.IMenuHandler;
import models.menu.Menu;

public class MenuLinkHandler implements IMenuHandler {

  @Override
  public void handleMenu(Menu menu) {
    MENU_NAVIGATION_HANDLER.showMenus(menu);
  }

  @Override
  public void goBack(Menu menu) {
    MENU_NAVIGATION_HANDLER.goBack(menu);
  }
}
