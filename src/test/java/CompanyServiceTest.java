import database.repository.CompanyPartRepository;
import database.repository.CompanyRepository;
import database.repository.PartManufacturerRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.CompanyService;

public class CompanyServiceTest {

  private static final String JDBC_URL = "jdbc:h2:memFS:test7";
  private static final String USER = "sa";
  private static final String PASSWORD = "";

  private Connection connection = null;

  final CompanyRepository companyRepository = new CompanyRepository();
  final CompanyPartRepository companyPartRepository = new CompanyPartRepository();
  final PartManufacturerRepository partManufacturerRepository = new PartManufacturerRepository();

  CompanyService companyService = new CompanyService(companyRepository,companyPartRepository,partManufacturerRepository);

  public CompanyServiceTest(){}

  @Before
  public void setUp() throws SQLException {
    try{
      connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    catch (Exception e) {
      System.console().printf(e.getMessage());
    }
  }

  // Given quantity is greater than the available quantity
  @Test
  public void testBuyPartsInvalid() throws SQLException {
    String partName = "ball bearings";
    int quantity = 10;
    Assert.assertEquals("Not enough parts available to buy", companyService.buyParts(partName,quantity));
    connection.close();
  }

  // If quantity * price < Money in Company -> Then allow buy
  @Test
  public void testBuyPartsValid() throws SQLException {
    String partName = "ball bearings";
    int quantity = 0;
    Assert.assertEquals("Spent: 0", companyService.buyParts(partName,quantity));
    connection.close();
  }

  @After
  public void tearDown() throws Exception {
    try {
      connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
      connection.close();
    }
    catch (Exception e) {
      System.console().printf(e.getMessage());
    }

  }

}
