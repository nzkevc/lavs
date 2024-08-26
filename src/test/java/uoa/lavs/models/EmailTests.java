package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import uoa.lavs.utils.objects.ValidationException;

public class EmailTests {
  @Test
  public void constructorTest() {
    // Arrange
    Email email = new Email("123", 1);
    email.setAddress("123@123.com");
    email.setIsPrimary(true);

    // Act
    boolean isCorrect =
        email.getCustomerId().equals("123")
            && email.getNumber() == 1
            && email.getAddress().equals("123@123.com")
            && email.getIsPrimary();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void noAddressContstructorTest() {
    // Arrange
    Email email = new Email("123", 1);
    email.setAddress("123@123.com");
    email.setIsPrimary(true);

    // Act
    boolean isCorrect =
        email.getCustomerId().equals("123")
            && email.getNumber() == 1
            && email.getAddress().equals("123@123.com")
            && email.getIsPrimary();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Email email = new Email();
    email.setCustomerId("123");
    email.setNumber(1);
    email.setAddress("123@123.com");
    email.setIsPrimary(true);

    // Act
    boolean isCorrect =
        email.getCustomerId().equals("123")
            && email.getNumber() == 1
            && email.getAddress().equals("123@123.com")
            && email.getIsPrimary();

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void equalsTest() {
    // Arrange
    Email email1 = new Email("123", 1);
    email1.setIsPrimary(true);
    email1.setAddress("123@123.com");
    Email email2 = new Email("123", 1);

    // Act
    email2.setAddress("123@123.com");
    email2.setIsPrimary(true);

    // Assert
    assertTrue(email1.equals(email2));
    assertTrue(email2.equals(email1));
  }

  @Test
  public void notEqualObjectTest() {
    // Arrange
    Email email1 = new Email();
    String email2 = "123@123.com";

    // Act

    // Assert
    assertFalse(email1.equals(email2));
  }

  @Test
  public void noteEqualNullTest() {
    // Arrange
    Email email1 = new Email();
    Email email2 = null;

    // Act

    // Assert
    assertFalse(email1.equals(email2));
  }

  @Test
  public void validateTest() {
    // Arrange
    Email email = new Email("123", 1);

    // Act

    // Assert
    // assertTrue(email.validate());
  }

  @Test
  public void validateValidEmailTest() {
    // Arrange
    Email email = new Email("123", 1);
    email.setAddress("123@123.com");

    // Act
    // Assert
    assertDoesNotThrow(() -> Email.validateCustomerId(email.getCustomerId()));
    assertDoesNotThrow(() -> Email.validateAddress(email.getAddress()));
  }

  @Test
  public void validateNullEmailTest() {
    // Arrange
    Email email = new Email();

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Email.validateCustomerId(email.getCustomerId()));
    assertThrows(ValidationException.class, () -> Email.validateAddress(email.getAddress()));
  }

  @Test
  public void validateEmptyEmailTest() {
    // Arrange
    Email email = new Email("123", 1);
    email.setCustomerId("");
    email.setAddress("");

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Email.validateCustomerId(email.getCustomerId()));
    assertThrows(ValidationException.class, () -> Email.validateAddress(email.getAddress()));
  }

  @Test
  public void validateOverLimitEmailTest() {
    // Arrange
    Email email = new Email("123", 1);
    email.setCustomerId("1111111111111111111111111111111111111111");
    email.setAddress(
        "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Email.validateCustomerId(email.getCustomerId()));
    assertThrows(ValidationException.class, () -> Email.validateAddress(email.getAddress()));
  }
}
