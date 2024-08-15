package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddress;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.models.Address;
import uoa.lavs.utils.ConnectionInstance;

public class AddressRepository {
  private static UpdateCustomerAddress persist(UpdateCustomerAddress message, Address address) {
    message.setCustomerId(address.getCustomerId());
    message.setType(address.getType());
    message.setLine1(address.getLine1());
    message.setLine2(address.getLine2());
    message.setSuburb(address.getSuburb());
    message.setCity(address.getCity());
    message.setPostCode(address.getPostCode());
    message.setCountry(address.getCountry());
    message.setIsMailing(address.getMailing());
    message.setIsPrimary(address.getPrimary());

    return message;
  }

  public static Address create(Address address) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerAddress message = new UpdateCustomerAddress();
    message = persist(message, address);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create address: " + status.getErrorMessage());
    } else {
      address.setNumber(message.getNumberFromServer());
      return address;
    }
  }

  public static Address update(Address address) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerAddress message = new UpdateCustomerAddress();
    message = persist(message, address);
    message.setNumber(address.getNumber());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update address: " + status.getErrorMessage());
    } else {
      address.setNumber(message.getNumberFromServer());
      return address;
    }
  }

  public static Address get(String customerId, Integer number) {
    Address address = new Address(customerId, number);

    Connection connection = ConnectionInstance.getConnection();
    LoadCustomerAddress message = new LoadCustomerAddress();

    message.setCustomerId(customerId);
    message.setNumber(number);
    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to load address: " + status.getErrorMessage());
    } else {
      address.setType(message.getTypeFromServer());
      address.setLine1(message.getLine1FromServer());
      address.setLine2(message.getLine2FromServer());
      address.setSuburb(message.getSuburbFromServer());
      address.setCity(message.getCityFromServer());
      address.setPostCode(message.getPostCodeFromServer());
      address.setCountry(message.getCountryFromServer());
      address.setIsPrimary(message.getIsPrimaryFromServer());
      address.setIsMailing(message.getIsMailingFromServer());
      return address;
    }
  }
}
