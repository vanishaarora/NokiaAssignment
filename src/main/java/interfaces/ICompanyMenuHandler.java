package interfaces;

import models.menu.Menu;

public interface ICompanyMenuHandler {
    void addMoney(Menu menu);

    void buyParts(Menu menu);

    void listParts(Menu menu);
}
