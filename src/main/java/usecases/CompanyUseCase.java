package usecases;

import entity.CompanyPart;
import models.menu.ServiceType;
import services.CompanyService;
import utils.DataValidator;
import utils.IOOperator;
import utils.ServiceFactory;

import java.util.List;

public class CompanyUseCase {

    IOOperator operator = IOOperator.getInstance();
    DataValidator dataValidator = DataValidator.getInstance();

    CompanyService companyService = (CompanyService) ServiceFactory.getInstance().getService(ServiceType.COMPANY);

    public void startAddMoneyFlow() {
        float money = operator.getFloatInput("Enter the amount to be added:");
        companyService.addMoney(money);
    }

    public void startBuyPartsFlow() {
        String partName = getPart();
        int quantity = getQuantity();

        companyService.buyParts(partName, quantity);
    }

    public void startListPartsFlow() {
        List<CompanyPart> companyParts = companyService.listParts();
        for (CompanyPart companyPart : companyParts) {
            System.out.println("Part: " + companyPart.getPart().getName() + ", manufacturer: "
                    + companyPart.getManufacturer().getName() + ", quantity: " + companyPart.getQuantity());
        }
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
