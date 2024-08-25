package uoa.lavs.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uoa.lavs.TestEntityCreator;
import uoa.lavs.models.Customer;
import uoa.lavs.repository.CustomerRepository;
import uoa.lavs.utils.objects.ConnectionInstance;

public class NoteServiceTests {
  @BeforeEach
  public void resetTestConnection() {
    ConnectionInstance.resetTestConnection();
  }

  @Test
  public void updateNotesFromCustomerTest() {
    // Arrange
    NoteService noteService = new NoteService();
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String notes = "This is a note";
    customer.setNotes(notes);

    // Act
    NoteService.updateNotesFromCustomer(customer);
    customer.setNotes(NoteService.getNotes(customer));

    // Assert
    assertEquals(notes, customer.getNotes());
  }

  @Test
  public void updateMultipleNotesFromCustomer() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String notes = "This is a note";
    String notes2 = "This is another note";
    customer.setNotes(notes);

    // Act
    NoteService.updateNotesFromCustomer(customer);
    customer.setNotes(NoteService.getNotes(customer));

    // Assert
    assertEquals(notes, customer.getNotes());

    // Act
    customer.setNotes(notes2);
    NoteService.updateNotesFromCustomer(customer);
    customer.setNotes(notes);
    customer.setNotes(NoteService.getNotes(customer));

    // Assert
    assertEquals(notes2, customer.getNotes());
  }

  @Test
  public void updateNullNotesFromCustomerTest() {
    // Arrange
    Customer customer = TestEntityCreator.createBasicCustomer();
    customer = CustomerRepository.create(customer);

    String notes = null;

    // Act
    customer.setNotes(notes);
    NoteService.updateNotesFromCustomer(customer);
    customer.setNotes(NoteService.getNotes(customer));

    // Assert
    assertEquals("", customer.getNotes());
  }
}
