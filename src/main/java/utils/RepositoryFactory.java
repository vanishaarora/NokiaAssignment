package utils;

import database.repository.*;

public class RepositoryFactory {

    private static CompanyRepository companyRepository = null;
    private static CompanyPartRepository companyPartRepository = null;
    private static ManufacturerRepository manufacturerRepository = null;
    private static PartManufacturerRepository partManufacturerRepository = null;
    private static PartRepository partRepository = null;

    public static CompanyRepository getCompanyRepository() {
        if (companyRepository == null) {
            companyRepository = new CompanyRepository();
        }
        return companyRepository;
    }

    public static CompanyPartRepository getCompanyPartRepository() {
        if (companyPartRepository == null) {
            companyPartRepository = new CompanyPartRepository();
        }
        return companyPartRepository;
    }

    public static ManufacturerRepository getManufacturerRepository() {
        if (manufacturerRepository == null) {
            manufacturerRepository = new ManufacturerRepository();
        }
        return manufacturerRepository;
    }

    public static PartManufacturerRepository getPartManufacturerRepository() {
        if (partManufacturerRepository == null) {
            partManufacturerRepository = new PartManufacturerRepository();
        }
        return partManufacturerRepository;
    }

    public static PartRepository getPartRepository() {
        if (partRepository == null) {
            partRepository = new PartRepository();
        }
        return partRepository;
    }
}
