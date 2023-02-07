package handlers.menuHandlers;

import entity.CompanyPart;
import interfaces.ICompanyMenuHandler;
import interfaces.IMenuHandler;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import models.menu.Menu;
import models.menu.ServiceType;
import services.CompanyService;
import utils.DataValidator;
import utils.InputValidator;
import utils.ServiceFactory;


public class CompanyMenuHandler implements IMenuHandler, ICompanyMenuHandler {

  InputValidator operator = InputValidator.getInstance();
  DataValidator dataValidator = DataValidator.getInstance();
  CompanyService companyService = (CompanyService) ServiceFactory.getInstance().getService(
      ServiceType.COMPANY);


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
    float money = operator.getFloatInput("Enter the amount to be added:");
    companyService.addMoney(money);
    goBack(menu);
  }

  @Override
  public void buyParts(Menu menu) {
    String partName = getPart();
    int quantity = getQuantity();
    companyService.buyParts(partName, quantity);
    goBack(menu);
  }

  @Override
  public void listParts(Menu menu) {
    List<CompanyPart> companyParts = companyService.listParts();
    for (CompanyPart companyPart : companyParts) {
      System.out.println("Part: " + companyPart.getPart().getName() + ", manufacturer: "
          + companyPart.getManufacturer().getName() + ", quantity: " + companyPart.getQuantity());
    }
    goBack(menu);
  }

  private String getPart() {
    return operator.getStringInput("Enter the part name:");
  }

  private int getQuantity() {
    int quantity = operator.getIntegerInput("Enter quantity of the parts:");
    while (!dataValidator.isQuantityValid(quantity)) {
      System.out.println("Please enter a valid quantity!");
      quantity = getQuantity();
    }
    return quantity;
  }
}
