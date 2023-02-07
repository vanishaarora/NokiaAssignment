package utils;

import models.menu.ServiceType;
import services.BaseService;
import services.CompanyService;
import services.ManufacturerService;
import services.PartService;

import java.util.HashMap;

public final class ServiceFactory {

    private static ServiceFactory instance = null;

    private final HashMap<String, BaseService> services = new HashMap<>();

    private ServiceFactory() {
    }

    public final static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public BaseService getService(ServiceType type) {
        try {
            return getServiceHandler(type);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
        return null;
    }

    private BaseService getServiceHandler(ServiceType type) throws Exception {

        String serviceKey = type.name();

        var service = services.get(serviceKey);
        if (service == null) {
            switch (type) {
                case COMPANY -> {
                    var newService = new CompanyService(RepositoryFactory.getCompanyRepository(), RepositoryFactory.getCompanyPartRepository(), RepositoryFactory.getPartManufacturerRepository());
                    services.put(serviceKey, newService);
                    return newService;
                }
                case MANUFACTURER -> {
                    var newService = new ManufacturerService(RepositoryFactory.getManufacturerRepository(), RepositoryFactory.getPartManufacturerRepository(), RepositoryFactory.getPartRepository());
                    services.put(serviceKey, newService);
                    return newService;
                }
                case PART -> {
                    var newService = new PartService(RepositoryFactory.getPartRepository());
                    services.put(serviceKey, newService);
                    return newService;
                }
                default -> throw new Exception("Found unsupported Service Type");
            }
        }
        return service;
    }
}
