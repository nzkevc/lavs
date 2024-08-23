package uoa.lavs.models;

public class Phone implements IModel<Phone> {
  private String customerID;
  private Integer number;
  private String type;
  private String prefix;
  private String phoneNumber;
  private Boolean isPrimary;
  private Boolean canSendTxt;

  public Phone() {}

  public Phone(String customerID, Integer number) {
    this.customerID = customerID;
    this.number = number;
  }

  public Phone(
      String customerID,
      Integer number,
      String type,
      String prefix,
      String phoneNumber,
      Boolean isPrimary,
      Boolean canSendTxt) {
    this.customerID = customerID;
    this.number = number;
    this.type = type;
    this.prefix = prefix;
    this.phoneNumber = phoneNumber;
    this.isPrimary = isPrimary;
    this.canSendTxt = canSendTxt;
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

  public Boolean getPrimary() {
    return isPrimary;
  }

  public void setPrimary(Boolean primary) {
    isPrimary = primary;
  }

  public Boolean getCanSendTxt() {
    return canSendTxt;
  }

  public void setCanSendTxt(Boolean canSendTxt) {
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

  // TODO
  @Override
  public boolean validate() {
    return true;
  }
}
