package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.utils.objects.ValidationException;

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

  @Test
  public void equalsTest() {
    Address address = TestEntityCreator.createBasicAddress(new Customer());
    address.setCustomerId(null);
    address.setNumber(null);

    Phone phone = TestEntityCreator.createBasicPhone(new Customer());
    phone.setCustomerID(null);
    phone.setNumber(null);

    Email email = TestEntityCreator.createBasicEmail(new Customer());
    email.setCustomerId(null);
    email.setNumber(null);

    // Arrange
    Employer employer1 =
        new Employer("123", "Test Employer", address, phone, email, "www.testemployer.com", true);
    Employer employer2 =
        new Employer("123", "Test Employer", address, phone, email, "www.testemployer.com", true);

    // Act
    // Assert
    assertTrue(employer1.equals(employer2));
  }

  @Test
  public void equalsNullTest() {
    // Arrange
    Employer employer1 = new Employer();
    Employer employer2 = null;

    // Act
    // Assert
    assertFalse(employer1.equals(employer2));
  }

  @Test
  public void validateValidEmployerTest() {
    // Arrange
    Employer employer =
        new Employer("123", "Test Employer", null, null, null, "www.testemployer.com", true);
    Phone phone = new Phone("123", 1);
    phone.setPrefix("+64");
    phone.setPhoneNumber("123456789");
    employer.setPhone(phone);

    // Act
    // Assert
    assertDoesNotThrow(() -> Employer.validateCustomerId(employer.getCustomerId()));
    assertDoesNotThrow(() -> Employer.validateName(employer.getName()));
    assertDoesNotThrow(() -> Employer.validatePhone(employer.getPhone().getFullNumber()));
    assertDoesNotThrow(() -> Employer.validateWebsite(employer.getWebsite()));
  }

  @Test
  public void validateNullEmployerTest() {
    // Arrange
    Employer employer = new Employer();

    // Act
    // Assert
    assertThrows(
        ValidationException.class, () -> Employer.validateCustomerId(employer.getCustomerId()));
    assertThrows(ValidationException.class, () -> Employer.validateName(employer.getName()));
    assertThrows(ValidationException.class, () -> Employer.validatePhone(null));
    assertThrows(ValidationException.class, () -> Employer.validateWebsite(employer.getWebsite()));
  }

  @Test
  public void validateEmptyEmployerTest() {
    // Arrange
    Employer employer = new Employer("", "", null, null, null, "", true);

    // Act
    // Assert
    assertThrows(
        ValidationException.class, () -> Employer.validateCustomerId(employer.getCustomerId()));
    assertThrows(ValidationException.class, () -> Employer.validateName(employer.getName()));
    assertThrows(ValidationException.class, () -> Employer.validatePhone(""));
    assertThrows(ValidationException.class, () -> Employer.validateWebsite(employer.getWebsite()));
  }

  @Test
  public void validateOverLimitEmployerTest() {
    // Arrange
    Employer employer = new Employer();
    employer.setCustomerId("1".repeat(20));
    employer.setName("1".repeat(100));
    employer.setWebsite("1".repeat(100));

    Phone phone = new Phone("1".repeat(20), 1);
    phone.setPrefix("1".repeat(20));
    phone.setPhoneNumber("1".repeat(30));

    // Act
    // Assert
    assertThrows(
        ValidationException.class, () -> Employer.validateCustomerId(employer.getCustomerId()));
    assertThrows(ValidationException.class, () -> Employer.validateName(employer.getName()));
    assertThrows(ValidationException.class, () -> Employer.validatePhone(phone.getFullNumber()));
    assertThrows(ValidationException.class, () -> Employer.validateWebsite(employer.getWebsite()));
  }
}
