package utils;

import database.repository.ManufacturerRepository;

public class DataValidator {
    private static DataValidator instance = null;

    private DataValidator() {
    }

    public static DataValidator getInstance() {
        if (instance == null) {
            instance = new DataValidator();
        }

        return instance;
    }

    public boolean isQuantityValid(int quantity) {
        return quantity != 0;
    }

    public boolean isPriceValid(float price) {
        return price != 0;
    }
}
