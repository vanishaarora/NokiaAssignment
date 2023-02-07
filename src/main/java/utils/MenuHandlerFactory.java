package utils;

import handlers.menuHandlers.*;
import interfaces.IMenuHandler;
import models.menu.Menu;

import java.util.HashMap;

public final class MenuHandlerFactory {

  private final HashMap<String, IMenuHandler> menuHandlers = new HashMap<>();

  public IMenuHandler getFactory(Menu menu) {
    try {
      return getMenuHandler(menu);
    } catch (Exception exp) {
      System.out.println(exp.getMessage());
    }

    return null;
  }

  public IMenuHandler getMenuHandler(Menu menu) throws Exception {

    String handlerKey = menu.getCategory().getHandlerKey();

    var handler = menuHandlers.get(handlerKey);
    if (handler == null) {
      switch (menu.getCategory()) {
        case INITIAL -> {
          var newHandler = new InitialMenuHandler();
          menuHandlers.put(handlerKey, newHandler);
          return newHandler;
        }
        case DATA -> {
          var newHandler = new DataMenuHandler();
          menuHandlers.put(handlerKey, newHandler);
          return newHandler;
        }
        case COMPANY -> {
          var newHandler = new CompanyMenuHandler();
          menuHandlers.put(handlerKey, newHandler);
          return newHandler;
        }
        case MANUFACTURERS -> {
          var newHandler = new ManufacturerMenuHandler();
          menuHandlers.put(handlerKey, newHandler);
          return newHandler;
        }
        default -> throw new Exception("Found unsupported Menu Category");
      }
    }
    return handler;
  }
}



