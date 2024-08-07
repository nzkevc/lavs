package uoa.lavs.data_models;

import java.util.ArrayList;
import java.util.List;

public class Emails {
  private List<Email> emails;
  private Email primaryEmail;

  public Emails(Email primaryEmail) {
    this.emails = new ArrayList<>();
    this.primaryEmail = primaryEmail;
    this.emails.add(primaryEmail);
  }

  public void addEmail(Email newEmail) {
    emails.add(newEmail);
  }

  public List<Email> getEmails() {
    return emails;
  }

  public Email getPrimaryEmail() {
    return primaryEmail;
  }

  public void setPrimaryEmail(Email email) {
    primaryEmail = email;
  }
}
