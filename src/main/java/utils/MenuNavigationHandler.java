package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import handlers.routeHandlers.RouteHandler;
import models.menu.Menu;
import models.menu.MenuCategory;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.*;

public class MenuNavigationHandler {

  private MenuNavigationHandler() {
  }

  public static MenuNavigationHandler instance = new MenuNavigationHandler();

  private List<Menu> completeMenuList = new ArrayList<>();

  public void loadAndShowMenus(String path) throws Exception {
    try (Reader reader = new InputStreamReader(
        Objects.requireNonNull(this.getClass().getResourceAsStream(path)))) {
      Type listType = new TypeToken<List<Menu>>() {
      }.getType();
      completeMenuList = new Gson().fromJson(reader, listType);
      showMenus(MenuCategory.INITIAL);
    } catch (Exception e) {
      throw new Exception("Unable to load menus");
    }
  }

  public void showMenus(MenuCategory menuCategory) {
    List<Menu> currentMenuList = new ArrayList<>();

    completeMenuList.forEach(menu -> {
      if (menu.getCategory() == menuCategory) {
        displayMenuItem(menu);
        currentMenuList.add(menu);
      }
    });

    //current menu list will be used to navigate between menus
    getUserInput(currentMenuList);
  }

  public void showMenus(List<Menu> menuList) {
    menuList.forEach(this::displayMenuItem);
    getUserInput(menuList);
  }

  public void showMenus(Menu menu) {
    menu.getChildren().forEach(this::displayMenuItem);
    getUserInput(menu.getChildren());
  }

  public void displayMenuItem(Menu menu) {
    System.out.println(menu.getDisNumber() + ": " + menu.getDisName());
  }

  private void getUserInput(List<Menu> currentMenuList) {

    int choice = readInput();
    var chosenMenu = currentMenuList.stream().filter(menu -> menu.getDisNumber() == choice)
        .findFirst();

    if (chosenMenu.isEmpty()) {
      System.out.println("Please enter a valid input");
      getUserInput(currentMenuList);
    }

    handleInput(chosenMenu.get());
  }

  private void handleInput(Menu menu) {
    RouteHandler.shared.handleSelectedMenu(menu);
  }

  private int readInput() {
    try {
      System.out.println("Enter input : ");
      Scanner sc = new Scanner(System.in);
      return sc.nextInt();
    } catch (InputMismatchException e) {
      System.out.println("\033[41m" + "Input type should be number" + "\033[0m");
      return readInput();
    }
  }

  public void goBack(Menu menu) {

    var parentMenu = getMenuFromId(menu.getParentId());
    if (parentMenu.isPresent()) {
      List<Menu> items;
      switch (menu.getType()) {
        case BACK, PARENT -> {
          items = getSiblingsOfMenu(parentMenu.get());
        }
        default -> {
          items = parentMenu.get().getChildren();
        }
      }
      showMenus(items);
    } else {
      System.out.println("Cannot go back, please select a new option!");
      readInput();
    }
  }

  private List<Menu> getSiblingsOfMenu(Menu menu) {
    var menus = getSiblings(completeMenuList, menu);
    if (menus.isEmpty()) {
      System.out.println("Cannot go back, please select a new option!");
      readInput();
    }
    return menus;
  }

  private List<Menu> getSiblings(List<Menu> menuList, Menu menu) {
    for (int i = 0; i < menuList.size(); i++) {
      var item = menuList.get(i);
      if (item.getId() == menu.getId()) {
        return menuList;
      }
      getSiblings(item.getChildren(), menu);
    }
    return new ArrayList<>();
  }

  private Optional<Menu> getMenuFromId(String id) {
    return getMenuItemFromList(completeMenuList, id);
  }

  private Optional<Menu> getMenuItemFromList(List<Menu> items, String id) {
    for (int i = 0; i < (long) items.size(); i++) {
      Menu item = items.get(i);
      if (Objects.equals(item.getId(), id)) {
        return Optional.of(item);
      } else {
        getMenuItemFromList(item.getChildren(), id);
      }
    }
    return Optional.empty();
  }
}
