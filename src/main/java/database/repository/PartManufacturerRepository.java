package database.repository;

import database.DatabaseFactory;
import entity.PartManufacturer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PartManufacturerRepository {

  SessionFactory partManufacturerFactory = DatabaseFactory.getDatabaseInstance(
      PartManufacturer.class);

  public void updateQuantity(PartManufacturer partManufacturer) {
    try {
      Session session = partManufacturerFactory.openSession();
      session.beginTransaction();

      String hql = "FROM PartManufacturer WHERE part.name = :partName AND manufacturer.name = :manufacturerName";
      Query query = session.createQuery(hql);
      query.setParameter("partName", partManufacturer.getPart().getName());
      query.setParameter("manufacturerName", partManufacturer.getManufacturer().getName());
      List<PartManufacturer> partManufacturerList = query.list();

      if (!partManufacturerList.isEmpty()) {
        partManufacturerList.get(0).setQuantity(
            partManufacturerList.get(0).getQuantity() + partManufacturer.getQuantity());
        partManufacturerList.get(0).setPrice(partManufacturer.getPrice());
        session.update(partManufacturerList.get(0));
      } else {
        session.save(partManufacturer);
      }
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public List<PartManufacturer> listQuantity(String manufacturerName, String partName) {
    Session session = partManufacturerFactory.openSession();
    session.beginTransaction();

    String hql = "SELECT p " +
        "FROM PartManufacturer p " +
        "WHERE p.part.name = :partName ";

    if (manufacturerName != null && !manufacturerName.isEmpty()) {
      hql += "AND p.manufacturer.name = :manufacturerName";
    }

    Query query = session.createQuery(hql);
    query.setParameter("partName", partName);

    if (manufacturerName != null && !manufacturerName.isEmpty()) {
      query.setParameter("manufacturerName", manufacturerName);
    }

    List<PartManufacturer> result = query.list();

    session.getTransaction().commit();
    session.close();

    return result;
  }

}
