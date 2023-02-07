package database.repository;

import database.DatabaseSessionFactory;
import entity.Company;
import entity.CompanyPart;
import entity.PartManufacturer;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CompanyPartRepository {

  SessionFactory companyPartSessionFactory = DatabaseSessionFactory.getDatabaseSession(
      CompanyPart.class);

  public String buyPart(List<PartManufacturer> partManufacturerList, int quantity,
      Company company) {

    var toBeBoughtQuantity = quantity;

    // Sort the PartManufacture list by price
    partManufacturerList.sort((a, b) ->
        Float.compare(a.getPrice(), b.getPrice())
    );

    int totalCost = 0;
    var companyPartList = new ArrayList<CompanyPart>();
    for (PartManufacturer partManufacturer : partManufacturerList) {

      int partsToBuy = Math.min(partManufacturer.getQuantity(), quantity);
      totalCost += partsToBuy * partManufacturer.getPrice();
      quantity -= partsToBuy;
      partManufacturer.setQuantity(partManufacturer.getQuantity() - partsToBuy);

      System.out.println(
          partsToBuy + " quantity is taken from " + partManufacturer.getManufacturer().getName()
              + " and added to the company.");

      if (toBeBoughtQuantity != quantity) {
        var companyPart = new CompanyPart();
        companyPart.setPart(partManufacturer.getPart());
        companyPart.setManufacturer(partManufacturer.getManufacturer());
        companyPart.setCompany(company);
        companyPart.setQuantity(partsToBuy);
        companyPartList.add(companyPart);
        updateCompanyPart(companyPart);
      }
      updatePartManufacturer(partManufacturer);
      if (quantity == 0) {
        break;
      }
    }
    if (quantity > 0) {
      System.out.println("Not enough parts available to buy");
      return "Not enough parts available to buy";
    }

    if (totalCost > company.getBalance()) {
      System.out.println("Not enough money to buy all the parts");
      return "Not enough money to buy all the parts";
    }

    company.setBalance(company.getBalance() - totalCost);
    company.setCompanyParts(companyPartList);
    updateCompany(company);
    System.out.println("Spent: " + totalCost);
    return "Spent: " + totalCost;
  }

  public List<CompanyPart> listParts(Company company) {
    var session = companyPartSessionFactory.openSession();
    session.beginTransaction();
    String hql = "SELECT p " +
        "FROM CompanyPart p " +
        "WHERE p.company.name = :companyName ";
    Query query = session.createQuery(hql);
    query.setParameter("companyName", company.getName());
    List<CompanyPart> companyParts = query.list();
    session.getTransaction().commit();
    return companyParts;
  }

  private void updateCompanyPart(CompanyPart companyPart) {
    var session = companyPartSessionFactory.openSession();
    session.beginTransaction();

    String hql = "SELECT p " +
        "FROM CompanyPart p " +
        "WHERE p.company.name = :companyName AND p.manufacturer.name = :manufacturerName AND p.part.name = :partName";
    Query query = session.createQuery(hql);
    query.setParameter("companyName", companyPart.getCompany().getName());
    query.setParameter("manufacturerName", companyPart.getManufacturer().getName());
    query.setParameter("partName", companyPart.getPart().getName());
    List<CompanyPart> companyParts = query.list();

    if (companyParts.isEmpty()) {
      session.save(companyPart);
    } else {
      session.update(companyPart);
    }
    session.getTransaction().commit();
    session.close();
  }

  private void updatePartManufacturer(PartManufacturer partManufacturer) {
    SessionFactory partManufacturerFactory = DatabaseSessionFactory.getDatabaseSession(
        PartManufacturer.class);
    var session = partManufacturerFactory.openSession();
    session.beginTransaction();
    session.update(partManufacturer);
    session.getTransaction().commit();
    session.close();
    partManufacturerFactory.close();
  }

  private void updateCompany(Company company) {
    SessionFactory companySessionFactory = DatabaseSessionFactory.getDatabaseSession(Company.class);
    var session = companySessionFactory.openSession();
    session.beginTransaction();
    session.update(company);
    session.getTransaction().commit();
    session.close();
    companySessionFactory.close();
  }
}
