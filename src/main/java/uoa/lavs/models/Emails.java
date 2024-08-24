package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;
import uoa.lavs.utils.objects.ValidationException;

public class Emails {
  private String customerId;
  private Set<Email> emails;
  private Email primaryEmail;

  public Emails(String customerId) {
    this.customerId = customerId;
    this.emails = new HashSet<>();
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addEmail(Email newEmail) {
    if (newEmail != null) {
      if (newEmail.getIsPrimary()) {
        setPrimaryEmail(newEmail);
      } else {
        emails.add(newEmail);
      }
    }
  }

  public Set<Email> getEmails() {
    return emails;
  }

  public Integer getEmailCount() {
    return emails.size();
  }

  public Email getPrimaryEmail() {
    return primaryEmail;
  }

  void setPrimaryEmail(Email email) {
    if (email != null) {
      if (primaryEmail != null) {
        primaryEmail.setIsPrimary(false);
      }
      email.setIsPrimary(true);
      primaryEmail = email;
      emails.add(email);
    }
  }

  public boolean validate() throws ValidationException {
    if (emails.isEmpty() || (primaryEmail != null && primaryEmail.getIsPrimary())) {
      return true;
    } else {
      throw new ValidationException("Primary email is not set");
    }
  }
}
