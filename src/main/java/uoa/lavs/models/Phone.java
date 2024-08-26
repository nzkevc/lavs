package uoa.lavs.models;

import uoa.lavs.utils.ValidationUtils;
import uoa.lavs.utils.objects.ValidationException;

public class Phone implements IModel {
  private String customerID;
  private Integer number;
  private String type;
  private String prefix;
  private String phoneNumber;
  private boolean isPrimary;
  private boolean canSendTxt;

  public Phone() {}

  public Phone(String customerID, Integer number) {
    this.customerID = customerID;
    this.number = number;
  }

  public String getCustomerId() {
    return customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getFullNumber() {
    return prefix + phoneNumber;
  }

  public boolean getPrimary() {
    return isPrimary;
  }

  public void setPrimary(boolean primary) {
    isPrimary = primary;
  }

  public boolean getCanSendTxt() {
    return canSendTxt;
  }

  public void setCanSendTxt(boolean canSendTxt) {
    this.canSendTxt = canSendTxt;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Phone) {
      Phone phone = (Phone) o;
      return type.equals(phone.getType())
          && prefix.equals(phone.getPrefix())
          && phoneNumber.equals(phone.getPhoneNumber());
    }
    return false;
  }

  public static void validateCustomerID(String customerID) throws ValidationException {
    ValidationUtils.validateFieldExists(customerID);
    Customer.validateCustomerId(customerID);
  }

  public static void validateType(String type) throws ValidationException {
    ValidationUtils.validateFieldExists(type);
    if (type.length() > 10) {
      throw new ValidationException("Type must be between 1 and 10 characters.");
    }
  }

  public static void validatePrefix(String prefix) throws ValidationException {
    ValidationUtils.validateFieldExists(prefix);
    if (prefix.length() > 10) {
      throw new ValidationException("Prefix must be between 1 and 10 characters.");
    }
    if (!prefix.matches("[0-9\\+]+")) {
      throw new ValidationException("Prefix must comprise only numbers and '+'.");
    }
  }

  public static void validatePhoneNumber(String phoneNumber) throws ValidationException {
    ValidationUtils.validateFieldExists(phoneNumber);
    if (phoneNumber.length() > 20) {
      throw new ValidationException("Phone must be between 1 and 20 characters.");
    }
    if (!phoneNumber.matches("[0-9\\-]+")) {
      throw new ValidationException("Phone must comprise only numbers and '-'.");
    }
  }
}
