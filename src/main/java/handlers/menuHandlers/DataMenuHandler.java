package handlers.menuHandlers;

import interfaces.IDataMenuHandler;
import interfaces.IMenuHandler;
import models.menu.Menu;

import java.lang.reflect.InvocationTargetException;
import usecases.DataUseCase;

public class DataMenuHandler implements IMenuHandler, IDataMenuHandler {

  DataUseCase DataUseCase = new DataUseCase();

  @Override
  public void handleMenu(Menu menu) {
    try {
      this.getClass().getDeclaredMethod(menu.getMethod(), Menu.class).invoke(this, menu);
    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void goBack(Menu menu) {
    MENU_NAVIGATION_HANDLER.goBack(menu);
  }

  @Override
  public void addPart(Menu menu) {
    DataUseCase.startAddPartFlow();
    goBack(menu);
  }

  @Override
  public void addManufacturer(Menu menu) {
    DataUseCase.startAddManufacturerFlow();
    goBack(menu);
  }

  @Override
  public void removeManufacturer(Menu menu) {
    DataUseCase.startRemoveManufacturerFlow();
    goBack(menu);
  }
}
