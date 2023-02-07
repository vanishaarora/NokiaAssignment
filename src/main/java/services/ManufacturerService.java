package services;

import database.repository.ManufacturerRepository;
import database.repository.PartManufacturerRepository;
import database.repository.PartRepository;
import entity.Manufacturer;
import entity.Part;
import entity.PartManufacturer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerService extends BaseService {

    final ManufacturerRepository manufacturerRepository;
    final PartManufacturerRepository partManufacturerRepository;

    final PartRepository partRepository;

    public ManufacturerService(
            ManufacturerRepository manufacturerRepository,
            PartManufacturerRepository partManufacturerRepository,
            PartRepository partRepository
    ) {
        this.manufacturerRepository = manufacturerRepository;
        this.partManufacturerRepository = partManufacturerRepository;
        this.partRepository = partRepository;
    }

    public String addQuantity(String partName, String manufacturerName, int quantity, float price) {
        Optional<Part> part = getPart(partName);
        Optional<Manufacturer> manufacturer = getManufacturer(manufacturerName);

        if (part.isEmpty() || manufacturer.isEmpty()) {
            return "Invalid input";
        }

        PartManufacturer partManufacturer = new PartManufacturer();
        partManufacturer.setPart(part.get());
        partManufacturer.setManufacturer(manufacturer.get());
        partManufacturer.setPrice(price);
        partManufacturer.setQuantity(quantity);

        try {
            partManufacturerRepository.updateQuantity(partManufacturer);
            System.out.println("Successfully updated quantity");
            return "Successfully updated quantity";
        } catch (Exception e) {
            System.out.println("Unable to update quantity");
            throw e;
        }
    }

    public List<PartManufacturer> listQuantity(String partName, String manufacturerName) {

        Optional<Part> part = getPart(partName);
        Optional<Manufacturer> manufacturer = getManufacturer(manufacturerName);

        if (part.isEmpty()) {
            return new ArrayList<>();
        }

        List<PartManufacturer> list;
        if (manufacturer.isPresent())
            list = partManufacturerRepository.listQuantity(manufacturerName, partName);
        else
            list = partManufacturerRepository.listQuantity("", partName);

        return list;
    }

    public String addNewManufacturer(String manufacturerName) {
        try {
            var manufacturer = manufacturerRepository.getManufacturerByName(manufacturerName);
            if (manufacturer.isPresent()) {
                System.out.println("Manufacturer already exist!");
                return "Manufacturer already exist!";
            }
            manufacturerRepository.addManufacturer(manufacturerName);
            System.out.println("Manufacturer successfully added");
        } catch (Exception e) {
            System.out.println("Manufacturer already exist, enter new manufacturer name ");
        }
        return "Manufacturer successfully added";
    }

    public String removeManufacturer(String manufacturerName) {
        try {
            var manufacturer = manufacturerRepository.getManufacturerByName(manufacturerName);
            if (manufacturer.isPresent()) {
                manufacturerRepository.removeManufacturer(manufacturer.get());
                System.out.println("Manufacturer removed successfully!");
                return "Manufacturer removed successfully!";
            } else {
                System.out.println("Manufacturer does not exist!");
                return "Manufacturer does not exist!";
            }
        } catch (Exception e) {
            System.out.println("Manufacturer does not exist!");
        }
        return "Manufacturer does not exist!";
    }

    private Optional<Manufacturer> getManufacturer(String manufacturerName) {
        try {
            return manufacturerRepository.getManufacturerByName(manufacturerName);
        } catch (Exception e) {
            System.out.println("Manufacturer with name: " + manufacturerName + "does not exist");
            return Optional.empty();
        }
    }

    private Optional<Part> getPart(String partName) {
        try {
            return partRepository.getPartByName(partName);
        } catch (Exception e) {
            System.out.println("Part with name: " + partName + " does not exist");
            return Optional.empty();
        }
    }
}
