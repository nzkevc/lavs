package uoa.lavs.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

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
    customer.setEmployer(null);
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
            && customer.getEmployer() == null
            && customer.getNotes().equals("")
            && customer.getVisa().equals("N/A");

    // Assert
    assertTrue(isCorrect);
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
    boolean isCorrect = customer.validate();

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
}
