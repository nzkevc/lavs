package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import uoa.lavs.utils.objects.ValidationException;

public class CustomerTests {
  @Test
  public void constructorTest() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setVisa("N/A");

    // Act
    boolean isCorrect =
        customer.getId().equals("1")
            && customer.getTitle().equals("Mr")
            && customer.getName().equals("John Doe")
            && customer.getDateOfBirth().equals(LocalDate.of(1990, 1, 1))
            && customer.getOccupation().equals("Consultant")
            && customer.getCitizenship().equals("New Zealand")
            && customer.getVisa().equals("N/A");

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void emptyConstructorTest() {
    // Arrange
    Customer customer = new Customer();
    customer.setId("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setLoans(null);
    customer.setStatus("Active");
    customer.setAddresses(null);
    customer.setEmails(null);
    customer.setPhones(null);
    customer.setEmployers(null);
    customer.setNotes("");
    customer.setVisa("N/A");

    // Act
    boolean isCorrect =
        customer.getId().equals("1")
            && customer.getTitle().equals("Mr")
            && customer.getName().equals("John Doe")
            && customer.getDateOfBirth().equals(LocalDate.of(1990, 1, 1))
            && customer.getOccupation().equals("Consultant")
            && customer.getCitizenship().equals("New Zealand")
            && customer.getLoans() == null
            && customer.getStatus().equals("Active")
            && customer.getAddresses() == null
            && customer.getEmails() == null
            && customer.getPhones() == null
            && customer.getEmployers() == null
            && customer.getNotes().equals("")
            && customer.getVisa().equals("N/A");

    // Assert
    assertTrue(isCorrect);
  }

  @Test
  public void toStringTest() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setVisa("N/A");

    // Act
    String expected = "John Doe: ID = 1";
    String actual = customer.toString();

    // Assert
    assertEquals(expected, actual);
  }

  @Test
  public void validateTest() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setVisa("N/A");

    // Act
    // boolean isCorrect = customer.validate();

    // Assert
    // assertTrue(isCorrect);
  }

  @Test
  public void validateValidCustomer() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setTitle("Mr");
    customer.setName("John Doe");
    customer.setDateOfBirth(LocalDate.of(1990, 1, 1));
    customer.setOccupation("Consultant");
    customer.setCitizenship("New Zealand");
    customer.setVisa("N/A");

    // Act
    // Assert
    assertDoesNotThrow(() -> Customer.validateCustomerId(customer.getId()));
    assertDoesNotThrow(() -> Customer.validateCitizenship(customer.getCitizenship()));
    assertDoesNotThrow(() -> Customer.validateDateOfBirth(customer.getDateOfBirth()));
    assertDoesNotThrow(() -> Customer.validateName(customer.getName()));
    assertDoesNotThrow(() -> Customer.validateOccupation(customer.getOccupation()));
    assertDoesNotThrow(() -> Customer.validateTitle(customer.getTitle()));
    assertDoesNotThrow(() -> Customer.validateVisa(customer.getVisa()));
  }

  @Test
  public void validateNullCustomer() {
    // Arrange
    Customer customer = new Customer();

    // Act
    // Assert
    assertThrows(
        ValidationException.class, () -> Customer.validateCitizenship(customer.getCitizenship()));
    assertThrows(
        ValidationException.class, () -> Customer.validateDateOfBirth(customer.getDateOfBirth()));
    assertThrows(ValidationException.class, () -> Customer.validateName(customer.getName()));
    assertThrows(
        ValidationException.class, () -> Customer.validateOccupation(customer.getOccupation()));
    assertThrows(ValidationException.class, () -> Customer.validateTitle(customer.getTitle()));
    assertThrows(ValidationException.class, () -> Customer.validateVisa(customer.getVisa()));
  }

  @Test
  public void validateOverLimitCustomer() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setId("11111111111111111111");
    customer.setTitle("MrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMrMr");
    customer.setName(
        "John DoeJohn DoeJohn DoeJohn DoeJohn DoeJohn DoeJohn DoeJohn DoeJohn DoeJohn Doe");
    customer.setOccupation(
        "ConsultantConsultantConsultantConsultantConsultantConsultantConsultantConsultant");
    customer.setCitizenship("New ZealandNew ZealandNew ZealandNew ZealandNew ZealandNew Zealand");
    customer.setVisa("N/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/AN/A");

    // Act
    // Assert
    assertThrows(ValidationException.class, () -> Customer.validateCustomerId(customer.getId()));
    assertThrows(
        ValidationException.class, () -> Customer.validateCitizenship(customer.getCitizenship()));
    assertThrows(ValidationException.class, () -> Customer.validateName(customer.getName()));
    assertThrows(
        ValidationException.class, () -> Customer.validateOccupation(customer.getOccupation()));
    assertThrows(ValidationException.class, () -> Customer.validateTitle(customer.getTitle()));
    assertThrows(ValidationException.class, () -> Customer.validateVisa(customer.getVisa()));
  }

  @Test
  public void validateInvalidDob() {
    // Arrange
    Customer customer = new Customer("1");
    customer.setDateOfBirth(LocalDate.of(2025, 1, 1));

    // Act
    // Assert
    assertThrows(
        ValidationException.class, () -> Customer.validateDateOfBirth(customer.getDateOfBirth()));
  }
}
