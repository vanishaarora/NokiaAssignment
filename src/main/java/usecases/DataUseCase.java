package usecases;

import models.menu.ServiceType;
import services.ManufacturerService;
import services.PartService;
import utils.IOOperator;
import utils.ServiceFactory;

public class DataUseCase {

    IOOperator operator = IOOperator.getInstance();
    PartService partService = (PartService) ServiceFactory.getInstance().getService(ServiceType.PART);
    ManufacturerService manufacturerService = (ManufacturerService) ServiceFactory.getInstance().getService(ServiceType.MANUFACTURER);

    public void startRemoveManufacturerFlow() {
        String manufacturerName = getManufacturer();

        if (manufacturerName == "") {
            manufacturerName = getManufacturer();
        }

        manufacturerService.removeManufacturer(manufacturerName);
    }

    public void startAddPartFlow() {
        String partName = getPart();

        if (partName == "") {
            partName = getPart();
        }

        partService.addNewPart(partName);
    }

    public void startAddManufacturerFlow() {
        String manufacturerName = getManufacturer();

        if (manufacturerName == "") {
            manufacturerName = getManufacturer();
        }

        manufacturerService.addNewManufacturer(manufacturerName);
    }


    private String getPart() {
        return operator.getStringInput("Enter the part name:");
    }

    private String getManufacturer() {
        return operator.getStringInput("Enter the manufacturer name:");
    }
}
