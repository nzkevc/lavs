package uoa.lavs.models;

import java.time.LocalDate;

public class Customer implements IModel<Customer> {

  private String id;
  private String status;
  private String title;
  private String name;
  private LocalDate dateOfBirth;
  private String occupation;
  private String citizenship;
  private String visa;
  private Addresses addresses;
  private Phones phones;
  private Emails emails;
  private Employer employer;
  private String notes;
  private Loans loans;

  public Customer() {}

  public Customer(String id) {
    this.id = id;
  }

  private Customer(Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.name = builder.name;
    this.dateOfBirth = builder.dateOfBirth;
    this.occupation = builder.occupation;
    this.citizenship = builder.citizenship;
    this.visa = builder.visa;
    this.addresses = builder.addresses;
    this.phones = builder.phones;
    this.emails = builder.emails;
    this.employer = builder.employer;
    this.notes = builder.notes;
    this.loans = builder.loans;
  }

  /**
   * Addresses, Phones, Emails, and Loans objects are initialized in the Builder, use
   * Builder(...).add... methods to add primary and additional addresses, phones, emails, and
   * employer informaiton.
   *
   * <p>NOTE: When using each entity's respective repositories to add to the database, the customer
   * must already exist in the mainframe database and you must update each with the customer's ID
   * before persisting them to the database.
   */
  public static class Builder {
    private String id;
    private String title;
    private String name;
    private LocalDate dateOfBirth;
    private String occupation;
    private String citizenship;
    private String visa;
    private Addresses addresses;
    private Phones phones;
    private Emails emails;
    private Employer employer;
    private String notes;
    private Loans loans;

    public Builder(
        String id,
        String title,
        String name,
        LocalDate dateOfBirth,
        String occupation,
        String citizenship,
        String visa) {
      this.id = id;
      this.title = title;
      this.name = name;
      this.dateOfBirth = dateOfBirth;
      this.occupation = occupation;
      this.citizenship = citizenship;
      this.visa = visa;
      this.addresses = new Addresses(id);
      this.phones = new Phones(id);
      this.emails = new Emails(id);
      this.notes = "";
    }

    public Builder addPrimaryAddress(Address primaryAddress) {
      this.addresses.setResidentialAddress(primaryAddress);
      return this;
    }

    public Builder addMailingAddress(Address mailingAddress) {
      this.addresses.setMailingAddress(mailingAddress);
      return this;
    }

    public Builder addAddress(Address address) {
      this.addresses.addAddress(address);
      return this;
    }

    public Builder addPrimaryPhone(Phone primaryPhone) {
      this.phones.setPrimaryPhone(primaryPhone);
      return this;
    }

    public Builder addTextPhone(Phone textPhone) {
      this.phones.setTextPhone(textPhone);
      return this;
    }

    public Builder addPhone(Phone phone) {
      this.phones.addPhone(phone);
      return this;
    }

    public Builder addPrimaryEmail(Email primaryEmail) {
      this.emails.setPrimaryEmail(primaryEmail);
      return this;
    }

    public Builder addEmail(Email email) {
      this.emails.addEmail(email);
      return this;
    }

    public Builder addNotes(String notes) {
      this.notes = notes;
      return this;
    }

    public Builder addEmployer(Employer employer) {
      this.employer = employer;
      return this;
    }

    public Customer build() {
      return new Customer(this);
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public String getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(String citizenship) {
    this.citizenship = citizenship;
  }

  public String getVisa() {
    return visa;
  }

  public void setVisa(String visa) {
    this.visa = visa;
  }

  public Addresses getAddresses() {
    return addresses;
  }

  public void setAddresses(Addresses addresses) {
    this.addresses = addresses;
  }

  public Phones getPhones() {
    return phones;
  }

  public void setPhones(Phones phones) {
    this.phones = phones;
  }

  public Emails getEmails() {
    return emails;
  }

  public void setEmails(Emails emails) {
    this.emails = emails;
  }

  public Employer getEmployer() {
    return employer;
  }

  public void setEmployer(Employer employer) {
    this.employer = employer;
  }

  public String getNotes() {
    return notes;
  }

  public String setNotes(String notes) {
    this.notes = notes;
    return notes;
  }

  public String appendNotes(String notes) {
    this.notes += notes;
    return notes;
  }

  public Loans getLoans() {
    return loans;
  }

  public void setLoans(Loans loans) {
    this.loans = loans;
  }

  // TODO
  @Override
  public boolean validate() {
    return false;
  }
}
