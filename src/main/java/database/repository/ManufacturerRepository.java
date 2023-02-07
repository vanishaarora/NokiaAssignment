package database.repository;

import database.DatabaseSessionFactory;
import entity.Manufacturer;
import java.util.Optional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class ManufacturerRepository {

  SessionFactory manufacturerSessionFactory = DatabaseSessionFactory.getDatabaseSession(
      Manufacturer.class);

  public Optional<Manufacturer> getManufacturerByName(String manufacturerName)
      throws HibernateException {
    Session session = manufacturerSessionFactory.openSession();
    String hql = "FROM Manufacturer m WHERE m.name = :name";
    Query query = session.createQuery(hql);
    query.setParameter("name", manufacturerName);
    Optional<Manufacturer> manufacturers = query.list().stream().findFirst();
    session.close();
    return manufacturers;
  }

  public void addManufacturer(String manufacturerName) {
    Session session = manufacturerSessionFactory.openSession();
    Manufacturer newManufacturer = new Manufacturer();
    newManufacturer.setName(manufacturerName);
    session.save(newManufacturer);
    session.close();
  }

  public void removeManufacturer(Manufacturer manufacturer) {
    Session session = manufacturerSessionFactory.openSession();
    session.beginTransaction();
    session.delete(manufacturer);
    session.getTransaction().commit();
    session.close();
  }
}
