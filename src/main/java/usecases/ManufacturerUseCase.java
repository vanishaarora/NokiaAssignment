package usecases;

import entity.PartManufacturer;
import models.menu.ServiceType;
import services.ManufacturerService;
import utils.DataValidator;
import utils.IOOperator;
import utils.ServiceFactory;

public class ManufacturerUseCase {

    IOOperator operator = IOOperator.getInstance();
    DataValidator validator = DataValidator.getInstance();

    ManufacturerService service = (ManufacturerService) ServiceFactory.getInstance().getService(ServiceType.MANUFACTURER);

    public void startAddQuantityFlow() {
        String partName = getPart();
        String manufacturerName = getManufacturer();
        int quantity = getQuantity();
        float price = getPrice();

        service.addQuantity(partName, manufacturerName, quantity, price);
    }

    public void startListQuantityFlow() {

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
        float price = operator.getFloatInput("Enter price for each part:");;
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
