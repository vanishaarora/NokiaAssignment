import database.repository.ManufacturerRepository;
import database.repository.PartManufacturerRepository;
import database.repository.PartRepository;
import entity.PartManufacturer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;


import org.junit.After;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import services.ManufacturerService;


public class ManufacturerServiceTest {

  private static final String JDBC_URL = "jdbc:h2:memFS:test6";
  private static final String USER = "sa";
  private static final String PASSWORD = "";


  final ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
  final PartManufacturerRepository partManufacturerRepository = new PartManufacturerRepository();
  final PartRepository partRepository = new PartRepository();

  private Connection connection= null;

  ManufacturerService manufacturerService = new ManufacturerService
      (manufacturerRepository,partManufacturerRepository,partRepository);


  public ManufacturerServiceTest() {
  }

  @Before
  public void setUp() throws SQLException {
    try{
      connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
    catch (Exception e) {
      System.console().printf(e.getMessage());
    }
  }

  // Test add quantity : Invalid Part Name and Manufacturer Name
  @Test
  public void testAddQuantityInvalidPartAndManufacturer() throws SQLException {
    String partName = "ball bearings";
    String manufacturerName = "manufacturerA";
    int quantity = 10;
    float price = 100;
    Assert.assertEquals("Invalid input", manufacturerService.addQuantity(partName,
        manufacturerName, quantity, price));
    connection.close();
  }

  // Test add quantity : Invalid Manufacturer Name
  @Test
  public void testAddQuantityInvalidManufacturer() throws SQLException {
    String partName = "PART-4";
    String manufacturerName = "MANU1";
    int quantity = 10;
    float price = 100;
    Assert.assertEquals("Invalid input", manufacturerService.addQuantity(partName,
        manufacturerName, quantity, price));
    connection.close();

  }

  // Test add quantity : Invalid Part Name
  @Test
  public void testAddQuantityInvalidPart() throws SQLException {
    String partName = "PART4";
    String manufacturerName = "MANU-1";
    int quantity = 10;
    float price = 100;
    Assert.assertEquals("Invalid input", manufacturerService.addQuantity(partName,
        manufacturerName, quantity, price));
    connection.close();

  }

  // Test add quantity : Valid
  @Test
  public void testAddQuantityValid() throws SQLException {
    String partName = "ball bearings";
    String manufacturerName = "MANU-1";
    int quantity = 0;
    float price = 0;
    Assert.assertEquals("Successfully updated quantity", manufacturerService.addQuantity(partName,
        manufacturerName, quantity, price));
    connection.close();

  }

  // Test list quantity : Invalid Part Name
  @Test
  public void testListQuantityInvalidPart() throws SQLException {
    String partName = "PART4";
    String manufacturerName = "MANU-1";
    List<PartManufacturer> expected = Arrays.asList();
    Assert.assertEquals(expected,manufacturerService.listQuantity(partName,manufacturerName));
    connection.close();
  }

  // Test list quantity : valid
  @Test
  public void testListQuantityValid() throws SQLException {
    String partName = "PART-4";
    String manufacturerName = "MANUR1";
    PartManufacturer partManufacturer = new PartManufacturer();
    partManufacturer.setId(202l);
    partManufacturer.setPrice(100.0f);
    partManufacturer.setQuantity(100);
    String expected = partManufacturer.toString();
    Assert.assertEquals(expected,manufacturerService.listQuantity(partName,manufacturerName).get(0).toString());
    connection.close();
  }

  // Test add new manufacturer : valid
  @Test
  public void testAddNewManufacturerValid() throws SQLException {
    String manufacturerName = "MANUFA1";
    Assert.assertEquals("Manufacturer successfully added",manufacturerService.addNewManufacturer(manufacturerName));
    connection.close();
  }

  // Test add new manufacturer : Invalid if manufacturer already exists
  @Test
  public void testAddNewManufacturerInvalid() throws SQLException {
    String manufacturerName = "MANU-1";
    Assert.assertEquals("Manufacturer already exist!",manufacturerService.addNewManufacturer(manufacturerName));
    connection.close();
  }

  @Test
  public void testRemoveManufacturerInValid() throws SQLException {
    String manufacturerName = "MANU-1";
    Assert.assertEquals("Manufacturer does not exist!",manufacturerService.removeManufacturer(manufacturerName));
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
