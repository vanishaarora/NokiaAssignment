package handlers.menuHandlers;

import interfaces.IDataMenuHandler;
import interfaces.IMenuHandler;
import java.lang.reflect.InvocationTargetException;
import models.menu.Menu;
import models.menu.ServiceType;
import services.ManufacturerService;
import services.PartService;
import utils.InputValidator;
import utils.ServiceFactory;

public class DataMenuHandler implements IMenuHandler, IDataMenuHandler {

  InputValidator operator = InputValidator.getInstance();
  PartService partService = (PartService) ServiceFactory.getInstance().getService(ServiceType.PART);
  ManufacturerService manufacturerService = (ManufacturerService) ServiceFactory.getInstance()
      .getService(ServiceType.MANUFACTURER);


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
    String partName = getPart();

    if (partName == "") {
      partName = getPart();
    }

    partService.addNewPart(partName);
    goBack(menu);
  }

  @Override
  public void addManufacturer(Menu menu) {
    String manufacturerName = getManufacturer();

    if (manufacturerName == "") {
      manufacturerName = getManufacturer();
    }

    manufacturerService.addNewManufacturer(manufacturerName);
    goBack(menu);
  }

  @Override
  public void removeManufacturer(Menu menu) {
    String manufacturerName = getManufacturer();

    if (manufacturerName == "") {
      manufacturerName = getManufacturer();
    }

    manufacturerService.removeManufacturer(manufacturerName);
    goBack(menu);
  }

  private String getPart() {
    return operator.getStringInput("Enter the part name:");
  }

  private String getManufacturer() {
    return operator.getStringInput("Enter the manufacturer name:");
  }
}
