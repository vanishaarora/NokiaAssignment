package database.repository;

import database.DatabaseFactory;
import entity.Part;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PartRepository {

  public SessionFactory partsSessionFactory = DatabaseFactory.getDatabaseInstance(Part.class);

  public Optional<Part> getPartByName(String partName) throws Exception {
    try {
      Session session = partsSessionFactory.openSession();
      String hql = "FROM Part m WHERE m.name = :name";
      Query query = session.createQuery(hql);
      query.setParameter("name", partName);
      List<Part> parts = query.list();
      session.close();
      if (parts.size() == 0) {
        throw new Exception("Part does not exist");
      }
      return parts.stream().findFirst();
    } catch (Exception e) {
      throw e;
    }
  }

  public void addPart(String partName) {
    Session session = partsSessionFactory.openSession();
    Part newPart = new Part();
    newPart.setName(partName);
    session.save(newPart);
    session.close();
  }
}
