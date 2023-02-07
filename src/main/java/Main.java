
import java.util.logging.Level;
import utils.MenuNavigationHandler;

public class Main {


  public static void main(String[] args) { startApp();
  }

  private static void startApp() {
    setHibernateLogLevel();

    try {
      //load and display initial menu from memory
      MenuNavigationHandler.instance.loadAndShowMenus("/menus.json");
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private static void setHibernateLogLevel() {
    java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
  }
}
