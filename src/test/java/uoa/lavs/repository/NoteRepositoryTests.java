package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import uoa.lavs.models.Customer;

public class NoteRepositoryTests {
  private Customer createBasicCustomer() {
    LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);
    return new Customer.Builder(
            "1", "Mr", "John Doe", dateOfBirth, "Consultant", "New Zealand", "N/A")
        .build();
  }

  @Test
  public void updateNoteTest() {
    // Arrange
    Customer customer = createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent = "This is a note";

    // Act
    noteContent = NoteRepository.update(noteContent, customer);

    // Assert
    assertEquals("This is a note", customer.getNotes());
  }

  @Test
  public void updateNoteWithInvalidCustomerTest() {
    // Arrange
    String note = "This is a note";

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> NoteRepository.update(note, null));
  }

  @Test
  public void getNoteTest() {
    // Arrange
    Customer customer = createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent = "This is a note";
    NoteRepository.update(noteContent, customer);

    // Act
    String note = NoteRepository.get(customer, 1);

    // Assert
    assertEquals("This is a note", note);
  }

  @Test
  public void getInvalidNoteTest() {
    // Arrange

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> NoteRepository.get(null, 1));
  }
}
