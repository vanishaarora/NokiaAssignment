package handlers.menuHandlers;

import interfaces.IInitialMenuHandler;
import interfaces.IMenuHandler;
import java.lang.reflect.InvocationTargetException;
import models.menu.Menu;
import models.menu.MenuType;

public class InitialMenuHandler implements IMenuHandler, IInitialMenuHandler {

  MenuLinkHandler MenuLinkHandler = new MenuLinkHandler();

  @Override
  public void handleMenu(Menu menu) {
    try {
      if (menu.getType() == MenuType.PARENT) {
        MenuLinkHandler.handleMenu(menu);
      } else {
        getClass().getDeclaredMethod(menu.getMethod()).invoke(this);
      }
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void goBack(Menu menu) {
    IMenuHandler.MENU_NAVIGATION_HANDLER.goBack(menu);
  }

  @Override
  public void exit() {
    System.exit(0);
  }
}
