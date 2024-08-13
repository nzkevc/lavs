package uoa.lavs.models;

import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.messages.customer.FindCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.LoadCustomerPhoneNumber;
import uoa.lavs.mainframe.messages.customer.UpdateCustomerPhoneNumber;

public class Phone implements IModel<Phone> {
  private String customerID;
  private String type;
  private String prefix;
  private String number;
  private Boolean isPrimary;
  private Boolean canSendTxt;

  public Phone(
      String customerID,
      String type,
      String prefix,
      String number,
      Boolean isPrimary,
      Boolean canSendTxt) {
    this.customerID = customerID;
    this.type = type;
    this.prefix = prefix;
    this.number = number;
    this.isPrimary = isPrimary;
    this.canSendTxt = canSendTxt;
  }

  public String getCustomerID() {
    return customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public String getType() {
    return type;
  }

  public String getPrefix() {
    return prefix;
  }

  public String getNumber() {
    return number;
  }

  public String getFullNumber() {
    return prefix + number;
  }

  public Boolean getPrimary() {
    return isPrimary;
  }

  public Boolean getCanSendTxt() {
    return canSendTxt;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    if (o instanceof Phone) {
      Phone phone = (Phone) o;
      return type.equals(phone.getType())
          && prefix.equals(phone.getPrefix())
          && number.equals(phone.getNumber());
    }
    return false;
  }

  /** Checks if the customer has a phone number in the database or not */
  @Override
  public boolean validate() {
    // Using nitrate mainframe connection
    Connection conneciton = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return false;
    }

    FindCustomerPhoneNumber message = new FindCustomerPhoneNumber();
    message.setCustomerId(customerID);

    return message.send(conneciton).getWasSuccessful();
  }

  /** Gets the number of phone numbers the customer has registered to them */
  public int getCountFromServer() {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    if (customerID == null || customerID.length() > 10 || customerID.length() == 0) {
      return 0;
    }

    FindCustomerPhoneNumber message = new FindCustomerPhoneNumber();
    message.setCustomerId(customerID);

    if (message.send(connection).getWasSuccessful()) {
      return message.getCountFromServer();
    }

    return 0;
  }

  /** Adding new phone number of updating existing phone number in the mainframe */
  @Override
  public Phone persist() {
    Connection connection = Instance.getConnection();

    // Setting up the phone message for mainframe
    UpdateCustomerPhoneNumber message = new UpdateCustomerPhoneNumber();
    if (customerID != null && customerID.length() <= 10) {
      message.setCustomerId(customerID);
    } else return null;

    if (type != null) {
      message.setType(type);
    } else return null;

    if (prefix != null) {
      message.setPrefix(prefix);
    } else return null;

    if (number != null) {
      message.setPhoneNumber(number);
    } else return null;

    if (isPrimary != null) {
      message.setIsPrimary(isPrimary);
    } else return null;

    if (canSendTxt != null) {
      message.setCanSendTxt(canSendTxt);
    } else return null;

    // Sending the phone update message to the mainframe
    if (message.send(connection).getWasSuccessful()) {
      return this;
    } else return null;
  }

  @Override
  public void delete() {}

  /** Updates phone instance's fields to be consistent with mainframe */
  @Override
  public Phone get(String id) {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    LoadCustomerPhoneNumber message = new LoadCustomerPhoneNumber();
    message.setCustomerId(id);
    message.setNumber(Integer.parseInt(number));

    // If the connection was not successful, not updating the instance and returning null
    if (message.send(connection).getWasSuccessful()) {
      this.type = message.getTypeFromServer();
      this.prefix = message.getPrefixFromServer();
      this.number = message.getPhoneNumberFromServer();
      this.isPrimary = message.getIsPrimaryFromServer();
      this.canSendTxt = message.getCanSendTxtFromServer();
      return this;
    }

    return null;
  }
}
