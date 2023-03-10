package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseFactory {

  public static SessionFactory getDatabaseInstance(Class classDetails) {
    try {
      // creates a new database connection
      return new Configuration().configure().addAnnotatedClass(classDetails)
          .buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
