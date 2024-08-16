package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerAddresses;
import uoa.lavs.models.Address;
import uoa.lavs.models.Addresses;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.ConnectionInstance;

public class AddressesRepository {
  /** Retrieves all the addresses registered to a customer in an Addresses object */
  public static Addresses get(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    LoadCustomerAddresses message = new LoadCustomerAddresses();
    message.setCustomerId(customer.getId());

    Status status = message.send(connection);
    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to get addresses: " + status.getErrorMessage());
    }

    Addresses addresses = new Addresses(customer.getId());
    int count = message.getCountFromServer();

    // If the connection was successful, adding the addresses to the Addresses object
    for (int i = 1; i <= count; i++) {
      Address address = AddressRepository.get(customer.getId(), message.getNumberFromServer(i));

      if (address.getPrimary() || address.getMailing()) {
        if (address.getPrimary()) {
          addresses.setResidentialAddress(address);
        }
        if (address.getMailing()) {
          addresses.setMailingAddress(address);
        }
      } else {
        addresses.addAddress(address);
      }
    }

    return addresses;
  }
}
