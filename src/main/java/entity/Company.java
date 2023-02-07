package entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private Double balance;

  private String name;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<CompanyPart> companyParts;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<CompanyPart> getCompanyParts() {
    return companyParts;
  }

  public void setCompanyParts(List<CompanyPart> companyParts) {
    this.companyParts = companyParts;
  }

  @Override
  public String toString() {
    return "Company{" +
        "id=" + id +
        ", balance=" + balance +
        ", name='" + name + '\'' +
        '}';
  }
}
