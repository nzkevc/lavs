package uoa.lavs.models;

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

  // TODO
  @Override
  public boolean validate() {
    return true;
  }
}
