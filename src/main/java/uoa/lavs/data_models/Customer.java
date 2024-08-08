package uoa.lavs.data_models;

import java.util.Date;

public class Customer {
  public enum Status {
    ACTIVE,
    INACTIVE,
    DELETED
  }

  private String id;
  private Status status;
  private String title;
  private String name;
  private Date dateOfBirth;
  private String occupation;
  private String citizenship;
  private String visa;
  private Addresses addresses;
  private Phones phones;
  private Emails emails;
  private Employer employer;
  private String notes;
  private Loans loans;

  private Customer(Builder builder) {
    this.id = builder.id;
    this.status = builder.status;
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

  public static class Builder {
    private String id;
    private Status status;
    private String title;
    private String name;
    private Date dateOfBirth;
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
        Date dateOfBirth,
        String occupation,
        String citizenship,
        String visa,
        Address residentialAddress,
        Phone primaryPhone,
        Email primaryEmail,
        Employer employer) {
      this.id = id;
      this.status = Status.ACTIVE;
      this.title = title;
      this.name = name;
      this.dateOfBirth = dateOfBirth;
      this.occupation = occupation;
      this.citizenship = citizenship;
      this.visa = visa;
      this.addresses = new Addresses(residentialAddress);
      this.phones = new Phones(primaryPhone);
      this.emails = new Emails(primaryEmail);
      this.employer = employer;
      this.notes = "";
      this.loans = new Loans();
    }

    public Builder setStatus(Status status) {
      this.status = status;
      return this;
    }

    public Builder addMailingAddress(Address mailingAddress) {
      this.addresses = new Addresses(addresses.getResidentialAddress(), mailingAddress);
      return this;
    }

    public Builder addAddress(Address address) {
      this.addresses = new Addresses(addresses.getResidentialAddress(), address);
      return this;
    }

    public Builder addTextPhone(Phone textPhone) {
      this.phones = new Phones(phones.getPrimaryPhone(), textPhone);
      return this;
    }

    public Builder addPhone(Phone phone) {
      this.phones.addPhone(phone);
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

    public Customer build() {
      return new Customer(this);
    }
  }

  public String getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public String getTitle() {
    return title;
  }

  public String getName() {
    return name;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public String getOccupation() {
    return occupation;
  }

  public String getCitizenship() {
    return citizenship;
  }

  public String getVisa() {
    return visa;
  }

  public Address getPrimaryAddress() {
    return addresses.getResidentialAddress();
  }

  public Address getPostalAddress() {
    return addresses.getMailingAddress();
  }

  public Addresses getAddresses() {
    return addresses;
  }

  public Phone getPrimaryPhone() {
    return phones.getPrimaryPhone();
  }

  public Phone getTextPhone() {
    return phones.getTextPhone();
  }

  public Phones getPhones() {
    return phones;
  }

  public Email getPrimaryEmail() {
    return emails.getPrimaryEmail();
  }

  public Emails getEmails() {
    return emails;
  }

  public Employer getEmployer() {
    return employer;
  }

  public String getNotes() {
    return notes;
  }

  public Loans getLoans() {
    return loans;
  }

  public String setNotes(String notes) {
    this.notes = notes;
    return notes;
  }

  public String appendNotes(String notes) {
    this.notes += notes;
    return notes;
  }

  public void addLoan(Loan loan) {
    loans.addLoan(loan);
  }

  public void setStatus(Status status) {
    if (!status.equals(Status.ACTIVE) && !status.equals(Status.INACTIVE)) {
      this.status = status;
    }
  }
}
