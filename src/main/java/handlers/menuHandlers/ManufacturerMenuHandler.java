package handlers.menuHandlers;

import entity.PartManufacturer;
import interfaces.IManufacturerMenuHandler;
import interfaces.IMenuHandler;
import java.lang.reflect.InvocationTargetException;
import models.menu.Menu;
import models.menu.ServiceType;
import services.ManufacturerService;
import utils.DataValidator;
import utils.InputValidator;
import utils.ServiceFactory;

public class ManufacturerMenuHandler implements IMenuHandler, IManufacturerMenuHandler {

  InputValidator operator = InputValidator.getInstance();
  DataValidator validator = DataValidator.getInstance();

  ManufacturerService service = (ManufacturerService) ServiceFactory.getInstance().getService(
      ServiceType.MANUFACTURER);


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
    String partName = getPart();
    String manufacturerName = getManufacturer();
    int quantity = getQuantity();
    float price = getPrice();

    service.addQuantity(partName, manufacturerName, quantity, price);
    goBack(menu);
    return "";
  }

  @Override
  public void listQuantity(Menu menu) {

    String partName = getPart();
    String manufacturerName = getManufacturer();

    var quantityList = service.listQuantity(partName, manufacturerName);

    if (quantityList.isEmpty()) {
      System.out.println(partName + " is not in stock");
    } else {
      for (PartManufacturer partManufacturer : quantityList) {
        System.out.println("Manufacturer: " + partManufacturer.getManufacturer().getName() +
            ", Price: " + partManufacturer.getPrice() +
            ", Quantity: " + partManufacturer.getQuantity());
      }
    }
    goBack(menu);
  }

  @Override
  public void goBack(Menu menu) {
    IMenuHandler.MENU_NAVIGATION_HANDLER.goBack(menu);
  }

  private int getQuantity() {
    int quantity = operator.getIntegerInput("Enter quantity of the parts:");
    while (!validator.isQuantityValid(quantity)) {
      System.out.println("Please enter a valid quantity!");
      quantity = getQuantity();
    }
    return quantity;
  }

  private float getPrice() {
    float price = operator.getFloatInput("Enter price for each part:");
    while (!validator.isPriceValid(price)) {
      System.out.println("Please enter a valid price!");
      price = getPrice();
    }
    return price;
  }

  private String getPart() {
    return operator.getStringInput("Enter the part name:");
  }

  private String getManufacturer() {
    return operator.getStringInput("Enter the manufacturer name:");
  }
}
