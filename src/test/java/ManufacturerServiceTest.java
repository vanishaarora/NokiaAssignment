import database.repository.ManufacturerRepository;
import database.repository.PartManufacturerRepository;
import database.repository.PartRepository;
import entity.PartManufacturer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Arrays;
import java.util.List;


import org.junit.Assert;

import org.junit.Test;
import services.ManufacturerService;


public class ManufacturerServiceTest {

  final ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
  final PartManufacturerRepository partManufacturerRepository = new PartManufacturerRepository();
  final PartRepository partRepository = new PartRepository();

  private Connection connection;

  ManufacturerService manufacturerService = new ManufacturerService
      (manufacturerRepository,partManufacturerRepository,partRepository);


  public ManufacturerServiceTest() {
  }

  // Test add quantity : Invalid Part Name and Manufacturer Name
  @Test
  public void testAddQuantityInvalidPartAndManufacturer() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
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
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
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
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
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
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String partName = "ball bearings";
    String manufacturerName = "MANUFA";
    int quantity = 10;
    float price = 100;
    Assert.assertEquals("Successfully updated quantity", manufacturerService.addQuantity(partName,
        manufacturerName, quantity, price));
    connection.close();

  }

  // Test list quantity : Invalid Part Name
  @Test
  public void testListQuantityInvalidPart() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String partName = "PART4";
    String manufacturerName = "MANU-1";
    List<PartManufacturer> expected = Arrays.asList();
    Assert.assertEquals(expected,manufacturerService.listQuantity(partName,manufacturerName));
    connection.close();
  }

  // Test list quantity : valid
  @Test
  public void testListQuantityValid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String partName = "PART-4";
    String manufacturerName = "MANUR1";
    PartManufacturer partManufacturer = new PartManufacturer();
    partManufacturer.setId(202l);
    partManufacturer.setPrice(100.0f);
    partManufacturer.setQuantity(110);
    String expected = partManufacturer.toString();
    Assert.assertEquals(expected,manufacturerService.listQuantity(partName,manufacturerName).get(0).toString());
    connection.close();
  }

  // Test add new manufacturer : valid
  @Test
  public void testAddNewManufacturerValid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String manufacturerName = "MANUFA1";
    Assert.assertEquals("Manufacturer successfully added",manufacturerService.addNewManufacturer(manufacturerName));
    connection.close();
  }

  // Test add new manufacturer : Invalid if manufacturer already exists
  @Test
  public void testAddNewManufacturerInvalid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String manufacturerName = "MANU-1";
    Assert.assertEquals("Manufacturer already exist!",manufacturerService.addNewManufacturer(manufacturerName));
    connection.close();
  }

  @Test
  public void testRemoveManufacturerInValid() throws SQLException {
    try{
      Class.forName ("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/test6", "sa", "");
    } catch (Exception e){

    }
    String manufacturerName = "MANU-1";
    Assert.assertEquals("Manufacturer does not exist!",manufacturerService.removeManufacturer(manufacturerName));
    connection.close();
  }
}
