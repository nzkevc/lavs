package uoa.lavs.repository;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Status;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmployer;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmployer;
import uoa.lavs.models.Employer;
import uoa.lavs.utils.ConnectionInstance;

public class EmployerRepository {
  private static UpdateCustomerEmployer persist(Employer employer) {
    UpdateCustomerEmployer message = new UpdateCustomerEmployer();

    message.setCustomerId(employer.getCustomerId());
    message.setName(employer.getName());
    message.setLine1(employer.getAddress().getLine1());
    message.setLine2(employer.getAddress().getLine2());
    message.setSuburb(employer.getAddress().getSuburb());
    message.setCity(employer.getAddress().getCity());
    message.setPostCode(employer.getAddress().getPostCode());
    message.setCountry(employer.getAddress().getCountry());
    message.setPhoneNumber(employer.getPhone().getPrefix() + employer.getPhone().getPhoneNumber());
    message.setEmailAddress(employer.getEmail().getAddress());
    message.setWebsite(employer.getWebsite());
    message.setIsOwner(employer.isOwner());

    return message;
  }

  public static Employer create(Employer employer) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerEmployer message = persist(employer);

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to create employer: " + status.getErrorMessage());
    } else {
      employer.setNumber(message.getNumberFromServer());
      return employer;
    }
  }

  public static Employer update(Employer employer) {
    Connection connection = ConnectionInstance.getConnection();

    UpdateCustomerEmployer message = persist(employer);
    message.setNumber(employer.getNumber());

    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to update employer: " + status.getErrorMessage());
    } else {
      employer.setNumber(message.getNumberFromServer());
      return employer;
    }
  }

  public static Employer get(String customerId, Integer number) {
    Employer employer = new Employer(customerId, number);

    Connection connection = ConnectionInstance.getConnection();
    LoadCustomerEmployer message = new LoadCustomerEmployer();

    message.setCustomerId(customerId);
    message.setNumber(number);
    Status status = message.send(connection);

    if (!status.getWasSuccessful()) {
      throw new RuntimeException("Failed to load employer: " + status.getErrorMessage());
    } else {
      employer.setName(message.getNameFromServer());
      employer.getAddress().setLine1(message.getLine1FromServer());
      employer.getAddress().setLine2(message.getLine2FromServer());
      employer.getAddress().setSuburb(message.getSuburbFromServer());
      employer.getAddress().setCity(message.getCityFromServer());
      employer.getAddress().setPostCode(message.getPostCodeFromServer());
      employer.getAddress().setCountry(message.getCountryFromServer());
      employer.getPhone().setPhoneNumber(message.getPhoneNumberFromServer());
      employer.getEmail().setAddress(message.getEmailAddressFromServer());
      employer.setWebsite(message.getWebsiteFromServer());
      employer.setOwner(message.getIsOwnerFromServer());

      return employer;
    }
  }
}
