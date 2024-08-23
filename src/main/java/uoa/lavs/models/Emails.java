package uoa.lavs.models;

import java.util.ArrayList;
import java.util.List;

public class Emails {
  private String customerId;
  private List<Email> emails;
  private Email primaryEmail;

  public Emails(String customerId) {
    this.customerId = customerId;
    this.emails = new ArrayList<>();
  }

  public Emails(String customerId, Email primaryEmail) {
    this.customerId = customerId;
    this.emails = new ArrayList<>();
    this.primaryEmail = primaryEmail;
    this.emails.add(primaryEmail);
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void addEmail(Email newEmail) {
    if (newEmail != null) {
      emails.add(newEmail);
    }
  }

  public List<Email> getEmails() {
    return emails;
  }

  public Integer getEmailCount() {
    return emails.size();
  }

  public Email getPrimaryEmail() {
    return primaryEmail;
  }

  public void setPrimaryEmail(Email email) {
    if (email != null) {
      if (primaryEmail != null) {
        primaryEmail.setIsPrimary(false);
      }
      email.setIsPrimary(true);
      primaryEmail = email;
      emails.add(email);
    }
  }
}
