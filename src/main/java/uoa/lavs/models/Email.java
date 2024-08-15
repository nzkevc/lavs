package uoa.lavs.models;


public class Email implements IModel<Email> {
  private String customerID;
  private Integer number;
  private String emailAddress;
  private Boolean isPrrmary;

  public Email(String customerId) {
    this.customerID = customerId;
  }

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
    return true;
  }
}
