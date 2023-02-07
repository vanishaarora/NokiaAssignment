package handlers.menuHandlers;

import interfaces.ICompanyMenuHandler;
import interfaces.IMenuHandler;
import models.menu.Menu;

import java.lang.reflect.InvocationTargetException;
import usecases.CompanyUseCase;


public class CompanyMenuHandler implements IMenuHandler, ICompanyMenuHandler {

  CompanyUseCase companyUseCase = new CompanyUseCase();

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
  public void addMoney(Menu menu) {
    companyUseCase.startAddMoneyFlow();
    goBack(menu);
  }

  @Override
  public void buyParts(Menu menu) {
    companyUseCase.startBuyPartsFlow();
    goBack(menu);
  }

  @Override
  public void listParts(Menu menu) {
    companyUseCase.startListPartsFlow();
    goBack(menu);
  }
}
