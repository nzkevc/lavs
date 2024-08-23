package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddress;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerAddress;
import uoa.lavs.models.Address;
import uoa.lavs.utils.objects.ConnectionInstance;

public class AddressRepository {
  /** Sets up message for persisting methods */
  private static UpdateCustomerAddress persist(Address address) {
    UpdateCustomerAddress message = new UpdateCustomerAddress();
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

  /** Creates new instance of an address in database and retieves identifying number */
  public static Address create(Address address) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerAddress message = persist(address);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create address: " + status.getErrorMessage());
    } else {
      address.setNumber(message.getNumberFromServer());
      return address;
    }
  }

  /** Updates existing address in database */
  public static Address update(Address address) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerAddress message = persist(address);
    message.setNumber(address.getNumber());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update address: " + status.getErrorMessage());
    } else {
      address.setNumber(message.getNumberFromServer());
      return address;
    }
  }

  /** Retrieves address from databas */
  public static Address get(String customerId, Integer number) {
    Address address = new Address();
    address.setCustomerId(customerId);
    address.setNumber(number);

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
