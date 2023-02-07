# NokiaAssignment



1. This is a console application written in Java with backend as H2.
2. It executes certain database transactions for a business requirement of a Company trying to buy parts from host of manufactures.
3. It provides menu on the console to navigate to different areas to perform certain operations.
4. It covers Junit test cases for two of the services.

###Installation
1. Setup jdk 17 in your system
2. Clone the repository
3. Rebuild project and sync maven dependencies
4. Run Main.java file

###To run the test cases- 
1. To run the test cases for ManufacturerServiceTest class. First you need to change the database name from "hibernate.cfg.xml" located under src/main/resources. Change the value "nokia" on line 4 to "test6".
2. To run the test cases for CompanyServiceTest class. First you need to change the database name from "hibernate.cfg.xml" located under src/main/resources. Change the value "nokia" on line 4 to "test7".


###Build Status
SUCCESS


###Technologies used
1. Language Used - Java
2. Database used - H2
3. Dependency Manager - Maven
4. ORM - Hibernate
5. Testing - Junit


###Database Schema
It consists of five entities:
1. Company
2. CompanyPart
3. Manufacturer
4. Part 
5. PartManufacturer

