package uoa.lavs.repository;

import java.util.ArrayList;
import java.util.List;
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

    List<String> serializedNotes = noteSerializer(note);

    UpdateCustomerNote message = new UpdateCustomerNote();

    message.setCustomerId(customer.getId());
    for (int i = 0; i < serializedNotes.size(); i++) {
      message.setLine(i + 1, serializedNotes.get(i));
    }

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update note: " + status.getErrorMessage());
    }

    customer.setNotes(noteDeserializer(serializedNotes));
    return customer.getNotes();
  }

  /** Retrieves the note for a customer */
  public static String get(Customer customer) throws RuntimeException {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerNote message = new LoadCustomerNote();

    List<String> notes = new ArrayList<>();

    message.setCustomerId(customer.getId());
    message.setNumber(1);
    message.send(connection);
    message.setNumber(message.getPageCountFromServer());
    message.send(connection);

    // If there are no notes, returning an empty string
    if (message.getLineCountFromServer() == 0) {
      return "";
    }

    for (int i = 1; i <= message.getLineCountFromServer(); i++) {
      notes.add(message.getLineFromServer(i));
    }

    customer.setNotes(noteDeserializer(notes));

    return customer.getNotes();
  }

  /** Used to format notes from the user interface into the mainframe's format */
  private static List<String> noteSerializer(String note) {
    List<String> serializedNotes = new ArrayList<>();

    // Converting null note to an empty string
    if (note == null) {
      serializedNotes.add("");
      return serializedNotes;
    }

    int lineLength = 70;

    String[] lines = note.split("\n");
    for (String line : lines) {
      // If the line is empty, making sure to add an empty line to the serialized notes
      if (line.length() == 0) {
        serializedNotes.add("");
        continue;
      }

      // If line is not empty, adding the line(s) based on line length
      for (int i = 0; i < line.length(); i += lineLength) {
        serializedNotes.add(line.substring(i, Math.min(i + lineLength, line.length())));
      }
    }

    return serializedNotes;
  }

  /** Used to format notes from the mainframe into the user interface's format */
  private static String noteDeserializer(List<String> noteLines) {
    StringBuilder note = new StringBuilder();

    // Converting note to string with appropriate new lines
    for (String line : noteLines) {
      note.append(line);
      note.append("\n");
    }

    // Removing the last new line character
    note.setLength(note.length() - 1);
    return note.toString();
  }
}
