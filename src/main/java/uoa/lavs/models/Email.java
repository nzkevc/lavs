package uoa.lavs.models;

public class Email implements IModel<Email> {
  private String customerID;
  private Integer number;
  private String emailAddress;
  private Boolean isPrimary;

  public Email(String customerId) {
    this.customerID = customerId;
  }

  public Email(String customerID, Integer number, String emailAddress, Boolean isPrimary) {
    this.customerID = customerID;
    this.number = number;
    this.emailAddress = emailAddress;
    this.isPrimary = isPrimary;
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
  public boolean validate() {
    return true;
  }
}
