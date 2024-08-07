package uoa.lavs.data_models;

public class Email {
  private String emailAddress;

  public Email(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress() {
    return emailAddress;
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
}
