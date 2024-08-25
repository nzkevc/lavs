package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EmployerTests {
  @Test
  public void fullConstructorTest() {
    // Arrange
    Employer employer =
        new Employer("123", "Test Employer", null, null, null, "www.testemployer.com", true);

    // Act
    boolean isCorrect =
        employer.getCustomerId().equals("123")
            && employer.getName().equals("Test Employer")
            && employer.getEmail() == null
            && employer.getPhone() == null
            && employer.getAddress() == null
            && employer.getWebsite().equals("www.testemployer.com")
            && employer.isOwner();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void constructorTest() {
    // Arrange
    Employer employer = new Employer("123", 1);
    employer.setName("Test Employer");
    employer.setEmail(null);
    employer.setPhone(null);
    employer.setAddress(null);
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    // Act
    boolean isCorrect =
        employer.getCustomerId().equals("123")
            && employer.getNumber() == 1
            && employer.getName().equals("Test Employer")
            && employer.getEmail() == null
            && employer.getPhone() == null
            && employer.getAddress() == null
            && employer.getWebsite().equals("www.testemployer.com")
            && employer.isOwner();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Employer employer = new Employer();
    employer.setCustomerId("123");
    employer.setNumber(1);
    employer.setName("Test Employer");
    employer.setWebsite("www.testemployer.com");
    employer.setOwner(true);

    // Act
    boolean isCorrect =
        employer.getCustomerId().equals("123")
            && employer.getNumber() == 1
            && employer.getName().equals("Test Employer")
            && employer.getWebsite().equals("www.testemployer.com")
            && employer.isOwner();

    // Assert
    assertTrue(isCorrect);
  }
}
