package uoa.lavs.models;

import uoa.lavs.utils.objects.ValidationException;

public class Email implements IModel<Email> {
  private String customerID;
  private Integer number;
  private String emailAddress;
  private boolean isPrimary;

  public Email() {}

  public Email(String customerId, Integer number) {
    this.customerID = customerId;
    this.number = number;
  }

  public String getCustomerId() {
    return customerID;
  }

  public void setCustomerId(String customerID) {
    this.customerID = customerID;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getAddress() {
    return emailAddress;
  }

  public void setAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public Boolean getIsPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
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

  // TODO
  @Override
  public boolean validate() throws ValidationException {
    if (validateCustomerId(customerID)
        && validateNumber(number)
        && validateAddress(emailAddress)
        && validateIsPrimary(isPrimary)) {
      return true;
    } else {
      throw new ValidationException("Email validation failed");
    }
  }

  public boolean validateCustomerId(String customerId) {
    if (customerId == null) {
      return false;
    }
    return customerId.length() <= 10 && !customerId.isEmpty();
  }

  public boolean validateNumber(Integer number) {
    return number != null;
  }

  public boolean validateAddress(String address) {
    if (address == null) {
      return false;
    }
    return address.length() <= 60 && !address.isEmpty();
  }

  public boolean validateIsPrimary(Boolean isPrimary) {
    return isPrimary != null;
  }
}
