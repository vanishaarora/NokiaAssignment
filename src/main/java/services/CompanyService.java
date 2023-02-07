package services;

import database.repository.CompanyPartRepository;
import database.repository.CompanyRepository;
import database.repository.PartManufacturerRepository;
import entity.CompanyPart;

import java.util.List;

public class CompanyService extends BaseService {

    CompanyRepository companyRepository;
    CompanyPartRepository companyPartRepository;
    PartManufacturerRepository partManufacturerRepository;


    public CompanyService(
            CompanyRepository companyRepository,
            CompanyPartRepository companyPartRepository,
            PartManufacturerRepository partManufacturerRepository
    ) {
        this.companyRepository = companyRepository;
        this.companyPartRepository = companyPartRepository;
        this.partManufacturerRepository = partManufacturerRepository;

        this.companyRepository.getCompany();
    }

    public void addMoney(float money) {
        var balance = companyRepository.addMoney(money);
        System.out.println("Amount added successfully.");
        System.out.println("Updated balance: " + (balance));
    }

    public String buyParts(String partName, int quantity) {
        var partManufacturerList = partManufacturerRepository.listQuantity("", partName);
        var company = companyRepository.getCompany();
        return companyPartRepository.buyPart(partManufacturerList, quantity, company);
    }

    public List<CompanyPart> listParts() {
        return companyPartRepository.listParts(companyRepository.getCompany());
    }
}
