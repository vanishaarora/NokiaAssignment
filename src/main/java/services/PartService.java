package services;

import database.repository.PartRepository;
import entity.Part;

import java.util.Optional;

public class PartService extends BaseService {

    PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void addNewPart(String partName) {
        Optional<Part> part = Optional.empty();
        try {
            part = partRepository.getPartByName(partName);
        } catch (Exception ignored) {
        }

        if (part.isPresent()) {
            System.out.println("Part already exist, enter new part name ");
            return;
        }
        partRepository.addPart(partName);
        System.out.println("Part successfully added");
    }
}
