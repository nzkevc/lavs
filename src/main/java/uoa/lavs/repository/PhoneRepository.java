package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;
import uoa.lavs.models.Phone;
import uoa.lavs.utils.ConnectionInstance;

public class PhoneRepository {
  private static UpdateCustomerPhoneNumber persist(Phone phone) {
    UpdateCustomerPhoneNumber message = new UpdateCustomerPhoneNumber();

    message.setCustomerId(phone.getCustomerId());
    message.setType(phone.getType());
    message.setPrefix(phone.getPrefix());
    message.setPhoneNumber(phone.getPhoneNumber());
    message.setIsPrimary(phone.getPrimary());

    return message;
  }

  public static Phone create(Phone phone) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerPhoneNumber message = persist(phone);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create phone: " + status.getErrorMessage());
    } else {
      phone.setNumber(message.getNumberFromServer());
      return phone;
    }
  }

  public static Phone update(Phone phone) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerPhoneNumber message = persist(phone);
    message.setNumber(phone.getNumber());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update phone: " + status.getErrorMessage());
    } else {
      phone.setNumber(message.getNumberFromServer());
      return phone;
    }
  }

  public static Phone get(String customerId, Integer number) {
    Phone phone = new Phone(customerId, number);

    Connection connection = ConnectionInstance.getConnection();
    LoadCustomerPhoneNumber message = new LoadCustomerPhoneNumber();

    message.setCustomerId(customerId);
    message.setNumber(number);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get phone: " + status.getErrorMessage());
    } else {
      phone.setPrefix(message.getPrefixFromServer());
      phone.setType(message.getTypeFromServer());
      phone.setPhoneNumber(message.getPhoneNumberFromServer());
      phone.setPrimary(message.getIsPrimaryFromServer());
      phone.setCanSendTxt(message.getCanSendTxtFromServer());
      return phone;
    }
  }
}
