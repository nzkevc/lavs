package uoa.lavs.models;

import java.time.LocalDate;
import uoa.lavs.mainframe.Connection;
import uoa.lavs.mainframe.Instance;
import uoa.lavs.mainframe.messages.customer.FindCustomer;
import uoa.lavs.mainframe.messages.customer.LoadCustomer;
import uoa.lavs.mainframe.messages.customer.UpdateCustomer;

public class Customer implements IModel<Customer> {
  public enum CustomerStatus {
    ACTIVE,
    INACTIVE,
    DELETED
  }

  private String id;
  private CustomerStatus status;
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
    private CustomerStatus status;
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
        String visa,
        Address residentialAddress,
        Phone primaryPhone,
        Email primaryEmail,
        Employer employer) {
      this.id = id;
      this.status = CustomerStatus.ACTIVE;
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

    public Builder setStatus(CustomerStatus status) {
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

  public CustomerStatus getStatus() {
    return status;
  }

  public String getTitle() {
    return title;
  }

  public String getName() {
    return name;
  }

  public LocalDate getDateOfBirth() {
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

  public Addresses getAddresses() {
    return addresses;
  }

  public Phones getPhones() {
    return phones;
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

  public void setStatus(CustomerStatus status) {
    this.status = status;
  }

  /** Checks if the customer is in the mainframe database or not. */
  @Override
  public boolean validate() {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    if (id == null || id.length() > 10 || id.length() == 0) {
      return false;
    }

    FindCustomer findCustomer = new FindCustomer();
    findCustomer.setCustomerId(id);

    return findCustomer.send(connection).getWasSuccessful();
  }

  /** Adding new customer or updating existing customer in the mainframe */
  @Override
  public Customer persist() {
    Connection connection = Instance.getConnection();

    // Setting up the customer message for mainframe
    UpdateCustomer updateCustomer = new UpdateCustomer();
    if (id != null && id.length() <= 10) {
      updateCustomer.setCustomerId(id);
    } else return null;

    if (title != null) {
      updateCustomer.setTitle(title);
    } else return null;

    if (name != null) {
      updateCustomer.setName(name);
    } else return null;

    if (dateOfBirth != null) {
      updateCustomer.setDateofBirth(dateOfBirth);
    } else return null;

    if (occupation != null) {
      updateCustomer.setOccupation(occupation);
    } else return null;

    if (citizenship != null) {
      updateCustomer.setCitizenship(citizenship);
    } else return null;

    if (visa != null) {
      updateCustomer.setVisa(visa);
    } else return null;

    // Sending the customer update message to the mainframe
    if (updateCustomer.send(connection).getWasSuccessful()) {
      return this;
    } else return null;
  }

  @Override
  public void delete() {
    // call mainframe to delete
  }

  /** Updates customer instance's fields to be consistent with mainframe */
  @Override
  public Customer get(String id) {
    // Using nitrate mainframe connection
    Connection connection = Instance.getConnection();

    LoadCustomer loadCustomer = new LoadCustomer();
    loadCustomer.setCustomerId(id);

    // If the connection was not successful, not updating the instance and returning null
    if (loadCustomer.send(connection).getWasSuccessful()) {
      this.id = id;
      this.title = loadCustomer.getTitleFromServer();
      this.name = loadCustomer.getNameFromServer();
      this.dateOfBirth = loadCustomer.getDateofBirthFromServer();
      this.occupation = loadCustomer.getOccupationFromServer();
      this.citizenship = loadCustomer.getCitizenshipFromServer();
      this.visa = loadCustomer.getVisaFromServer();
      return this;
    }

    return null;
  }
}
