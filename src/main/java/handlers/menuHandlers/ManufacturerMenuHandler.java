package handlers.menuHandlers;

import interfaces.IManufacturerMenuHandler;
import interfaces.IMenuHandler;
import models.menu.Menu;

import java.lang.reflect.InvocationTargetException;
import usecases.ManufacturerUseCase;

public class ManufacturerMenuHandler implements IMenuHandler, IManufacturerMenuHandler {

  ManufacturerUseCase ManufactureUseCase = new ManufacturerUseCase();

  @Override
  public void handleMenu(Menu menu) {
    try {
      this.getClass().getDeclaredMethod(menu.getMethod(), Menu.class).invoke(this, menu);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String addQuantity(Menu menu) {
    ManufactureUseCase.startAddQuantityFlow();
    goBack(menu);
    return "";
  }

  @Override
  public void listQuantity(Menu menu) {

    ManufactureUseCase.startListQuantityFlow();
    goBack(menu);
  }

  @Override
  public void goBack(Menu menu) {
    IMenuHandler.MENU_NAVIGATION_HANDLER.goBack(menu);
  }
}
