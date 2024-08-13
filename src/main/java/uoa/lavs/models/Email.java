package uoa.lavs.models;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.messages.customer.FindCustomerEmail;
import uoa.lavs.mainframe.messages.customer.LoadCustomerEmail;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerEmail;

public class Email implements IModel<Email> {
  private String customerID;
  private Integer number;
  private String emailAddress;
  private Boolean isPrrmary;

  public Email(String customerID, Integer number, String emailAddress, Boolean isPrimary) {
    this.customerID = customerID;
    this.number = number;
    this.emailAddress = emailAddress;
    this.isPrrmary = isPrimary;
  }

  public String getCustomerID() {
    return customerID;
  }

  public Integer getNumber() {
    return number;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public Boolean getIsPrimary() {
    return isPrrmary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    isPrrmary = isPrimary;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Email) {
      Email email = (Email) o;
      return emailAddress.equals(email.emailAddress);
    }
    return false;
  }

  @Override
  public boolean validate() {
    Connection connection = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return false;
    }

    FindCustomerEmail findCustomerEmail = new FindCustomerEmail();
    findCustomerEmail.setCustomerId(customerID);

    return findCustomerEmail.send(connection) == null;
  }

  public int getCountFromServer() {
    Connection connection = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return 0;
    }

    FindCustomerEmail findCustomerEmail = new FindCustomerEmail();
    findCustomerEmail.setCustomerId(customerID);

    if (findCustomerEmail.send(connection).getWasSuccessful()) {
      return findCustomerEmail.getCountFromServer();
    }

    return 0;
  }

  @Override
  public Email persist() {
    Connection connection = Instance.getConnection();

    UpdateCustomerEmail updateCustomerEmail = new UpdateCustomerEmail();
    if (customerID != null && customerID.length() <= 10) {
      updateCustomerEmail.setCustomerId(customerID);
    } else return null;

    if (emailAddress != null) {
      updateCustomerEmail.setAddress(emailAddress);
    } else return null;

    if (isPrrmary != null) {
      updateCustomerEmail.setIsPrimary(isPrrmary);
    } else return null;

    if (updateCustomerEmail.send(connection).getWasSuccessful()) {
      return this;
    }

    return null;
  }

  @Override
  public void delete() {}

  @Override
  public Email get(String id) {
    Connection connection = Instance.getConnection();

    LoadCustomerEmail loadCustomerEmail = new LoadCustomerEmail();
    if (customerID != null && customerID.length() <= 10) {
      loadCustomerEmail.setCustomerId(customerID);
    } else return null;

    if (number != null) {
      loadCustomerEmail.setNumber(number);
    } else return null;

    if (loadCustomerEmail.send(connection).getWasSuccessful()) {
      emailAddress = loadCustomerEmail.getAddressFromServer();
      isPrrmary = loadCustomerEmail.getIsPrimaryFromServer();
      return this;
    }

    return null;
  }
}
