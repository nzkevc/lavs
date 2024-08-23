package uoa.lavs.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class NoteRepositoryTests {

  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void updateNoteTest() {
    // Arrange
    NoteRepository noteRepository = new NoteRepository();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent = "This is a note";

    // Act
    noteContent = NoteRepository.update(noteContent, customer);

    // Assert
    assertEquals("This is a note", customer.getNotes());
  }

  @Test
  public void updateLargeNoteTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent =
        "This is a note with a lot of characters to test that the note correctly splits across"
            + " multiple lines when being sent to the mainframe.";

    // Act
    noteContent = NoteRepository.update(noteContent, customer);

    // Assert
    assertEquals(
        "This is a note with a lot of characters to test that the note correctl\ny splits across"
            + " multiple lines when being sent to the mainframe.",
        customer.getNotes());
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
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent =
        "This is a note with a lot of characters to test that the note correctly splits across"
            + " multiple lines when being sent to the mainframe.";
    String note = NoteRepository.update(noteContent, customer);

    // Act
    note = NoteRepository.get(customer);

    // Assert
    assertEquals(
        "This is a note with a lot of characters to test that the note correctl\n"
            + "y splits across multiple lines when being sent to the mainframe.",
        note);
  }

  @Test
  public void getNoteWithGapTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent =
        "This is a note with a lot of characters to test that the note correctly splits across"
            + "\nmultiple lines when being sent to the mainframe.";
    String note = NoteRepository.update(noteContent, customer);

    // Act
    note = NoteRepository.get(customer);

    // Assert
    assertEquals(
        "This is a note with a lot of characters to test that the note correctl\n"
            + "y splits across\n"
            + "multiple lines when being sent to the mainframe.",
        note);
  }

  @Test
  public void getInvalidNoteTest() {
    // Arrange

    // Act

    // Assert
    assertThrows(RuntimeException.class, () -> NoteRepository.get(null));
  }

  @Test
  public void updateNullNoteTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    // Act
    String note = NoteRepository.update(null, customer);

    // Assert
    assertEquals("", note);
  }

  @Test
  public void newLineNoteTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String noteContent = "this is a note\n\n\n\nthis is a note";

    // Act
    String note = NoteRepository.update(noteContent, customer);

    // Assert
    assertEquals(noteContent, note);
  }
}
