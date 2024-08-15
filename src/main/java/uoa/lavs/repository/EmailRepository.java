package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmail;
import uoa.lavs.models.Email;
import uoa.lavs.utils.ConnectionInstance;

public class EmailRepository {
  private static UpdateCustomerEmail persist(Email email) {
    UpdateCustomerEmail message = new UpdateCustomerEmail();

    message.setCustomerId(email.getCustomerId());
    message.setNumber(email.getNumber());
    message.setAddress(email.getAddress());
    message.setIsPrimary(email.getIsPrimary());

    return message;
  }

  public static Email create(Email email) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerEmail message = persist(email);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create email: " + status.getErrorMessage());
    } else {
      email.setNumber(message.getNumberFromServer());
      return email;
    }
  }

  public static Email update(Email email) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerEmail message = persist(email);
    message.setNumber(email.getNumber());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update email: " + status.getErrorMessage());
    } else {
      email.setNumber(message.getNumberFromServer());
      return email;
    }
  }

  public static Email get(String customerId, Integer number) {
    Email email = new Email(customerId, number);

    Connection connection = ConnectionInstance.getConnection();
    LoadCustomerEmail message = new LoadCustomerEmail();

    message.setCustomerId(customerId);
    message.setNumber(number);
    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to load email: " + status.getErrorMessage());
    } else {
      email.setAddress(message.getAddressFromServer());
      email.setIsPrimary(message.getIsPrimaryFromServer());
      return email;
    }
  }
}
