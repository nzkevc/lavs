package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerNote;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerNote;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class NoteRepository {
  /** Updates the note for a customer */
  public static String update(String note, Customer customer) throws RuntimeException {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerNote message = new UpdateCustomerNote();

    message.setCustomerId(customer.getId());
    // Hard coded to always be on line 1 to avoid having to split accross multiple lines
    message.setLine(1, note);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update note: " + status.getErrorMessage());
    } else {
      customer.setNotes(note);
      return note;
    }
  }

  /** Retrieves the note for a customer */
  public static String get(Customer customer, Integer number) throws RuntimeException {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerNote message = new LoadCustomerNote();

    message.setCustomerId(customer.getId());
    message.setNumber(number);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to load note: " + status.getErrorMessage());
    } else {
      customer.setNotes(message.getLineFromServer(number));
      return customer.getNotes();
    }
  }
}
