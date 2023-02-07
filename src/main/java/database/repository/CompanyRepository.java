package database.repository;

import database.DatabaseSessionFactory;
import entity.Company;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CompanyRepository {

  SessionFactory companySessionFactory = DatabaseSessionFactory.getDatabaseSession(Company.class);

  public Company getCompany() {
    var session = companySessionFactory.openSession();
    session.beginTransaction();
    String hql = "FROM Company C WHERE C.name = :name";
    Query query = session.createQuery(hql);
    query.setParameter("name", "Nokia");
    List<Company> results = query.list();

    if (results.isEmpty()) {
      var company = new Company();
      company.setName("Nokia");
      company.setBalance(0.0);
      session.save(company);
      session.getTransaction().commit();
      session.close();
      return company;
    }
    session.getTransaction().commit();
    session.close();
    return results.get(0);
  }

  public double addMoney(double amount) {
    var company = getCompany();
    var session = companySessionFactory.openSession();
    session.beginTransaction();
    company.setBalance(company.getBalance() + amount);
    session.update(company);
    session.getTransaction().commit();
    session.close();

    return company.getBalance();
  }

}
