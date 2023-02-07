package interfaces;

import models.menu.Menu;

public interface IManufacturerMenuHandler {
    String addQuantity(Menu menu);

    void listQuantity(Menu menu);
}
