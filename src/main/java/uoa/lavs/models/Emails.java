package uoa.lavs.models;

import java.util.HashSet;
import java.util.Set;
import uoa.lavs.utils.objects.ValidationException;

public class Emails implements IModel {
  private String customerId;
  private Set<Email> emails;
  private Email primaryEmail;

  public Emails(String customerId) {
    this.customerId = customerId;
    this.emails = new HashSet<>();
  }

  public Emails(Set<Email> emails) {
    this.emails = new HashSet<>();
    emails.forEach(this::addEmail);
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
    if (primaryEmail != null) {
      primaryEmail.setIsPrimary(false);
    }
    email.setIsPrimary(true);
    primaryEmail = email;
    emails.add(email);
  }

  public static void validate(Emails emails) throws ValidationException {
    if (emails.getEmails().isEmpty()) {
      return;
    }
    if (emails.getEmails().stream().noneMatch(Email::getIsPrimary)) {
      throw new ValidationException("Primary email must be set.");
    }
  }
}
