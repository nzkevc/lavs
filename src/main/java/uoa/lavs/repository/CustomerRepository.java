package uoa.lavs.repository;

import java.util.ArrayList;
import java.util.List;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.FindCustomerAdvanced;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;
import uoa.lavs.models.Customer;
import uoa.lavs.utils.objects.ConnectionInstance;

public class CustomerRepository {
  /** Sets up message for persisting methods */
  private static UpdateCustomer persist(Customer customer) {
    UpdateCustomer message = new UpdateCustomer();

    message.setCustomerId(customer.getId());
    message.setName(customer.getName());
    message.setTitle(customer.getTitle());
    message.setDateofBirth(customer.getDateOfBirth());
    message.setOccupation(customer.getOccupation());
    message.setCitizenship(customer.getCitizenship());

    return message;
  }

  /** Creates new instance of a customer in database and retrieves identifying number */
  public static Customer create(Customer customer) {
    Connection connection = ConnectionInstance.getConnection();

    customer.setId(null);
    UpdateCustomer message = persist(customer);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create customer: " + status.getErrorMessage());
    } else {
      customer.setId(message.getCustomerIdFromServer());
      return customer;
    }
  }

  /** Updates existing customer in database */
  public static Customer update(Customer customer) throws RuntimeException {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomer message = persist(customer);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update customer: " + status.getErrorMessage());
    } else {
      customer.setId(message.getCustomerIdFromServer());
      return customer;
    }
  }

  /** Used to get all the customers with a given name */
  public static List<Customer> getCustomersByName(String name) {
    Connection connection = ConnectionInstance.getConnection();
    List<Customer> customers = new ArrayList<>();

    FindCustomerAdvanced message = new FindCustomerAdvanced();
    message.setSearchName(name);

    Status status = message.send(connection);

    for (int i = 1; i <= message.getCustomerCountFromServer(); i++) {
      Customer customer = new Customer(message.getIdFromServer(i));
      customer.setName(message.getNameFromServer(i));
      customer.setDateOfBirth(message.getDateofBirthFromServer(i));
      customers.add(customer);
    }

    return customers;
  }

  /** Retrieves customer from database */
  public static Customer get(String id) throws RuntimeException {
    Customer customer = new Customer(id);

    Connection connection = ConnectionInstance.getConnection();
    LoadCustomer message = new LoadCustomer();

    message.setCustomerId(id);
    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to load customer: " + status.getErrorMessage());
    } else {
      customer.setName(message.getNameFromServer());
      customer.setTitle(message.getTitleFromServer());
      customer.setDateOfBirth(message.getDateofBirthFromServer());
      customer.setOccupation(message.getOccupationFromServer());
      customer.setCitizenship(message.getCitizenshipFromServer());
      customer.setStatus(message.getStatusFromServer());
      customer.setVisa(message.getVisaFromServer());
      return customer;
    }
  }
}
