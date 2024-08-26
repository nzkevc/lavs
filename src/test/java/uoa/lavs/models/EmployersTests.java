package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class EmployersTests {
  @Test
  public void constructorSetTest() {
    // Arrange
    Employer employer = new Employer();
    employer.setCustomerId("123");
    employer.setNumber(1);
    employer.setName("Test Employer");
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    Set<Employer> employerSet = new HashSet<>();
    employerSet.add(employer);

    Employers employers = new Employers(employerSet);

    // Act
    // Assert
    assertTrue(employers.getEmployers().contains(employer));
  }

  @Test
  public void constructorCustomerIdTest() {
    // Arrange
    Employers employers = new Employers("123");

    // Act
    // Assert
    assertTrue(employers.getCustomerId().equals("123"));
  }

  @Test
  public void addEmployerTest() {
    // Arrange
    Employers employers = new Employers("123");
    Employer employer = new Employer();
    employer.setCustomerId("123");
    employer.setNumber(1);
    employer.setName("Test Employer");
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    // Act
    employers.addEmployer(employer);

    // Assert
    assertTrue(employers.getEmployers().contains(employer));
  }

  @Test
  public void getEmployerCountTest() {
    // Arrange
    Employers employers = new Employers("123");
    Employer employer = new Employer();
    employer.setCustomerId("123");
    employer.setNumber(1);
    employer.setName("Test Employer");
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    // Act
    employers.addEmployer(employer);

    // Assert
    assertTrue(employers.getEmployerCount() == 1);
  }

  @Test
  public void setCustomerIdTest() {
    // Arrange
    Employers employers = new Employers("124");
    Employer employer = new Employer();
    employer.setCustomerId("123");
    employer.setNumber(1);
    employer.setName("Test Employer");
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    // Act
    employers.setCustomerId("124");
    employers.addEmployer(employer);

    // Assert
    assertTrue(employers.getCustomerId().equals("124"));
  }

  @Test
  public void addNullEmployerTest() {
    // Arrange
    Employers employers = new Employers("123");

    // Act
    employers.addEmployer(null);

    // Assert
    assertTrue(employers.getEmployerCount() == 0);
  }
}
