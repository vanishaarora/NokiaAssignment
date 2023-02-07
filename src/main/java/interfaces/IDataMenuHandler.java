package interfaces;

import models.menu.Menu;

public interface IDataMenuHandler {
    void addPart(Menu menu);

    void addManufacturer(Menu menu);

    void removeManufacturer(Menu menu);
}
