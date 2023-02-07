import database.repository.CompanyPartRepository;
import database.repository.CompanyRepository;
import database.repository.PartManufacturerRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import services.CompanyService;

public class CompanyServiceTest {

  private Connection connection;

  final CompanyRepository companyRepository = new CompanyRepository();
  final CompanyPartRepository companyPartRepository = new CompanyPartRepository();
  final PartManufacturerRepository partManufacturerRepository = new PartManufacturerRepository();

  CompanyService companyService = new CompanyService(companyRepository,companyPartRepository,partManufacturerRepository);

  public CompanyServiceTest(){}

  // Given quantity is greater than the available quantity
  @Test
  public void testBuyPartsInvalid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test7", "sa", "");
    } catch (Exception e){

    }
    String partName = "ball bearings";
    int quantity = 10;
    Assert.assertEquals("Not enough parts available to buy", companyService.buyParts(partName,quantity));
    connection.close();
  }

  // If quantity * price < Money in Company -> Then allow buy
  @Test
  public void testBuyPartsValid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test7", "sa", "");
    } catch (Exception e){

    }
    String partName = "ball bearings";
    int quantity = 0;
    Assert.assertEquals("Spent: 0", companyService.buyParts(partName,quantity));
    connection.close();
  }

}
